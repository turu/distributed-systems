package agh.sr.rmi.noteboard.client;

import agh.sr.rmi.noteboard.api.Noteboard;
import agh.sr.rmi.noteboard.api.NoteboardListener;
import agh.sr.rmi.noteboard.api.UserToken;
import agh.sr.rmi.noteboard.client.core.NoteboardListenerImpl;
import org.apache.log4j.Logger;

import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;


public class NoteboardClient {

	private static final Logger logger = Logger.getLogger(NoteboardClient.class);
	
	// TODO: Te ustawienia powinny byc konfigurowalne w bardziej
	// elegancki sposob -> argumenty programu, moze properties?
	private static final String RMI_REGISTRY_ADDRESS = "rmi://127.0.0.1:1099";
	private static final String NOTEBOARD_REMOTE_OBJECT_NAME = "noteboard";

	public static void main(String[] args) {

		try {
            if(System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }

			// 1. Odszukujemy referencje do obiektu zdalnego - odpytujemy rejestr pod wskazanym adresem
			Noteboard noteboard = (Noteboard) Naming
					.lookup(RMI_REGISTRY_ADDRESS + "/" + NOTEBOARD_REMOTE_OBJECT_NAME);
			logger.debug("Mam referencje do obiektu zdalnego!");

            NoteboardListener impl = new NoteboardListenerImpl();
            NoteboardListener listener = (NoteboardListener) UnicastRemoteObject.exportObject(impl, 0);
            listener.onNewText("dupakupa");

            final String name = args[0];

            UserToken token = noteboard.register(name, listener);

			// 2. Wolamy metody zdalne
			noteboard.appendText(token, "Hej!");
			noteboard.appendText(token, "Ho!");
			
			System.out.println("Aktualna zawartosc tablicy: " + noteboard.getText(token));

            noteboard.unregister(token);
			
		} catch (Exception e) {
			logger.error(e);
			System.exit(-1);
		}

		System.exit(0);
	}

}
