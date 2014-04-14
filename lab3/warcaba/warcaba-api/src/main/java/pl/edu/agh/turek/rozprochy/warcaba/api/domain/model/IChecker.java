package pl.edu.agh.turek.rozprochy.warcaba.api.domain.model;

import java.io.Serializable;

/**
 * Author: Piotr Turek
 */
public interface IChecker extends Serializable {
    IWarPlayerToken owner();

    CheckerType type();
}
