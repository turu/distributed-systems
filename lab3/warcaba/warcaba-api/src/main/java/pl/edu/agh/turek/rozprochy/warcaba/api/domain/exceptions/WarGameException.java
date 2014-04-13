package pl.edu.agh.turek.rozprochy.warcaba.api.domain.exceptions;

/**
 * Author: Piotr Turek
 */
public class WarGameException extends Exception {
    private static final long serialVersionUID = 5454061715850183097L;

    public WarGameException(String message) {
        super(message);
    }
}
