package agh.sr.rmi.noteboard.api;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Author: Piotr Turek
 */
public interface NoteboardListener extends Remote {
    void onNewText(String text) throws RemoteException;
}
