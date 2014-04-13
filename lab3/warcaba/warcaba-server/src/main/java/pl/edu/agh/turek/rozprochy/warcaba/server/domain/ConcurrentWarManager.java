package pl.edu.agh.turek.rozprochy.warcaba.server.domain;

import org.slf4j.LoggerFactory;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.IWarManager;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.exceptions.PlayerAlreadyExistsException;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.exceptions.WarAuthenticationException;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.exceptions.WarGameException;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay.IWarGame;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarGameToken;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarPlayerToken;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.setup.IGameRequest;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.setup.exceptions.GameRequestException;
import pl.edu.agh.turek.rozprochy.warcaba.server.domain.setup.IGameFactory;
import pl.edu.agh.turek.rozprochy.warcaba.shared.domain.model.BasicWarGameToken;
import pl.edu.agh.turek.rozprochy.warcaba.shared.domain.model.BasicWarPlayerToken;

import java.rmi.RemoteException;

/**
 * Author: Piotr Turek
 */
public class ConcurrentWarManager implements IWarManager {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ConcurrentWarManager.class);

    private IWarAuthenticationManager authenticationManager;
    private IPairingManager pairingManager;
    private IGameFactory gameFactory;
    private IGameRegistry gameRegistry;

    public ConcurrentWarManager(IWarAuthenticationManager authenticationManager, IPairingManager pairingManager,
                                IGameFactory gameFactory, IGameRegistry gameRegistry) {
        this.authenticationManager = authenticationManager;
        this.pairingManager = pairingManager;
        this.gameFactory = gameFactory;
        this.gameRegistry = gameRegistry;
    }

    @Override
    public IWarGameToken getGame(IGameRequest request) throws RemoteException, WarGameException {
        LOG.info("Requesting game for request {}", request);
        validatePairForRequest(request);
        final IWarGameToken gameToken = new BasicWarGameToken(request);
        createGameIfNeeded(gameToken);
        return gameToken;
    }

    private void createGameIfNeeded(IWarGameToken gameToken) {
        if (!gameRegistry.hasGameFor(gameToken)) {
            final IWarGame game = gameFactory.createForToken(gameToken);
            gameRegistry.add(gameToken, game);
            LOG.info("Game created and added to registry, for token {}", gameToken);
        } else {
            LOG.info("Game for token {} already in the registry");
        }
    }

    private void validatePairForRequest(IGameRequest request) throws GameRequestException {
        if (!pairingManager.hasPairForRequest(request)) {
            throw new GameRequestException("Game request could not be fulfilled");
        }
    }

    @Override
    public IWarPlayerToken register(String nick) throws RemoteException, WarGameException {
        LOG.info("Received register request for nick {}", nick);
        BasicWarPlayerToken token;
        synchronized (this) {
            if (authenticationManager.isRegistered(nick)) {
                LOG.info("Player of nick {} already exists", nick);
                throw new PlayerAlreadyExistsException("Player of a nick " + nick + " already exists");
            }
            token = new BasicWarPlayerToken(nick);
            authenticationManager.register(token);
        }
        return token;
    }

    @Override
    public void unregister(IWarPlayerToken token) throws RemoteException, WarGameException {
        if (!authenticationManager.authenticate(token)) {
            throw new WarAuthenticationException("Player doesn't exist");
        }
        authenticationManager.unregister(token);
    }
}
