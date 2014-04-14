package pl.edu.agh.turek.rozprochy.warcaba.client.domain.model;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay.command.IWarCommand;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IGameBoard;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarGameToken;

/**
 * Author: Piotr Turek
 */
public interface IMoveFactory {
    IWarCommand create(IGameBoard board, IWarGameToken gameToken);
}
