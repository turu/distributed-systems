package pl.edu.agh.turek.rozprochy.warcaba.server.domain.setup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.exceptions.WarAuthenticationException;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarPlayer;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarPlayerToken;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.setup.IGameRequest;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.setup.IWaitingRoom;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.setup.exceptions.EnemyAbsentException;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.setup.exceptions.WaitingRoomException;
import pl.edu.agh.turek.rozprochy.warcaba.server.domain.IPairingManager;
import pl.edu.agh.turek.rozprochy.warcaba.server.domain.IWarAuthenticationManager;
import pl.edu.agh.turek.rozprochy.warcaba.shared.domain.model.BasicPlayerPair;
import pl.edu.agh.turek.rozprochy.warcaba.shared.domain.setup.BasicGameRequest;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Author: Piotr Turek
 */
public class ConcurrentWaitingRoom implements IWaitingRoom {
    private static final Logger LOG = LoggerFactory.getLogger(ConcurrentWaitingRoom.class);

    private final ConcurrentMap<IWarPlayerToken, IWarPlayer> waitingPlayers = new ConcurrentHashMap<>();
    private final IPairingManager pairingManager;
    private final IWarAuthenticationManager authenticationManager;

    public ConcurrentWaitingRoom(IPairingManager pairingManager, IWarAuthenticationManager authenticationManager) {
        this.pairingManager = pairingManager;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public List<IWarPlayerToken> waitingPlayers() throws RemoteException {
        filterOutDeadPlayers();
        final Set<IWarPlayerToken> waitingPlayerTokens = waitingPlayers.keySet();
        LOG.info("List of players requested");
        return new LinkedList<>(waitingPlayerTokens);
    }

    private void filterOutDeadPlayers() {
        for (IWarPlayerToken waiting : waitingPlayers.keySet()) {
            if (!authenticationManager.authenticate(waiting)) {
                waitingPlayers.remove(waiting);
            }
        }
    }

    @Override
    public IGameRequest request(IWarPlayer player, IWarPlayerToken enemyToken) throws RemoteException, WaitingRoomException {
        final IWarPlayerToken playerToken = tryRetrievePlayerToken(player);
        authenticate(enemyToken, playerToken);
        IWarPlayer enemy;
        enemy = retrieveEnemyPlayer(enemyToken);
        final IGameRequest request = new BasicGameRequest(playerToken, enemyToken);
        pairingManager.registerPair(request, new BasicPlayerPair(player, enemy));
        enemy.onGameRequested(request);
        return request;
    }

    @Override
    public void join(IWarPlayer player) throws RemoteException, WaitingRoomException {
        IWarPlayerToken token = tryRetrievePlayerToken(player);
        authenticate(token);
        LOG.info("Player {} joins waiting room", token);
        waitingPlayers.putIfAbsent(token, player);
    }

    private void authenticate(IWarPlayerToken token) throws WaitingRoomException {
        if (!authenticationManager.authenticate(token)) {
            LOG.info("Player {} could not be authenticated!", token);
            throw new WaitingRoomException(new WarAuthenticationException("Player could not be authenticated"));
        }
    }

    private synchronized IWarPlayer retrieveEnemyPlayer(IWarPlayerToken enemyToken) throws EnemyAbsentException {
        if (waitingPlayers.containsKey(enemyToken)) {
            return waitingPlayers.get(enemyToken);
        } else {
            LOG.info("Requested enemy {} is gone.", enemyToken);
            throw new EnemyAbsentException("Requested enemy " + enemyToken + " is gone");
        }
    }

    private void authenticate(IWarPlayerToken enemyToken, IWarPlayerToken playerToken) throws WaitingRoomException {
        final boolean enemyAuth = authenticationManager.authenticate(enemyToken);
        if (!enemyAuth || !authenticationManager.authenticate(playerToken)) {
            cleanUpMissingEnemy(enemyToken, enemyAuth);
            throw new WaitingRoomException(new WarAuthenticationException("Either one or both players are could" +
                    " not be authenticated"));
        }
    }

    private void cleanUpMissingEnemy(IWarPlayerToken enemyToken, boolean enemyAuth) {
        if (!enemyAuth) {
            waitingPlayers.remove(enemyToken);
        }
    }

    private IWarPlayerToken tryRetrievePlayerToken(IWarPlayer player) throws RemoteException {
        IWarPlayerToken token;
        try {
            token = player.getToken();
        } catch (RemoteException e) {
            LOG.error("Communication error, could not retrieve player token");
            throw e;
        }
        return token;
    }

}
