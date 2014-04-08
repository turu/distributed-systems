package agh.sr.rmi.noteboard.server.core;

import agh.sr.rmi.noteboard.api.UserToken;

/**
 * Author: Piotr Turek
 */
public class UserTokenImpl implements UserToken {
    private final String name;
    private final Integer id;
    private static int ID = 1;

    public UserTokenImpl(String name) {
        this.name = name;
        this.id = ID++;
    }

    @Override
    public String getUserName() {
        return name;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserTokenImpl userToken = (UserTokenImpl) o;

        if (!id.equals(userToken.id)) return false;
        if (!name.equals(userToken.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + id.hashCode();
        return result;
    }
}
