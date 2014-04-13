package pl.edu.agh.turek.rozprochy.warcaba.shared.domain.model;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarGameToken;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarPlayerToken;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.setup.IGameRequest;

import java.util.Arrays;
import java.util.UUID;

/**
 * Author: Piotr Turek
 */
public class BasicWarGameToken implements IWarGameToken {
    private static final long serialVersionUID = 9079360689907877374L;

    private final UUID id;
    private final IWarPlayerToken[] players;
    private final IGameRequest request;

    public BasicWarGameToken(IGameRequest request) {
        this.request = request;
        this.id = request.id();
        players = new IWarPlayerToken[]{request.requesting(), request.requested()};
    }

    @Override
    public UUID id() {
        return id;
    }

    @Override
    public IWarPlayerToken[] players() {
        return Arrays.copyOf(players, 2);
    }

    @Override
    public IGameRequest gameRequest() {
        return request;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasicWarGameToken that = (BasicWarGameToken) o;

        if (!id.equals(that.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "BasicWarGameToken{" +
                "id=" + id +
                ", request=" + request +
                '}';
    }
}
