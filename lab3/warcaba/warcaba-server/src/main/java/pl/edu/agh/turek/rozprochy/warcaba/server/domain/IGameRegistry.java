package pl.edu.agh.turek.rozprochy.warcaba.server.domain;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay.IWarGame;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarGameToken;

/**
 * Author: Piotr Turek
 */
public interface IGameRegistry {
    void add(IWarGameToken token, IWarGame game);
}
