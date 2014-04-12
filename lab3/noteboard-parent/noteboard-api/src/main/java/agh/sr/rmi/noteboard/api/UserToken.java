package agh.sr.rmi.noteboard.api;

import java.io.Serializable;

/**
 * Author: Piotr Turek
 */
public interface UserToken extends Serializable {
    String getUserName();
    Integer getId();
}
