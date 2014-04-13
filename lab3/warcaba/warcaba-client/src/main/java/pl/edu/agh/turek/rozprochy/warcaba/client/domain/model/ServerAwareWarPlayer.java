package pl.edu.agh.turek.rozprochy.warcaba.client.domain.model;

import org.slf4j.LoggerFactory;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.IWarManager;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.exceptions.WarGameException;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarPlayer;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarPlayerToken;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.setup.IGameRequest;

import java.rmi.RemoteException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Author: Piotr Turek
 */
public class ServerAwareWarPlayer implements IWarPlayer {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ServerAwareWarPlayer.class);

    private final IWarManager warManager;
    private final IWarPlayerToken token;

    private final BlockingDeque<IGameRequest> gameRequests = new LinkedBlockingDeque<>();

    public ServerAwareWarPlayer(IWarManager warManager, IWarPlayerToken token) {
        this.warManager = warManager;
        this.token = token;
    }

    @Override
    public IWarPlayerToken getToken() throws RemoteException {
        return token;
    }

    @Override
    public IGameRequest waitForGameRequest() throws RemoteException {
        try {
            return gameRequests.take();
        } catch (InterruptedException e) {
            LOG.warn("Player {} waiting for an enemy has been interrupted", token);
        }
        return null;
    }

    @Override
    public void onGameRequested(IGameRequest request) throws RemoteException {
        LOG.info("Game request {} received by {}", request, token);
        try {
            gameRequests.put(request);
        } catch (InterruptedException e) {
            LOG.warn("Player {} waiting till he can receive game request has been interrupted", token);
        }
    }

    private void preDestroy() throws RemoteException, WarGameException {
        warManager.unregister(token);
    }
}
