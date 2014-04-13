package pl.edu.agh.turek.rozprochy.warcaba.server.domain;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IPlayerPair;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.setup.IGameRequest;

/**
 * Author: Piotr Turek
 */
public interface IPairingManager {
    void registerPair(IGameRequest request, IPlayerPair playerPair);

    boolean hasPairForRequest(IGameRequest request);

    IPlayerPair pairForRequest(IGameRequest request);
}
