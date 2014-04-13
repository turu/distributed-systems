package pl.edu.agh.turek.rozprochy.warcaba.client.communication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.remoting.RemoteLookupFailureException;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

/**
 * Author: Piotr Turek
 */
public class RmiResolver {
    private static final Logger LOG = LoggerFactory.getLogger(RmiResolver.class);

    private Registry rmiRegistry;
    private Integer maxRetries;

    public RmiResolver(Registry rmiRegistry, Integer maxRetries) {
        this.rmiRegistry = rmiRegistry;
        this.maxRetries = maxRetries;
    }

    public Object resolve(String name) throws RemoteLookupFailureException {
        Object resolved = null;
        int retryCount = 0;
        while (resolved == null && retryCount < maxRetries) {
            try {
                resolved = rmiRegistry.lookup(name);
            } catch (RemoteException e) {
                LOG.warn("Communication error, retryCount={}", retryCount, e);
            } catch (NotBoundException e) {
                LOG.error("No such object {} registered", name);
                throw new RemoteLookupFailureException(e.getLocalizedMessage());
            } finally {
                retryCount++;
            }
        }
        LOG.info("Object {} resolved after {} retries", name, retryCount);
        return resolved;
    }
}
