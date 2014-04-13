package pl.edu.agh.turek.rozprochy.warcaba.server.domain.setup;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay.IWarGame;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarGameToken;

/**
 * Author: Piotr Turek
 */
public interface IGameFactory {
    IWarGame createForToken(IWarGameToken gameToken);
}
