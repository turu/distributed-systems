package pl.edu.agh.turek.rozprochy.warcaba.api.domain.exceptions;

/**
 * Author: Piotr Turek
 */
public class PlayerAlreadyExistsException extends WarGameException {
    public PlayerAlreadyExistsException(String message) {
        super(message);
    }
}
