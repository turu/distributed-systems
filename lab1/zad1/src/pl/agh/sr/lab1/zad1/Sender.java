package pl.agh.sr.lab1.zad1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Sender {

    private static final int DEFAULT_PORT = 7777;
    private final String hostname;
    private final IRemoteData remoteData;
    private int messageCount;

    public Sender(String hostname, IRemoteData remoteData, int messageCount) {
        this.hostname = hostname;
        this.remoteData = remoteData;
        this.messageCount = messageCount;
    }

    public void play() {
        while (messageCount-- > 0) {
            sendAndReceive();
            finalizeIteration();
        }
    }

    private void finalizeIteration() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void sendAndReceive() {
        try (Socket socket = new Socket(hostname, DEFAULT_PORT)) {
            doSendAndReceive(socket);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void doSendAndReceive(Socket socket) throws IOException {
        final DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
        final DataInputStream inputStream = new DataInputStream(socket.getInputStream());

        long data = remoteData.send(outputStream);
        System.out.println("Message sent: " + data);

        data = remoteData.receive(inputStream);
        System.out.println("Message returned: " + data);
    }

    public static void main(String[] args) {
        validateArgs(args);

        final Sender sender = setUpSender(args);

        sender.play();
    }

    private static Sender setUpSender(String[] args) {
        final String hostname = args[0].trim();
        final int messageSize = Integer.parseInt(args[1].trim());
        final int numberOfMessages = Integer.parseInt(args[2].trim());
        final IRemoteDataFactory factory = new RemoteDataFactory(messageSize);
        final IRemoteData remoteData = factory.create();

        return new Sender(hostname, remoteData, numberOfMessages);
    }

    private static void validateArgs(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: <server addr> <size of a message in bytes> <number of messages>");
            System.exit(-1);
        }
    }

}
