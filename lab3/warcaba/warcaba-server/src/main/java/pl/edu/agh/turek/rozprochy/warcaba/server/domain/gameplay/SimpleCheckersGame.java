package pl.edu.agh.turek.rozprochy.warcaba.server.domain.gameplay;

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

    public SimpleCheckersGame(IWarGameToken token) {
        this.token = token;
        this.players = token.players();
    }

    @Override
    public IWarGameToken getToken() throws RemoteException {
        return token;
    }
}
