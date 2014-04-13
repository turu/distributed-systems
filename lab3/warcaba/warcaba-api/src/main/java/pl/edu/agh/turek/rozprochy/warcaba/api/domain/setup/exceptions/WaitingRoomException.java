package pl.edu.agh.turek.rozprochy.warcaba.api.domain.setup.exceptions;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.exceptions.WarAuthenticationException;

/**
 * Author: Piotr Turek
 */
public class WaitingRoomException extends Exception {
    private static final long serialVersionUID = 8830569711054458390L;

    public WaitingRoomException(String message) {
        super(message);
    }

    public WaitingRoomException(WarAuthenticationException e) {
        super(e);
    }

    public WaitingRoomException(Throwable cause) {
        super(cause);
    }
}
