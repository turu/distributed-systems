package pl.edu.agh.turek.rozprochy.warcaba.client.flow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay.IWarGame;

import java.rmi.RemoteException;
import java.util.concurrent.TimeUnit;

/**
 * Author: Piotr Turek
 */
public class TuiGameRunner implements IGameRunner {
    private static final Logger LOG = LoggerFactory.getLogger(TuiGameRunner.class);

    @Override
    public void play(IWarGame game) {
        try {
            LOG.info("Game has started. Game info: {}", game.getToken());
            while (!game.isFinished()) {
                TimeUnit.SECONDS.sleep(10);
            }
        } catch (RemoteException e) {
            LOG.error("Communication error. Game could not be started", e);
            return;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
