package agh.sr.rmi.noteboard.client.core;

import agh.sr.rmi.noteboard.api.NoteboardListener;
import org.apache.log4j.Logger;

import java.rmi.RemoteException;

/**
 * Author: Piotr Turek
 */
public class NoteboardListenerImpl implements NoteboardListener {
    private static Logger logger = Logger.getLogger(NoteboardListenerImpl.class);

    @Override
    public void onNewText(String text) throws RemoteException {
        logger.info("Text added: " + text);
        System.out.println("Dupa kupa: " + text);
    }
}
