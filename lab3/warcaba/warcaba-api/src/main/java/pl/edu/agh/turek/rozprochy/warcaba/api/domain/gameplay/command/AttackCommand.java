package pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay.command;

import com.google.common.base.Optional;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IChecker;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IGameBoard;

/**
 * Author: Piotr Turek
 */
public class AttackCommand implements IWarCommand {
    private static final long serialVersionUID = 6997745219366905906L;

    private final IGameBoard board;
    private final Location sourceLocation;
    private final Direction direction;

    public AttackCommand(IGameBoard board, Location sourceLocation, Direction direction) {
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
            board.removeCheckerAt(sourceLocation.y() + direction.yDiff(), sourceLocation.x() + direction.xDiff());
            int newY = (int) (sourceLocation.y() + Math.signum(direction.yDiff()) * (Math.abs(direction.yDiff()) > 0 ? 2 : 0));
            int newX = (int) (sourceLocation.x() + Math.signum(direction.xDiff()) * (Math.abs(direction.xDiff()) > 0 ? 2 : 0));
            board.addCheckerAt(newY, newX, checker);
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

}
