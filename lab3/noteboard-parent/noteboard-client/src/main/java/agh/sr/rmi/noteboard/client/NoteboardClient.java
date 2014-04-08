package agh.sr.rmi.noteboard.client;

import java.rmi.Naming;

import org.apache.log4j.Logger;

import agh.sr.rmi.noteboard.api.Noteboard;


public class NoteboardClient {

	private static final Logger logger = Logger.getLogger(NoteboardClient.class);
	
	// TODO: Te ustawienia powinny byc konfigurowalne w bardziej
	// elegancki sposob -> argumenty programu, moze properties?
	private static final String RMI_REGISTRY_ADDRESS = "rmi://127.0.0.1:1099";
	private static final String NOTEBOARD_REMOTE_OBJECT_NAME = "noteboard";

	public static void main(String[] args) {

		try {
			// 1. Odszukujemy referencje do obiektu zdalnego - odpytujemy rejestr pod wskazanym adresem
			Noteboard noteboard = (Noteboard) Naming
					.lookup(RMI_REGISTRY_ADDRESS + "/" + NOTEBOARD_REMOTE_OBJECT_NAME);
			logger.debug("Mam referencje do obiektu zdalnego!");
			
			// 2. Wolamy metody zdalne
			noteboard.appendText("Hej!");
			noteboard.appendText("Ho!");
			
			System.out.println("Aktualna zawartosc tablicy: " + noteboard.getText());
			
		} catch (Exception e) {
			logger.error(e);
			System.exit(-1);
		}

		System.exit(0);
	}

}
