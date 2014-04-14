package pl.edu.agh.turek.rozprochy.warcaba.server.domain.gameplay.command.validate;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay.command.IWarCommand;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IGameBoard;

/**
 * Author: Piotr Turek
 */
public class AlwaysTrueValidationStrategy implements IValidationStrategy {
    @Override
    public boolean apply(IWarCommand move, IGameBoard board) {
        return true;
    }
}
