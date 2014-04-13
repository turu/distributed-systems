package pl.edu.agh.turek.rozprochy.warcaba.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.edu.agh.turek.rozprochy.warcaba.commons.runner.AbstractWarcabaRunner;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Author: Piotr Turek
 */
public class ServerRunner extends AbstractWarcabaRunner {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ServerRunner.class);

    public static void main(String[] args) {
        final ServerRunner serverRunner = new ServerRunner();
        serverRunner.run(args);
    }

    @Override
    protected void doRun() {
        eagerlyCreateRegistry();
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        LOG.info("Server is up and running. Listening for clients...");
    }

    private void eagerlyCreateRegistry() {
        final String port = System.getProperty("warcaba.server.port");
        try {
            LocateRegistry.createRegistry(Integer.parseInt(port));
            LOG.info("RMI registry on port {} has been successfully created.", port);
        } catch (RemoteException e) {
            LOG.error("Couldn't create rmi registry. Exiting now...", e);
        }
    }

    @Override
    protected String getRunnerName() {
        return "WarcabaServer";
    }

    @Override
    protected Logger getLogger() {
        return LOG;
    }

}
