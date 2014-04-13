package pl.edu.agh.turek.rozprochy.warcaba.api.domain.setup.exceptions;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.exceptions.WarGameException;

/**
 * Author: Piotr Turek
 */
public class GameRequestException extends WarGameException {
    public GameRequestException(String message) {
        super(message);
    }
}
