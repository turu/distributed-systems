package pl.edu.agh.turek.rozprochy.warcaba.client.domain.model;

import org.slf4j.LoggerFactory;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.IWarManager;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.exceptions.WarGameException;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay.command.IWarCommand;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IGameBoard;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarGameToken;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarPlayer;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarPlayerToken;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.setup.IGameRequest;

import java.rmi.RemoteException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Author: Piotr Turek
 */
public class ServerAwareWarPlayer implements IWarPlayer {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ServerAwareWarPlayer.class);

    private final IWarManager warManager;
    private final IWarPlayerToken token;
    private final IMoveFactory moveFactory;
    private final IBoardPrinter boardPrinter;

    private final BlockingDeque<IGameRequest> gameRequests = new LinkedBlockingDeque<>();

    public ServerAwareWarPlayer(IWarManager warManager, IWarPlayerToken token, IMoveFactory moveFactory, IBoardPrinter boardPrinter) {
        this.warManager = warManager;
        this.token = token;
        this.moveFactory = moveFactory;
        this.boardPrinter = boardPrinter;
    }

    @Override
    public IWarPlayerToken getToken() throws RemoteException {
        return token;
    }

    @Override
    public IGameRequest waitForGameRequest() throws RemoteException {
        try {
            return gameRequests.take();
        } catch (InterruptedException e) {
            LOG.warn("Player {} waiting for an enemy has been interrupted", token);
        }
        return null;
    }

    @Override
    public void onGameRequested(IGameRequest request) throws RemoteException {
        LOG.info("Game request {} received by {}", request, token);
        try {
            gameRequests.put(request);
        } catch (InterruptedException e) {
            LOG.warn("Player {} waiting till he can receive game request has been interrupted", token);
        }
    }

    @Override
    public void onGameStarted(IWarGameToken warGameToken) throws RemoteException {
        System.out.println("Game " + warGameToken.id() + " has just begun. Wait for your move...");
    }

    @Override
    public IWarCommand move(IGameBoard board, IWarGameToken gameToken) throws RemoteException {
        boardPrinter.print(board);
        return moveFactory.create(board, gameToken);
    }

    @Override
    public void onMoveAccepted(IWarCommand move, IGameBoard board, IWarGameToken gameToken) throws RemoteException {
        System.out.println("Your move has been accepted");
    }

    @Override
    public void onMoveDeclined(IWarCommand move, IGameBoard board, IWarGameToken gameToken) throws RemoteException {
        System.out.println("Your move has been declined");
    }

    @Override
    public void onVictory(IGameBoard board, IWarGameToken gameToken) throws RemoteException {
        System.out.println("You won!!!!oneoneone");
    }

    @Override
    public void onDefeat(IGameBoard board, IWarGameToken gameToken) throws RemoteException {
        System.out.println("You looooose ;(((((");
    }

    @Override
    public void onRoundFinished(IGameBoard board, IWarGameToken token) throws RemoteException {
        System.out.println("Round has finished:");
        boardPrinter.print(board);
        System.out.println("Wait for your move...");
    }

    private void preDestroy() throws RemoteException, WarGameException {
        LOG.info("Unregister myself");
        warManager.unregister(token);
    }
}
