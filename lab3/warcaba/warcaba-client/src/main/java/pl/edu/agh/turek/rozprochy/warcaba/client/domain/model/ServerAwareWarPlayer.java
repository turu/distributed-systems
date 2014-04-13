package pl.edu.agh.turek.rozprochy.warcaba.client.domain.model;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.IWarManager;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarPlayer;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarPlayerToken;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.setup.IGameRequest;

import java.rmi.RemoteException;

/**
 * Author: Piotr Turek
 */
public class ServerAwareWarPlayer implements IWarPlayer {
    private IWarManager warManager;
    private IWarPlayerToken token;

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
        return null;
    }

    private void preDestroy() throws RemoteException {
        warManager.unregister(token);
    }
}
