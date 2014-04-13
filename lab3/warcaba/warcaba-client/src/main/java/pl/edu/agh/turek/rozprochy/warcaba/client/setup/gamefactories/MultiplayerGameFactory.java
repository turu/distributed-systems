package pl.edu.agh.turek.rozprochy.warcaba.client.setup.gamefactories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.IWarManager;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.exceptions.WarGameException;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay.IWarGame;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarGameToken;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarPlayer;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarPlayerToken;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.setup.IGameRequest;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.setup.IWaitingRoom;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.setup.exceptions.EnemyAbsentException;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.setup.exceptions.WaitingRoomException;
import pl.edu.agh.turek.rozprochy.warcaba.client.communication.WarGameResolver;
import pl.edu.agh.turek.rozprochy.warcaba.client.setup.IGameFactory;

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
    private WarGameResolver gameResolver;

    public MultiplayerGameFactory(IWaitingRoom waitingRoom, IWarPlayer me, IWarManager warManager, Integer maxRetries,
                                  WarGameResolver gameResolver) {
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
                LOG.warn("Communication error while creating game, retry count={}", retryCount, e);
            } catch (EnemyAbsentException e) {
                LOG.warn("Selected enemy was absent at the time of request");
            } catch (WaitingRoomException e) {
                LOG.warn("Details of the problem: ", e);
            } catch (WarGameException e) {
                LOG.warn("Game could not be created", e);
            } finally {
                retryCount++;
            }
        }
        if (gameToken != null) {
            LOG.info("Game token retrieved after {} tries", retryCount);
            return gameResolver.resolve(gameToken);
        }
        throw new BeanCreationException("Could not retrieved game token from the server!");
    }

    private IGameRequest getGameRequest(Scanner scanner) throws RemoteException, WaitingRoomException {
        System.out.println(">>> Type 'c' to choose an enemy or 'w' to join waiting room");
        final String decision = scanner.next();
        if (decision.toLowerCase().trim().equals("c")) {
            return tryChooseEnemy(scanner);
        } else {
            return waitForEnemy();
        }
    }

    private IGameRequest waitForEnemy() throws RemoteException, WaitingRoomException {
        waitingRoom.join(me);
        LOG.info("Entered waiting room. Waiting for game request");
        return me.waitForGameRequest();
    }

    private IGameRequest tryChooseEnemy(Scanner scanner) throws RemoteException, WaitingRoomException {
        final List<IWarPlayerToken> players = waitingRoom.waitingPlayers();
        if (!players.isEmpty()) {
            displayWaitingPlayers(players);
            System.out.println(">>> Type id of player you want to play with");
            final int id = scanner.nextInt();
            return tryGetEnemyFromList(scanner, players, id);
        } else {
            System.out.println("There aren't any players waiting ;(");
            return getGameRequest(scanner);
        }
    }

    private IGameRequest tryGetEnemyFromList(Scanner scanner, List<IWarPlayerToken> players, int id)
            throws RemoteException, WaitingRoomException {
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
