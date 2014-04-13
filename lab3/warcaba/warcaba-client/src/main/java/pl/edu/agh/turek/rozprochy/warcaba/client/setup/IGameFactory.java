package pl.edu.agh.turek.rozprochy.warcaba.client.setup;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay.IWarGame;

/**
 * Author: Piotr Turek
 */
public interface IGameFactory {
    IWarGame create();
}
