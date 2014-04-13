package pl.edu.agh.turek.rozprochy.warcaba.api.domain.exceptions;

/**
 * Author: Piotr Turek
 */
public class WarAuthenticationException extends WarGameException {
    private static final long serialVersionUID = 4156089645728003835L;

    public WarAuthenticationException(String message) {
        super(message);
    }
}
