package pl.edu.agh.turek.rozprochy.warcaba.api.domain.exceptions;

/**
 * Author: Piotr Turek
 */
public class PlayerAlreadyExists extends WarGameException {
    public PlayerAlreadyExists(String message) {
        super(message);
    }
}
