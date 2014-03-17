package pl.agh.sr.lab1.zad1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Author: Piotr Turek
 */
public class RemoteInt extends RemoteRandom {

    @Override
    public long provideAndSend(DataOutputStream out) throws IOException {
        int value = random.nextInt();
        out.writeInt(value);
        return value;
    }

    @Override
    public long receive(DataInputStream in) throws IOException {
        return in.readInt();
    }
}
