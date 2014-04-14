package pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay.command.validate;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay.command.IWarCommand;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IGameBoard;

/**
 * Author: Piotr Turek
 */
public interface IValidationStrategy {
    boolean apply(IWarCommand move, IGameBoard board);
}
