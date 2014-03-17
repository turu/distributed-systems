package pl.agh.sr.lab1.zad1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Author: Piotr Turek
 */
public abstract class RemoteRandom implements IRemoteData {
    protected final Random random = new Random();

    @Override
    public abstract long send(DataOutputStream out) throws IOException;

    @Override
    public abstract long receive(DataInputStream in) throws IOException;
}
