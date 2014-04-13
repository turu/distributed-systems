package pl.edu.agh.turek.rozprochy.warcaba.client.communication;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarPlayer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Author: Piotr Turek
 */
public class WarPlayerExporter {
    public static IWarPlayer export(IWarPlayer player, int port) throws RemoteException {
        return (IWarPlayer) UnicastRemoteObject.exportObject(player, port);
    }
}
