package pl.edu.agh.turek.rozprochy.warcaba.server.domain;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.IWarManager;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.exceptions.PlayerAlreadyExists;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.exceptions.WarAuthenticationException;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.exceptions.WarGameException;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay.IWarGame;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IPlayerPair;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarGameToken;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarPlayerToken;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.setup.IGameRequest;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.setup.exceptions.GameRequestException;
import pl.edu.agh.turek.rozprochy.warcaba.server.domain.setup.IGameFactory;
import pl.edu.agh.turek.rozprochy.warcaba.shared.domain.model.BasicWarGameToken;
import pl.edu.agh.turek.rozprochy.warcaba.shared.domain.model.BasicWarPlayerToken;

import java.rmi.RemoteException;

/**
 * Author: Piotr Turek
 */
public class ConcurrentWarManager implements IWarManager {
    private IWarAuthenticationManager authenticationManager;
    private IPairingManager pairingManager;
    private IGameFactory gameFactory;
    private IGameRegistry gameRegistry;

    public ConcurrentWarManager(IWarAuthenticationManager authenticationManager, IPairingManager pairingManager,
                                IGameFactory gameFactory, IGameRegistry gameRegistry) {
        this.authenticationManager = authenticationManager;
        this.pairingManager = pairingManager;
        this.gameFactory = gameFactory;
        this.gameRegistry = gameRegistry;
    }

    @Override
    public IWarGameToken getGame(IGameRequest request) throws RemoteException, WarGameException {
        IPlayerPair playerPair = getPairForRequest(request);
        final IWarGameToken gameToken = new BasicWarGameToken(playerPair);
        final IWarGame game = gameFactory.createForToken(gameToken);
        gameRegistry.add(gameToken, game);
        return gameToken;
    }

    private IPlayerPair getPairForRequest(IGameRequest request) throws GameRequestException {
        if (pairingManager.hasPairForRequest(request)) {
            return pairingManager.pairForRequest(request);
        } else {
            throw new GameRequestException("Game request could not be fulfilled");
        }
    }

    @Override
    public IWarPlayerToken register(String nick) throws RemoteException, WarGameException {
        BasicWarPlayerToken token;
        synchronized (this) {
            if (authenticationManager.isRegistered(nick)) {
                throw new PlayerAlreadyExists("Player of a nick " + nick + " already exists");
            }
            token = new BasicWarPlayerToken(nick);
            authenticationManager.register(token);
        }
        return token;
    }

    @Override
    public void unregister(IWarPlayerToken token) throws RemoteException, WarGameException {
        if (!authenticationManager.authenticate(token)) {
            throw new WarAuthenticationException("Player doesn't exist");
        }
        authenticationManager.unregister(token);
    }
}
