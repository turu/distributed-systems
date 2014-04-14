package pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay.command;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IGameBoard;

import java.io.Serializable;

/**
 * Author: Piotr Turek
 */
public interface IWarCommand extends Serializable {
    void execute(IGameBoard board);
}
