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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Direction direction = (Direction) o;

        if (xDiff != direction.xDiff) return false;
        if (yDiff != direction.yDiff) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = xDiff;
        result = 31 * result + yDiff;
        return result;
    }

    @Override
    public String toString() {
        return "Direction{" +
                "xDiff=" + xDiff +
                ", yDiff=" + yDiff +
                '}';
    }
}
