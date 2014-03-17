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

        try (Socket socket = serverSocket.accept();
             OutputStream outputStream = new FileOutputStream(outputFile)) {

            final DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            readAndStoreBytes(outputStream, dataInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readAndStoreBytes(OutputStream outputStream, DataInputStream dataInputStream) throws IOException {
        final int fileLength = dataInputStream.readInt();
        final byte[] buffer = new byte[fileLength];

        int totalRead = 0;
        while (totalRead < fileLength) {
            final int bytesRead = readAndStoreChunk(outputStream, dataInputStream, buffer);
            if (bytesRead == -1) {
                break;
            }
            totalRead += bytesRead;
        }
    }

    private int readAndStoreChunk(OutputStream outputStream, DataInputStream dataInputStream, byte[] buffer) throws IOException {
        final int bytesRead = dataInputStream.read(buffer);
        outputStream.write(buffer, 0, bytesRead);
        return bytesRead;
    }

    public static void main(String[] args) {
        final String outputDir = args.length < 1 ? "/tmp" : args[0];
        new Receiver(outputDir).play();
    }
}
