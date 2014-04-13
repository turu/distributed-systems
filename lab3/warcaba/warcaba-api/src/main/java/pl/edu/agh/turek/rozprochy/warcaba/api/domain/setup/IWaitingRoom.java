package pl.edu.agh.turek.rozprochy.warcaba.api.domain.setup;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarPlayer;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarPlayerToken;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.setup.exceptions.WaitingRoomException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Author: Piotr Turek
 */
public interface IWaitingRoom extends Remote {
    List<IWarPlayerToken> waitingPlayers() throws RemoteException;

    IGameRequest request(IWarPlayer player, IWarPlayerToken enemy) throws RemoteException, WaitingRoomException;

    void join(IWarPlayer player) throws RemoteException, WaitingRoomException;

}
