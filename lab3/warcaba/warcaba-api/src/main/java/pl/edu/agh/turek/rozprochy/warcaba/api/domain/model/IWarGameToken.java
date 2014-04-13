package pl.edu.agh.turek.rozprochy.warcaba.api.domain.model;


import pl.edu.agh.turek.rozprochy.warcaba.api.domain.setup.IGameRequest;

import java.io.Serializable;
import java.util.UUID;

/**
 * Author: Piotr Turek
 */
public interface IWarGameToken extends Serializable {
    UUID id();

    IWarPlayerToken[] players();

    IGameRequest gameRequest();
}
