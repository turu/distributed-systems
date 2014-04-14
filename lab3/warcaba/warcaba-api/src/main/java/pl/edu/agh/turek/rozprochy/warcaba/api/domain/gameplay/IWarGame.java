package pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IGameBoard;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IPlayerPair;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarGameToken;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Author: Piotr Turek
 */
public interface IWarGame extends Remote {
    IWarGameToken getToken() throws RemoteException;

    IGameBoard getBoard() throws RemoteException;

    GameStatus getGameStatus() throws RemoteException;

    boolean isFinished() throws RemoteException;

    void start() throws RemoteException;

    IPlayerPair getPlayers() throws RemoteException;
}
