package pl.edu.agh.turek.rozprochy.warcaba.shared.domain.model.board;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IChecker;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.ICheckerType;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarPlayerToken;

/**
 * Author: Piotr Turek
 */
public class SimpleChecker implements IChecker {
    private static final long serialVersionUID = -4824663359054338657L;

    private IWarPlayerToken owner;
    private ICheckerType type;

    public SimpleChecker(IWarPlayerToken owner, ICheckerType type) {
        this.owner = owner;
        this.type = type;
    }

    @Override
    public IWarPlayerToken owner() {
        return owner;
    }

    @Override
    public ICheckerType type() {
        return type;
    }
}
