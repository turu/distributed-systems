package pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay.command;

import com.google.common.base.Optional;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IChecker;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IGameBoard;

/**
 * Author: Piotr Turek
 */
public class MoveCommand implements IWarCommand {
    private static final long serialVersionUID = 7037390634515500079L;

    private final IGameBoard board;
    private final Location sourceLocation;
    private final Direction direction;

    public MoveCommand(IGameBoard board, Location sourceLocation, Direction direction) {
        this.board = board;
        this.sourceLocation = sourceLocation;
        this.direction = direction;
    }

    @Override
    public void execute() {
        final Optional<IChecker> checkerOptional = board.checkerAt(sourceLocation.y(), sourceLocation.x());
        if (checkerOptional.isPresent()) {
            board.removeCheckerAt(sourceLocation.y(), sourceLocation.x());
            final IChecker checker = checkerOptional.get();
            board.addCheckerAt(sourceLocation.y() + direction.yDiff(), sourceLocation.x() + direction.xDiff(), checker);
        }
    }

    public IGameBoard getBoard() {
        return board;
    }

    public Location getSourceLocation() {
        return sourceLocation;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MoveCommand that = (MoveCommand) o;

        if (!board.equals(that.board)) return false;
        if (!direction.equals(that.direction)) return false;
        if (!sourceLocation.equals(that.sourceLocation)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = board.hashCode();
        result = 31 * result + sourceLocation.hashCode();
        result = 31 * result + direction.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MoveCommand{" +
                "sourceLocation=" + sourceLocation +
                ", direction=" + direction +
                '}';
    }
}
