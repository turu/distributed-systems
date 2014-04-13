package pl.edu.agh.turek.rozprochy.warcaba.server.domain.gameplay;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay.GameStatus;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay.IWarGame;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IPlayerPair;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarGameToken;

import java.rmi.RemoteException;

/**
 * Author: Piotr Turek
 */
public class SimpleCheckersGame implements IWarGame {
    private final IWarGameToken token;
    private final IPlayerPair players;
    private GameStatus gameStatus;

    public SimpleCheckersGame(IWarGameToken token, IPlayerPair players) {
        this.token = token;
        this.players = players;
        gameStatus = GameStatus.NOT_STARTED;
    }

    @Override
    public IWarGameToken getToken() throws RemoteException {
        return token;
    }

    @Override
    public GameStatus getGameStatus() {
        return gameStatus;
    }

    @Override
    public boolean isFinished() throws RemoteException {
        return gameStatus == GameStatus.FINISHED;
    }
}
