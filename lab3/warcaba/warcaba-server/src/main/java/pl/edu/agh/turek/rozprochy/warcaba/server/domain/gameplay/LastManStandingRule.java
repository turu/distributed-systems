package pl.edu.agh.turek.rozprochy.warcaba.server.domain.gameplay;

import com.google.common.base.Optional;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay.IWarGame;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.*;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Piotr Turek
 */
public class LastManStandingRule implements IFinishRule {
    @Override
    public boolean isGameFinished(IWarGame game) {
        IGameBoard board;
        try {
            board = game.getBoard();
        } catch (RemoteException e) {
            return true;
        }
        return checkIfFinished(board);
    }

    @Override
    public IWarPlayer getVictor(IWarGame game) {
        try {
            return doGetVictor(game);
        } catch (RemoteException e) {
            return null;
        }
    }

    private IWarPlayer doGetVictor(IWarGame game) throws RemoteException {
        IGameBoard board;
        board = game.getBoard();
        final Map<IWarPlayerToken, Boolean> ownersMap = composeOwnersMap(board, board.height(), board.width());
        final IWarPlayerToken victorToken = ownersMap.keySet().iterator().next();
        IPlayerPair players = game.getPlayers();
        for (IWarPlayer player : players) {
            if (player.getToken().equals(victorToken)) {
                return player;
            }
        }
        return null;
    }

    @Override
    public IWarPlayer getLooser(IWarGame game) {
        try {
            return doGetLooser(game);
        } catch (RemoteException e) {
            return null;
        }
    }

    private IWarPlayer doGetLooser(IWarGame game) throws RemoteException {
        final IWarPlayer victor = getVictor(game);
        final IWarPlayerToken victorToken = victor.getToken();
        final IPlayerPair players = game.getPlayers();
        for (IWarPlayer player : players) {
            if (!player.getToken().equals(victorToken)) {
                return player;
            }
        }
        return victor;
    }

    private boolean checkIfFinished(IGameBoard board) {
        final int height = board.height();
        final int width = board.width();
        final Map<IWarPlayerToken, Boolean> ownersMap = composeOwnersMap(board, height, width);
        return ownersMap.size() < 2;
    }

    private Map<IWarPlayerToken, Boolean> composeOwnersMap(IGameBoard board, int height, int width) {
        final Map<IWarPlayerToken, Boolean> ownersMap = new HashMap<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                final Optional<IChecker> opt = board.checkerAt(i, j);
                if (opt.isPresent()) {
                    final IChecker checker = opt.get();
                    ownersMap.put(checker.owner(), true);
                }
            }
        }
        return ownersMap;
    }
}
