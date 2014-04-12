package agh.sr.rmi.noteboard.server;

import agh.sr.rmi.noteboard.api.Noteboard;
import agh.sr.rmi.noteboard.server.core.NoteboardImpl;
import org.apache.log4j.Logger;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class NoteboardServer {

	private static final Logger logger = Logger.getLogger(NoteboardServer.class);
	
	// TODO: Te ustawienia powinny byc konfigurowalne w bardziej
	// elegancki sposob -> argumenty programu, moze properties?
	private static final String RMI_REGISTRY_ADDRESS = "rmi://127.0.0.1:1099";
	private static final String NOTEBOARD_REMOTE_OBJECT_NAME = "noteboard";

	public static void main(String[] args) {

		try {

            if(System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }

			// 1. Stworzmy nasz obiekt zdalny
			NoteboardImpl impl = new NoteboardImpl();

			// 2. Wyeksportuj obiekt zdalny, konsekwencje:
			// - otwiera socket (argument 0 oznacza, ze port zostanie wybrany
			// automatycznie przez JVM)
			// - od tej chwili kazdy, kto ma referencje na ten obiekt moze
			// zdalnie wolac na nim metody
			Noteboard noteboard = (Noteboard) UnicastRemoteObject.exportObject(impl, 0);

			// 3. Mamy juz referencje na obiekt zdalny, wiec teraz chcemy ja
			// udostepnic tak, by klienci mogli ta referencje latwo odnalezc.
			// 
			// Referencje mozna udostepnic na wiele sposobow:
			// - mozna ja odczytac z zserializowanego pliku,
			// - mozna ja otrzymac jako argument wywolania (etap drugi laboratorium)
			// - Najpowszechniejszym ze sposobow udostepnienia referencji jest zarejestrowanie
			// jej w publicznym rejestrze (RMI registry).
			
			// 3a. Stworzmy rejestr RMI w procesie serwera, konsekwencje:
			// - rejestr ma ten sam classpath co serwer ("widzi" te same klasy)
			// - 
			Registry createRegistry = LocateRegistry.createRegistry(1099);
			
			// 3b. Rejestrujemy zatem referencje w rejestrze o podanym adresie:
			// "rmi://127.0.0.1:1099/noteboard"
			Naming.rebind(RMI_REGISTRY_ADDRESS + "/" + NOTEBOARD_REMOTE_OBJECT_NAME, noteboard);
			
			// 5. Od tej chwili kliencie moga odnalezc referencje na obiekt w
			// rejestrze i zaczac go uzywac
			logger.debug("Tablica uruchomiona!");
			
		} catch (Exception e) {
			logger.error(e);
			System.exit(-1);
		}
	}
}
