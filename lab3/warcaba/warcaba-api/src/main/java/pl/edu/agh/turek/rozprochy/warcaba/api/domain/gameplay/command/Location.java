package pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay.command;

/**
 * Author: Piotr Turek
 */
public class Location {
    private final int x;
    private final int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }
}
