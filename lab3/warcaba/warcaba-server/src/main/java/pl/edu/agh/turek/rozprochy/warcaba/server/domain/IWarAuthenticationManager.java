package pl.edu.agh.turek.rozprochy.warcaba.server.domain;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarPlayerToken;

/**
 * Author: Piotr Turek
 */
public interface IWarAuthenticationManager {
    boolean authenticate(IWarPlayerToken token);

    boolean isRegistered(String nick);

    void register(IWarPlayerToken token);

    void unregister(IWarPlayerToken token);
}
