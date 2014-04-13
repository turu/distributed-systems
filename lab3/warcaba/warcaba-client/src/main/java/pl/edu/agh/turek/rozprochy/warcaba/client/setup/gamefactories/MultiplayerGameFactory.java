package pl.edu.agh.turek.rozprochy.warcaba.client.setup.gamefactories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.IWarManager;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay.IWarGame;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarGameToken;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarPlayer;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarPlayerToken;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.setup.IGameRequest;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.setup.IWaitingRoom;
import pl.edu.agh.turek.rozprochy.warcaba.client.setup.IGameFactory;
import pl.edu.agh.turek.rozprochy.warcaba.client.setup.IWarGameResolver;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

/**
 * Author: Piotr Turek
 */
public class MultiplayerGameFactory implements IGameFactory {
    private static final Logger LOG = LoggerFactory.getLogger(MultiplayerGameFactory.class);

    private IWaitingRoom waitingRoom;
    private IWarPlayer me;
    private IWarManager warManager;
    private Integer maxRetries;
    private IWarGameResolver gameResolver;

    public MultiplayerGameFactory(IWaitingRoom waitingRoom, IWarPlayer me, IWarManager warManager, Integer maxRetries,
                                  IWarGameResolver gameResolver) {
        this.waitingRoom = waitingRoom;
        this.me = me;
        this.warManager = warManager;
        this.maxRetries = maxRetries;
        this.gameResolver = gameResolver;
    }

    @Override
    public IWarGame create() {
        Scanner scanner = new Scanner(System.in);
        int retryCount = 0;
        IWarGameToken gameToken = null;
        while (gameToken == null && retryCount < maxRetries) {
            try {
                IGameRequest request = getGameRequest(scanner);
                gameToken = warManager.getGame(request);
            } catch (RemoteException e) {
                LOG.warn("Communication error, retry count={}", retryCount, e);
            } finally {
                retryCount++;
            }
        }
        LOG.info("Game token retrieved after {} tries", retryCount);
        return gameResolver.resolve(gameToken);
    }

    private IGameRequest getGameRequest(Scanner scanner) throws RemoteException {
        System.out.println(">>> Type 'c' to choose an enemy or 'w' to join waiting room");
        final String decision = scanner.next();
        if (decision.toLowerCase().equals("c")) {
            return tryChooseEnemy(scanner);
        } else {
            return waitForEnemy();
        }
    }

    private IGameRequest waitForEnemy() throws RemoteException {
        waitingRoom.join(me);
        return me.waitForGameRequest();
    }

    private IGameRequest tryChooseEnemy(Scanner scanner) throws RemoteException {
        final List<IWarPlayerToken> players = waitingRoom.waitingPlayers();
        if (!players.isEmpty()) {
            displayWaitingPlayers(players);
            System.out.println(">>> Type id of player you want to play with");
            final int id = scanner.nextInt();
            return tryGetEnemyFromList(scanner, players, id);
        } else {
            return getGameRequest(scanner);
        }
    }

    private IGameRequest tryGetEnemyFromList(Scanner scanner, List<IWarPlayerToken> players, int id) throws RemoteException {
        if (id >= 0 && id < players.size()) {
            final IWarPlayerToken enemy = players.get(id);
            LOG.info("Enemy {} chosen", enemy);
            return waitingRoom.request(me, enemy);
        } else {
            LOG.info("Bad enemy id {}!!!", id);
            return getGameRequest(scanner);
        }
    }

    private void displayWaitingPlayers(Collection<IWarPlayerToken> players) {
        int i = 0;
        System.out.println("List of players awaiting a game:");
        for (IWarPlayerToken player : players) {
            System.out.println(">>> " + i + ": " + player.name());
        }
    }
}
