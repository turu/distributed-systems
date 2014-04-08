package agh.sr.rmi.noteboard.server.core;

import agh.sr.rmi.noteboard.api.Noteboard;
import agh.sr.rmi.noteboard.api.NoteboardException;
import agh.sr.rmi.noteboard.api.NoteboardListener;
import agh.sr.rmi.noteboard.api.UserToken;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class NoteboardImpl implements Noteboard {
	
	private StringBuffer buf;
    private Map<UserToken, NoteboardListener> people = new HashMap<UserToken, NoteboardListener>();

    public NoteboardImpl() {
		buf = new StringBuffer();
	}

	public synchronized void appendText(UserToken token, String newNote)
            throws NoteboardException, RemoteException {
        if (!userRegistered(token)) {
            throw new AccessDeniedForUserException();
        }

        buf.append(newNote);
        notifyClients(token, newNote);
	}

    private void notifyClients(UserToken token, String text) throws RemoteException {
        for (Map.Entry<UserToken, NoteboardListener> entry : people.entrySet()) {
            final UserToken user = entry.getKey();
            final NoteboardListener listener = entry.getValue();
            if (!user.equals(token)) {
                listener.onNewText(text);
            }
        }
    }

    public synchronized void clear(UserToken token) throws NoteboardException {
        if (!userRegistered(token)) {
            throw new AccessDeniedForUserException();
        }
		buf = new StringBuffer();
	}

    private boolean userRegistered(UserToken token) {
        return people.containsKey(token);
    }

    @Override
    public synchronized UserToken register(String nick, NoteboardListener listener)
            throws RemoteException, NoteboardException {
        if (userRegisteredByNick(nick)) {
            throw new UserAlreadyRegisteredException();
        }
        UserToken token = new UserTokenImpl(nick);
        people.put(token, listener);
        return token;
    }

    private boolean userRegisteredByNick(String nick) {
        for (UserToken userToken : people.keySet()) {
            if (userToken.getUserName().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public synchronized void unregister(UserToken token)
            throws RemoteException, NoteboardException {
        if (!userRegistered(token)) {
            throw new UserNotRegisteredException();
        }
        people.remove(token);
    }

    @Override
	public synchronized String getText(UserToken token) throws NoteboardException {
	    if (!userRegistered(token)) {
            throw new AccessDeniedForUserException();
        }
        return buf.toString();
    }
}
