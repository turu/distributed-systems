package pl.agh.sr.lab1.zad1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Author: Piotr Turek
 */
public class RemoteShort extends RemoteRandom {

    private static final int MAX_SHORT = 65536;

    @Override
    public long provideAndSend(DataOutputStream out) throws IOException {
        int value = random.nextInt(MAX_SHORT);
        out.writeShort(value);
        return value;
    }

    @Override
    public long receive(DataInputStream in) throws IOException {
        return in.readShort();
    }
}
