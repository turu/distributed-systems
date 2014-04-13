package pl.edu.agh.turek.rozprochy.warcaba.api.domain.model;


import java.io.Serializable;
import java.util.UUID;

/**
 * Author: Piotr Turek
 */
public interface IWarGameToken extends Serializable {
    UUID id();

    IPlayerPair players();
}
