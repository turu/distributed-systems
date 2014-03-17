package pl.agh.sr.lab1.zad2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Receiver {
    private static final int DEFAULT_PORT = 7777;

    private final File outputDir;

    public Receiver(String outputDir) {
        this.outputDir = new File(outputDir);
    }

    private void play() {
        try (ServerSocket serverSocket = new ServerSocket(DEFAULT_PORT)) {
            while (!Thread.interrupted()) {
                tryReceiveFile(serverSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void tryReceiveFile(ServerSocket serverSocket) {
        final File outputFile = new File(outputDir, Long.toString(System.currentTimeMillis()));
        try (Socket socket = serverSocket.accept(); OutputStream outputStream = new FileOutputStream(outputFile)) {

            final InputStream inputStream = socket.getInputStream();
            final DataInputStream dataInputStream = new DataInputStream(inputStream);

            final int fileLength = dataInputStream.readInt();
            final byte[] buffer = new byte[1024];

            int totalBytesRead = 0;

            while (totalBytesRead < fileLength) {
                final int bytesRead = dataInputStream.read(buffer);
                outputStream.write(buffer, 0, bytesRead);
                totalBytesRead += bytesRead;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final String outputDir = args.length < 1 ? "/tmp" : args[0];
        new Receiver(outputDir).play();
    }
}
