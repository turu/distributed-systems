package pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarGameToken;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Author: Piotr Turek
 */
public interface IWarGame extends Remote {
    IWarGameToken getToken() throws RemoteException;
}
