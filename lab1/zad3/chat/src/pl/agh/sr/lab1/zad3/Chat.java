package pl.agh.sr.lab1.zad3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Chat {
    private static final String DEFAULT_ADDRESS = "225.7.7.7";
    private static final int DEFAULT_PORT = 7777;

    private final ExecutorService executorService = Executors.newFixedThreadPool(2);
    private final String name;

    public Chat(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        validateArgs(args);
        final String name = args[0];

        new Chat(name).run();
    }

    private static void validateArgs(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: <username>\n");
            System.exit(-1);
        }
    }

    private void run() {
        executorService.submit(new Sender(DEFAULT_ADDRESS, DEFAULT_PORT, name));
        executorService.submit(new Receiver(DEFAULT_ADDRESS, DEFAULT_PORT, name));
    }

}
