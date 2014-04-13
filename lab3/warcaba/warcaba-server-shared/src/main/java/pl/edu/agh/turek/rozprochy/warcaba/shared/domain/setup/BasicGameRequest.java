package pl.edu.agh.turek.rozprochy.warcaba.shared.domain.setup;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarPlayerToken;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.setup.IGameRequest;

import java.util.UUID;

/**
 * Author: Piotr Turek
 */
public class BasicGameRequest implements IGameRequest {
    private static final long serialVersionUID = -5227079998226607258L;

    private final UUID id = UUID.randomUUID();
    private IWarPlayerToken requesting;
    private IWarPlayerToken requested;

    public BasicGameRequest(IWarPlayerToken requesting, IWarPlayerToken requested) {
        this.requesting = requesting;
        this.requested = requested;
    }

    @Override
    public UUID id() {
        return id;
    }

    @Override
    public IWarPlayerToken requesting() {
        return requesting;
    }

    @Override
    public IWarPlayerToken requested() {
        return requested;
    }
}
