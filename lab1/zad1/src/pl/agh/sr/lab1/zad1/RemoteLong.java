package pl.agh.sr.lab1.zad1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Author: Piotr Turek
 */
public class RemoteLong extends RemoteRandom {

    @Override
    public long send(DataOutputStream out) throws IOException {
        long value = random.nextLong();
        out.writeLong(value);
        return value;
    }

    @Override
    public long receive(DataInputStream in) throws IOException {
        return in.readLong();
    }
}
