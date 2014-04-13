package pl.edu.agh.turek.rozprochy.warcaba.server.domain.setup.gamefactories;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay.IWarGame;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IPlayerPair;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarGameToken;
import pl.edu.agh.turek.rozprochy.warcaba.server.domain.IPairingManager;
import pl.edu.agh.turek.rozprochy.warcaba.server.domain.gameplay.SimpleCheckersGame;
import pl.edu.agh.turek.rozprochy.warcaba.server.domain.setup.IGameFactory;

/**
 * Author: Piotr Turek
 */
public class SimpleCheckersGameFactory implements IGameFactory {
    private final IPairingManager pairingManager;

    public SimpleCheckersGameFactory(IPairingManager pairingManager) {
        this.pairingManager = pairingManager;
    }

    @Override
    public IWarGame createForToken(IWarGameToken gameToken) {
        final IPlayerPair players = pairingManager.pairForRequest(gameToken.gameRequest());
        return new SimpleCheckersGame(gameToken, players);
    }
}
