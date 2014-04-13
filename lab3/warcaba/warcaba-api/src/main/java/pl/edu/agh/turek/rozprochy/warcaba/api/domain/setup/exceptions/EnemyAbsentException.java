package pl.edu.agh.turek.rozprochy.warcaba.api.domain.setup.exceptions;

/**
 * Author: Piotr Turek
 */
public class EnemyAbsentException extends WaitingRoomException {
    private static final long serialVersionUID = -1899296080624514047L;

    public EnemyAbsentException(Throwable cause) {
        super(cause);
    }

    public EnemyAbsentException(String s) {
        super(s);
    }
}
