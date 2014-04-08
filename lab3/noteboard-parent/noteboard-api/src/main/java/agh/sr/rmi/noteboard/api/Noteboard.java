package agh.sr.rmi.noteboard.api;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Noteboard extends Remote {
	
	public String getText(UserToken userToken) throws RemoteException, NoteboardException;
	
	public void appendText(UserToken token, String newNote) throws RemoteException, NoteboardException;
	
	public void clear(UserToken token) throws RemoteException, NoteboardException;

    public UserToken register(String nick, NoteboardListener listener) throws RemoteException, NoteboardException;

    public void unregister(UserToken token) throws RemoteException, NoteboardException;
	
}

