package pl.edu.agh.turek.rozprochy.warcaba.api.domain.model;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay.command.IWarCommand;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.setup.IGameRequest;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Author: Piotr Turek
 */
public interface IWarPlayer extends Remote {
    IWarPlayerToken getToken() throws RemoteException;

    IGameRequest waitForGameRequest() throws RemoteException;

    void onGameRequested(IGameRequest request) throws RemoteException;

    IWarCommand move(IGameBoard board, IWarGameToken gameToken);

    void onMoveAccepted(IWarCommand move, IGameBoard board, IWarGameToken gameToken);

    void onMoveDeclined(IWarCommand move, IGameBoard board, IWarGameToken gameToken);

    void onVictory(IGameBoard board, IWarGameToken gameToken);

    void onDefeat(IGameBoard board, IWarGameToken gameToken);
}
