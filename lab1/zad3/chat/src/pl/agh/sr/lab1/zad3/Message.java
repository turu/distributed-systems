package pl.agh.sr.lab1.zad3;

import java.io.*;

/**
 * Author: Piotr Turek
 */
public class Message {
    private final Long timestamp;
    private final String username;
    private final String line;

    public Message(String username, String line) {
        this.username = username;
        this.line = line;
        this.timestamp = System.currentTimeMillis();
    }

    public Message(String username, String line, long timestamp) {
        this.username = username;
        this.line = line;
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getUsername() {
        return username;
    }

    public String getLine() {
        return line;
    }

    public byte[] getAsBytes() throws IOException {
        final ByteArrayOutputStream byteStream = new ByteArrayOutputStream(38);
        final DataOutputStream out = new DataOutputStream(byteStream);

        out.writeBytes(trimToSize(username, 6));
        out.writeBytes(trimToSize(line, 20));
        out.writeLong(timestamp);
        out.writeInt(this.hashCode());

        return byteStream.toByteArray();
    }

    private String trimToSize(String string, int length) {
        if (string.length() < length) {
            return addPadding(string, length - string.length());
        }
        return string.substring(0, length - 1);
    }

    private String addPadding(String username, int paddingLength) {
        StringBuilder sb = new StringBuilder();
        while (paddingLength-- > 0) {
            sb.append(' ');
        }
        return username + sb.toString();
    }

    public static Message getFromBytes(byte[] dataBuffer) throws IOException {
        final ByteArrayInputStream byteStream = new ByteArrayInputStream(dataBuffer);
        final DataInputStream in = new DataInputStream(byteStream);
        byte[] userBuffer = new byte[6];
        byte[] lineBuffer = new byte[20];

        in.read(userBuffer);
        in.read(lineBuffer);
        final String username = new String(userBuffer);
        final String line = new String(lineBuffer);
        final long timestamp = in.readLong();
        final long checksum = in.readInt();

        final Message message = new Message(username, line, timestamp);

        if (message.hashCode() != checksum) {
            throw new DataIntegrityException();

        }

        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (!line.equals(message.line)) return false;
        if (!timestamp.equals(message.timestamp)) return false;
        if (!username.equals(message.username)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = timestamp.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + line.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "timestamp=" + timestamp +
                ", username='" + username + '\'' +
                ", line='" + line + '\'' +
                "}\n";
    }
}
