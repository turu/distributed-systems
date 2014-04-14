package pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay.command;

import java.io.Serializable;

/**
 * Author: Piotr Turek
 */
public class Location implements Serializable {
    private static final long serialVersionUID = -8835448152457013162L;

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
