package pl.edu.agh.turek.rozprochy.warcaba.api.domain.model;

import java.io.Serializable;

/**
 * Author: Piotr Turek
 */
public interface IPlayerPair extends Serializable, Iterable<IWarPlayer> {
    IWarPlayer getPlayer();

    IWarPlayer getEnemy();
}
