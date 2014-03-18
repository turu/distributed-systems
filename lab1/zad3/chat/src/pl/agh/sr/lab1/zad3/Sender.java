package pl.agh.sr.lab1.zad3;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * Author: Piotr Turek
 */
public class Sender implements Runnable {
    private final String address;
    private final int port;
    private final String username;

    public Sender(String address, int port, String username) {
        this.address = address;
        this.port = port;
        this.username = username;
    }

    @Override
    public void run() {
        try(DatagramSocket socket = new DatagramSocket()) {
            final Scanner scanner = new Scanner(System.in);
            while (!Thread.interrupted()) {
                final String line = scanner.nextLine();
                final Message message = new Message(username, line);
                byte[] bytes = message.getAsBytes();
                final DatagramPacket packet = new DatagramPacket(bytes, bytes.length, InetAddress.getByName(address), port);
                socket.send(packet);
            }
        } catch (SocketException e) {
            e.printStackTrace();
            System.out.println("Could not open connection");
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.out.println("Could not resolve host");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not send message");
        }
    }
}
