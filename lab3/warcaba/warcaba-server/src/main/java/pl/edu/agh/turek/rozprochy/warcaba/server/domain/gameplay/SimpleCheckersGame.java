package pl.edu.agh.turek.rozprochy.warcaba.server.domain.gameplay;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay.GameStatus;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay.IWarGame;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay.command.IWarCommand;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IGameBoard;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IPlayerPair;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarGameToken;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarPlayer;
import pl.edu.agh.turek.rozprochy.warcaba.server.domain.gameplay.command.validate.IValidationStrategy;

import java.rmi.RemoteException;
import java.util.Iterator;

/**
 * Author: Piotr Turek
 */
public class SimpleCheckersGame implements IWarGame {
    private final IWarGameToken token;
    private final IPlayerPair players;
    private GameStatus gameStatus;
    private Iterator<IWarPlayer> playerIterator;
    private IGameBoard board;
    private IFinishRule finishRule;
    private IValidationStrategy validationStrategy;

    public SimpleCheckersGame(IWarGameToken token, IPlayerPair players, IGameBoard board, IFinishRule finishRule, IValidationStrategy validationStrategy) {
        this.token = token;
        this.players = players;
        this.board = board;
        this.finishRule = finishRule;
        this.validationStrategy = validationStrategy;
        gameStatus = GameStatus.NOT_STARTED;
    }

    @Override
    public IWarGameToken getToken() throws RemoteException {
        return token;
    }

    @Override
    public IGameBoard getBoard() throws RemoteException {
        return board;
    }

    @Override
    public GameStatus getGameStatus() {
        return gameStatus;
    }

    @Override
    public boolean isFinished() throws RemoteException {
        return gameStatus == GameStatus.FINISHED;
    }

    @Override
    public void start() throws RemoteException {
        gameStatus = GameStatus.IN_PROGRESS;
        notifyPlayersGameStarted();
        playerIterator = players.iterator();
        while (!isFinished()) {
            playRound();
        }
        finalizeGame();
    }

    @Override
    public IPlayerPair getPlayers() throws RemoteException {
        return players;
    }

    private void playRound() throws RemoteException {
        IWarPlayer activePlayer = nextPlayer();
        IWarCommand move = forceMove(activePlayer);
        move.execute();
        notifyPlayersRoundFinished();
        if (finishRule.isGameFinished(this)) {
            gameStatus = GameStatus.FINISHED;
        }
    }

    private IWarCommand forceMove(IWarPlayer activePlayer) throws RemoteException {
        IWarCommand move;
        boolean accepted;
        do {
            move = activePlayer.move(board, token);
            accepted = validationStrategy.apply(move, board);
            handleValidation(activePlayer, move, accepted);
        } while (!accepted);
        return move;
    }

    private void handleValidation(IWarPlayer activePlayer, IWarCommand move, boolean accepted) throws RemoteException {
        if (accepted) {
            activePlayer.onMoveAccepted(move, board, token);
        } else {
            activePlayer.onMoveDeclined(move, board, token);
        }
    }

    private void notifyPlayersRoundFinished() throws RemoteException {
        for (IWarPlayer player : players) {
            player.onRoundFinished(board, token);
        }
    }

    private void notifyPlayersGameStarted() throws RemoteException {
        for (IWarPlayer player : players) {
            player.onGameStarted(token);
        }
    }

    private void finalizeGame() throws RemoteException {
        final IWarPlayer victor = finishRule.getVictor(this);
        final IWarPlayer looser = finishRule.getLooser(this);
        victor.onVictory(board, token);
        looser.onDefeat(board, token);
    }

    private IWarPlayer nextPlayer() {
        if (!playerIterator.hasNext()) {
            playerIterator = players.iterator();
        }
        return playerIterator.next();
    }
}
