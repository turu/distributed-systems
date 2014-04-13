package pl.edu.agh.turek.rozprochy.warcaba.server.domain.setup.gamefactories;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay.IWarGame;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarGameToken;
import pl.edu.agh.turek.rozprochy.warcaba.server.domain.setup.IGameFactory;

/**
 * Author: Piotr Turek
 */
public class BasicGameFactory implements IGameFactory {
    @Override
    public IWarGame createForToken(IWarGameToken gameToken) {
        return null;
    }
}
