package pl.edu.agh.turek.rozprochy.warcaba.api.domain;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarGameToken;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarPlayerToken;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.setup.IGameRequest;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Author: Piotr Turek
 */
public interface IWarManager extends Remote {
    IWarGameToken getGame(IGameRequest request) throws RemoteException;

    IWarPlayerToken register(String nick) throws RemoteException;

    void unregister(IWarPlayerToken token) throws RemoteException;
}
