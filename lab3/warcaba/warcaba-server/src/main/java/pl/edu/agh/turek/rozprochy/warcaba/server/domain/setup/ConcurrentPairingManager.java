package pl.edu.agh.turek.rozprochy.warcaba.server.domain.setup;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IPlayerPair;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarPlayerToken;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.setup.IGameRequest;
import pl.edu.agh.turek.rozprochy.warcaba.server.domain.IPairingManager;
import pl.edu.agh.turek.rozprochy.warcaba.server.domain.IWarAuthenticationManager;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Author: Piotr Turek
 */
public class ConcurrentPairingManager implements IPairingManager {
    private final ConcurrentMap<IGameRequest, IPlayerPair> requestToPlayersMap = new ConcurrentHashMap<>();
    private IWarAuthenticationManager authenticationManager;

    public ConcurrentPairingManager(IWarAuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void registerPair(IGameRequest request, IPlayerPair playerPair) {
        requestToPlayersMap.putIfAbsent(request, playerPair);
    }

    @Override
    public boolean hasPairForRequest(IGameRequest request) {
        boolean result = true;
        if (!requestToPlayersMap.containsKey(request)) {
            result = false;
        }
        if (!authenticationManager.authenticate(request.requested())) {
            removePairsOf(request.requested());
            result = false;
        }
        if (!authenticationManager.authenticate(request.requesting())) {
            removePairsOf(request.requesting());
            result = false;
        }
        return result;
    }

    private synchronized void removePairsOf(IWarPlayerToken playerToken) {
        for (IGameRequest request : requestToPlayersMap.keySet()) {
            if (request.requested().equals(playerToken) || request.requesting().equals(playerToken)) {
                requestToPlayersMap.remove(request);
            }
        }
    }

    @Override
    public IPlayerPair pairForRequest(IGameRequest request) {
        if (!hasPairForRequest(request)) {
            throw new IllegalArgumentException("Cannot return pair for this request. No pair present for it! " + request);
        }
        return requestToPlayersMap.get(request);
    }
}
