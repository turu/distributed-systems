package pl.edu.agh.turek.rozprochy.warcaba.server.domain.model;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IGameBoard;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IPlayerPair;

/**
 * Author: Piotr Turek
 */
public interface IBoardFactory {
    IGameBoard create(IPlayerPair players);
}
