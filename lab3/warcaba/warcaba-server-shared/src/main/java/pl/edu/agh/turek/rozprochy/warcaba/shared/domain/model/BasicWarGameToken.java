package pl.edu.agh.turek.rozprochy.warcaba.shared.domain.model;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarGameToken;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarPlayerToken;

import java.util.Arrays;
import java.util.UUID;

/**
 * Author: Piotr Turek
 */
public class BasicWarGameToken implements IWarGameToken {
    private static final long serialVersionUID = 9079360689907877374L;

    private final UUID id = UUID.randomUUID();
    private final IWarPlayerToken[] players;

    public BasicWarGameToken(IWarPlayerToken[] players) {
        this.players = players;
    }

    @Override
    public UUID id() {
        return id;
    }

    @Override
    public IWarPlayerToken[] players() {
        return players;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasicWarGameToken that = (BasicWarGameToken) o;

        if (!id.equals(that.id)) return false;
        if (!Arrays.equals(players, that.players)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + Arrays.hashCode(players);
        return result;
    }
}
