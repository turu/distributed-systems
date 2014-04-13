package pl.edu.agh.turek.rozprochy.warcaba.client.setup;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay.IWarGame;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarGameToken;

/**
 * Author: Piotr Turek
 */
public interface IWarGameResolver {
    IWarGame resolve(IWarGameToken gameToken);
}
