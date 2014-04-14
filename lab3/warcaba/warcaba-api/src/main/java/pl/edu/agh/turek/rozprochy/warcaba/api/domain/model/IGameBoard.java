package pl.edu.agh.turek.rozprochy.warcaba.api.domain.model;

import com.google.common.base.Optional;

import java.io.Serializable;

/**
 * Author: Piotr Turek
 */
public interface IGameBoard extends Serializable {
    int height();

    int width();

    Optional<IChecker> checkerAt(int i, int j);

    boolean removeCheckerAt(int i, int j);

    boolean addCheckerAt(int i, int j, IChecker checker);
}
