package pl.edu.agh.turek.rozprochy.warcaba.shared.domain.model;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarPlayerToken;

import java.util.UUID;

/**
 * Author: Piotr Turek
 */
public class BasicWarPlayerToken implements IWarPlayerToken {
    private static final long serialVersionUID = -3974466088015918111L;

    private final UUID id = UUID.randomUUID();
    private final String name;

    public BasicWarPlayerToken(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public UUID id() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasicWarPlayerToken that = (BasicWarPlayerToken) o;

        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
