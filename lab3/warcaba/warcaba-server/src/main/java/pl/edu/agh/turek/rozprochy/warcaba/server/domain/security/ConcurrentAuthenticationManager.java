package pl.edu.agh.turek.rozprochy.warcaba.server.domain.security;

import org.slf4j.LoggerFactory;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarPlayerToken;
import pl.edu.agh.turek.rozprochy.warcaba.server.domain.IWarAuthenticationManager;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Author: Piotr Turek
 */
public class ConcurrentAuthenticationManager implements IWarAuthenticationManager {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ConcurrentAuthenticationManager.class);

    private final ConcurrentMap<String, IWarPlayerToken> playerMap = new ConcurrentHashMap<>();

    @Override
    public boolean authenticate(IWarPlayerToken token) {
        if (!isRegistered(token.name())) {
            return false;
        }
        final IWarPlayerToken storedToken = playerMap.get(token.name());
        return token.equals(storedToken);
    }

    @Override
    public boolean isRegistered(String nick) {
        return playerMap.containsKey(nick);
    }

    @Override
    public void register(IWarPlayerToken token) {
        if (isRegistered(token.name())) {
            throw new IllegalArgumentException("Player already registered: " + token);
        }
        playerMap.put(token.name(), token);
        LOG.info("Player {} registered in the system", token);
    }

    @Override
    public void unregister(IWarPlayerToken token) {
        if (!authenticate(token)) {
            throw new IllegalArgumentException("Cannot unregister a player who's not registered! " + token);
        }
        playerMap.remove(token.name());
        LOG.info("Player {} removed from the system", token);
    }
}
