package pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay.command;

import java.io.Serializable;

/**
 * Author: Piotr Turek
 */
public interface IWarCommand extends Serializable {
    void execute();
}
