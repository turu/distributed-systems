package pl.agh.sr.lab1.zad1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Author: Piotr Turek
 */
public interface IRemoteDataProvider {
   long provideAndSend(DataOutputStream out) throws IOException;
   long receive(DataInputStream in) throws IOException;
}
