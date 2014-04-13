package pl.edu.agh.turek.rozprochy.warcaba.api.domain.setup;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarPlayerToken;

import java.io.Serializable;
import java.util.UUID;

/**
 * Author: Piotr Turek
 */
public interface IGameRequest extends Serializable {
    UUID id();

    IWarPlayerToken requesting();

    IWarPlayerToken requested();
}
