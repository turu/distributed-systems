package pl.edu.agh.turek.rozprochy.warcaba.client.communication;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay.IWarGame;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarGameToken;

/**
 * Author: Piotr Turek
 */
public class WarGameResolver {
    private RmiResolver rmiResolver;

    public WarGameResolver(RmiResolver rmiResolver) {
        this.rmiResolver = rmiResolver;
    }

    public IWarGame resolve(IWarGameToken gameToken) {
        return (IWarGame) rmiResolver.resolve(gameToken.id().toString());
    }
}
