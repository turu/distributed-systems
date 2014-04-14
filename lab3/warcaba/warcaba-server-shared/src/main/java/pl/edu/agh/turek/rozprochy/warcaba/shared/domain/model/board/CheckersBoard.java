package pl.edu.agh.turek.rozprochy.warcaba.shared.domain.model.board;

import com.google.common.base.Optional;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IChecker;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IGameBoard;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Author: Piotr Turek
 */
public class CheckersBoard implements IGameBoard {
    private static final long serialVersionUID = 149361671895500849L;

    private final int height;
    private final int width;
    private final ConcurrentMap<Location, IChecker> mapBoard = new ConcurrentHashMap<>();

    public CheckersBoard(int height, int width) {
        this.height = height;
        this.width = width;
    }

    @Override
    public int height() {
        return height;
    }

    @Override
    public int width() {
        return width;
    }

    @Override
    public Optional<IChecker> checkerAt(int i, int j) {
        final Location location = new Location(i, j);
        return Optional.fromNullable(mapBoard.get(location));
    }

    @Override
    public boolean removeCheckerAt(int i, int j) {
        if (!validateLocation(i, j)) {
            return false;
        }
        final Location location = new Location(i, j);
        return mapBoard.remove(location) != null;
    }

    @Override
    public boolean addCheckerAt(int i, int j, IChecker checker) {
        if (!validateLocation(i, j)) {
            return false;
        }
        final Location location = new Location(i, j);
        if (mapBoard.containsKey(location)) {
            return false;
        }
        mapBoard.put(location, checker);
        return true;
    }

    private boolean validateLocation(int i, int j) {
        return i >= 0 && i < height && j >= 0 && j < width;
    }

    private static class Location {
        private final int y;
        private final int x;

        private Location(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Location location = (Location) o;

            if (x != location.x) return false;
            if (y != location.y) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = y;
            result = 31 * result + x;
            return result;
        }
    }
}
