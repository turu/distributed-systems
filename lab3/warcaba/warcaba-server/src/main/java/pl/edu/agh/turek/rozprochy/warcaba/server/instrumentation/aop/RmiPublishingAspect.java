package pl.edu.agh.turek.rozprochy.warcaba.server.instrumentation.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay.IWarGame;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarGameToken;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

/**
 * Author: Piotr Turek
 */
@Component
@Aspect
public class RmiPublishingAspect {
    private static final Logger LOG = LoggerFactory.getLogger(RmiPublishingAspect.class);

    private final Registry registry;

    @Autowired
    public RmiPublishingAspect(@Qualifier("rmiRegistry") Registry registry) {
        this.registry = registry;
    }

    @AfterReturning(pointcut = "execution(* create*(..))",
            returning = "game")
    public void publishRmiGame(IWarGame game) throws RemoteException {
        if (game == null) {
            return;
        }
        IWarGameToken token = getWarGameToken(game);
        final String exportedName = token.id().toString();
        try {
            registry.bind(exportedName, game);
            LOG.info("Game {} exported under {} name", token, exportedName);
            LOG.trace("List of active remote objects: {}", registry.list());
        } catch (AlreadyBoundException e) {
            LOG.error("Game {} is published already! Aborting...", token);
        }
    }

    private IWarGameToken getWarGameToken(IWarGame game) throws RemoteException {
        try {
            return game.getToken();
        } catch (RemoteException e) {
            LOG.error("Remote communication error when accessing locally... shouldn't happen really");
            throw e;
        }
    }
}
