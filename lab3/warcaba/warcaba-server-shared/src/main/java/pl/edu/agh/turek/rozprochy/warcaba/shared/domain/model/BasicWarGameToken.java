package pl.edu.agh.turek.rozprochy.warcaba.shared.domain.model;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IPlayerPair;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarGameToken;

import java.util.UUID;

/**
 * Author: Piotr Turek
 */
public class BasicWarGameToken implements IWarGameToken {
    private static final long serialVersionUID = 9079360689907877374L;

    private final UUID id = UUID.randomUUID();
    private final IPlayerPair players;

    public BasicWarGameToken(IPlayerPair players) {
        this.players = players;
    }

    @Override
    public UUID id() {
        return id;
    }

    @Override
    public IPlayerPair players() {
        return players;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasicWarGameToken that = (BasicWarGameToken) o;

        if (!id.equals(that.id)) return false;
        if (!players.equals(that.players)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + players.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BasicWarGameToken{" +
                "id=" + id +
                ", players=" + players +
                '}';
    }
}
