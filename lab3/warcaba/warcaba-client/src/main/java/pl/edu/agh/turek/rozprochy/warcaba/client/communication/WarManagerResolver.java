package pl.edu.agh.turek.rozprochy.warcaba.client.communication;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.IWarManager;

/**
 * Author: Piotr Turek
 */
public class WarManagerResolver {
    private RmiResolver rmiResolver;

    public WarManagerResolver(RmiResolver rmiResolver) {
        this.rmiResolver = rmiResolver;
    }

    public IWarManager resolve(String name) {
        return (IWarManager) rmiResolver.resolve(name);
    }
}
