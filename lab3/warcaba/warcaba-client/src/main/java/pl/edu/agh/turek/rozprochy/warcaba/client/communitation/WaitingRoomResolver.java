package pl.edu.agh.turek.rozprochy.warcaba.client.communitation;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.setup.IWaitingRoom;

/**
 * Author: Piotr Turek
 */
public class WaitingRoomResolver {
    private RmiResolver rmiResolver;

    public WaitingRoomResolver(RmiResolver rmiResolver) {
        this.rmiResolver = rmiResolver;
    }

    public IWaitingRoom resolve(String name) {
        return (IWaitingRoom) rmiResolver.resolve(name);
    }
}
