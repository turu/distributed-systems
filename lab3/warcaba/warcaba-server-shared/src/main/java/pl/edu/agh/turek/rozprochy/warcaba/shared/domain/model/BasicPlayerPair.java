package pl.edu.agh.turek.rozprochy.warcaba.shared.domain.model;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IPlayerPair;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarPlayer;

/**
 * Author: Piotr Turek
 */
public class BasicPlayerPair implements IPlayerPair {
    private static final long serialVersionUID = 4592343048967194540L;

    private final IWarPlayer player;
    private final IWarPlayer enemy;

    public BasicPlayerPair(IWarPlayer player, IWarPlayer enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    @Override
    public IWarPlayer getPlayer() {
        return player;
    }

    @Override
    public IWarPlayer getEnemy() {
        return enemy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasicPlayerPair that = (BasicPlayerPair) o;

        if (!enemy.equals(that.enemy)) return false;
        if (!player.equals(that.player)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + enemy.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BasicPlayerPair{" +
                "player=" + player +
                ", enemy=" + enemy +
                '}';
    }
}
