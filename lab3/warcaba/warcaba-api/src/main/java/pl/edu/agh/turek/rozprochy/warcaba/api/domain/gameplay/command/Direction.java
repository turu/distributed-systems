package pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay.command;

/**
 * Author: Piotr Turek
 */
public class Direction {
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
