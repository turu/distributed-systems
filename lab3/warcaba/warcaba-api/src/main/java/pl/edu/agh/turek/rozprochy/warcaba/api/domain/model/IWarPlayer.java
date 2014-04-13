package pl.edu.agh.turek.rozprochy.warcaba.api.domain.model;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.setup.IGameRequest;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Author: Piotr Turek
 */
public interface IWarPlayer extends Remote {
    IWarPlayerToken getToken() throws RemoteException;

    IGameRequest waitForGameRequest() throws RemoteException;
}
