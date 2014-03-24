package pl.agh.sr.lab1.zad3;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * Author: Piotr Turek
 */
public class Receiver implements Runnable {
    private final String address;
    private final int port;
    private final String username;

    public Receiver(String address, int port, String username) {
        this.address = address;
        this.port = port;
        this.username = username;
    }

    @Override
    public void run() {
        try (MulticastSocket socket = new MulticastSocket(port)) {
            socket.setInterface(InetAddress.getByName(null));
            socket.joinGroup(Chat.INET_ADDRESS);
            while (!Thread.interrupted()) {
                byte[] dataBuffer = new byte[38];
                final DatagramPacket packet = new DatagramPacket(dataBuffer, 38);
                socket.receive(packet);

                final Message message = Message.getFromBytes(dataBuffer);
                if (!message.getUsername().equals(username)) {
                    System.out.println(message);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
