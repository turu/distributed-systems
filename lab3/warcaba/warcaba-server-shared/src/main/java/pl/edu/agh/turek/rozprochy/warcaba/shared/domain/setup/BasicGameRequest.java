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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasicGameRequest that = (BasicGameRequest) o;

        if (!id.equals(that.id)) return false;
        if (!requested.equals(that.requested)) return false;
        if (!requesting.equals(that.requesting)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + requesting.hashCode();
        result = 31 * result + requested.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BasicGameRequest{" +
                "id=" + id +
                ", requesting=" + requesting +
                ", requested=" + requested +
                '}';
    }
}
