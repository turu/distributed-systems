package pl.edu.agh.turek.rozprochy.warcaba.server.domain.gameplay;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay.IWarGame;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarPlayer;

/**
 * Author: Piotr Turek
 */
public interface IFinishRule {
    boolean isGameFinished(IWarGame game);

    IWarPlayer getVictor(IWarGame game);

    IWarPlayer getLooser(IWarGame game);
}
