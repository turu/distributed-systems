package pl.agh.sr.lab1.zad1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Author: Piotr Turek
 */
public class RemoteChar extends RemoteRandom {

    private static final int MAX_CHAR = 256;

    @Override
    public long send(DataOutputStream out) throws IOException {
        int value = random.nextInt(MAX_CHAR);
        out.writeByte(value);
        return value;
    }

    @Override
    public long receive(DataInputStream in) throws IOException {
        return in.readByte();
    }
}
