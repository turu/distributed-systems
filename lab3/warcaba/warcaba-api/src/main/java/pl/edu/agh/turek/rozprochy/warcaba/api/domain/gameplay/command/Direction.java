package pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay.command;

import java.io.Serializable;

/**
 * Author: Piotr Turek
 */
public class Direction implements Serializable {
    private static final long serialVersionUID = -2942226928212019162L;

    private final int xDiff;
    private final int yDiff;

    public Direction(int xDiff, int yDiff) {
        this.xDiff = xDiff > 0 ? 1 : xDiff < 0 ? -1 : 0;
        this.yDiff = yDiff > 0 ? 1 : yDiff < 0 ? -1 : 0;
    }

    public int xDiff() {
        return xDiff;
    }

    public int yDiff() {
        return yDiff;
    }
}
