package pl.edu.agh.turek.rozprochy.warcaba.server.domain.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay.IWarGame;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarGameToken;
import pl.edu.agh.turek.rozprochy.warcaba.server.domain.IGameRegistry;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Author: Piotr Turek
 */
public class BasicGameRegistry implements IGameRegistry {
    private static final Logger LOG = LoggerFactory.getLogger(BasicGameRegistry.class);

    private final ConcurrentMap<IWarGameToken, IWarGame> activeGames = new ConcurrentHashMap<>();

    @Override
    public void add(IWarGameToken token, IWarGame game) {
        activeGames.putIfAbsent(token, game);
    }

    @Override
    public boolean hasGameFor(IWarGameToken gameToken) {
        final boolean hasGameForToken = activeGames.containsKey(gameToken);
        LOG.trace("HasGameForToken {} equals {}", gameToken.id(), hasGameForToken);
        return hasGameForToken;
    }
}
