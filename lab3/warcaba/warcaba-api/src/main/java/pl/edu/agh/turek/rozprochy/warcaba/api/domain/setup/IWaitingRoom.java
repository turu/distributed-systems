package pl.edu.agh.turek.rozprochy.warcaba.api.domain.setup;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarPlayer;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarPlayerToken;

import java.rmi.Remote;
import java.util.List;

/**
 * Author: Piotr Turek
 */
public interface IWaitingRoom extends Remote {
    List<IWarPlayerToken> waitingPlayers();

    IGameRequest request(IWarPlayer player, IWarPlayerToken enemy);

    void join(IWarPlayer player);

    void leave(IWarPlayer player);
}
