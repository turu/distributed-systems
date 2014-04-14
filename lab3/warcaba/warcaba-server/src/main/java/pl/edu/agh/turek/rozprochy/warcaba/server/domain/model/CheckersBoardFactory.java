package pl.edu.agh.turek.rozprochy.warcaba.server.domain.model;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.CheckerType;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IGameBoard;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IPlayerPair;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarPlayer;
import pl.edu.agh.turek.rozprochy.warcaba.shared.domain.model.board.CheckersBoard;
import pl.edu.agh.turek.rozprochy.warcaba.shared.domain.model.board.SimpleChecker;

import java.rmi.RemoteException;

/**
 * Author: Piotr Turek
 */
public class CheckersBoardFactory implements IBoardFactory {
    private final int height;
    private final int width;

    public CheckersBoardFactory(int height, int width) {
        this.height = height;
        this.width = width;
    }

    @Override
    public IGameBoard create(IPlayerPair players) {
        try {
            return doCreate(players);
        } catch (RemoteException e) {
            return null;
        }
    }

    private CheckersBoard doCreate(IPlayerPair players) throws RemoteException {
        final CheckersBoard checkersBoard = new CheckersBoard(height, width);
        final IWarPlayer player = players.getPlayer();
        final IWarPlayer enemy = players.getEnemy();
        checkersBoard.addCheckerAt(0, 0, new SimpleChecker(player.getToken(), CheckerType.NORMAL));
        checkersBoard.addCheckerAt(7, 7, new SimpleChecker(enemy.getToken(), CheckerType.NORMAL));
        return checkersBoard;
    }
}
