package pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay.command;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IGameBoard;

/**
 * Author: Piotr Turek
 */
public interface IWarCommand {
    void apply(IGameBoard board);
}
