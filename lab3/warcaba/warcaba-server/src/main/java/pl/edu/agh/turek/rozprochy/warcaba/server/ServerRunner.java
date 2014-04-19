package pl.edu.agh.turek.rozprochy.warcaba.server;

import org.apache.commons.cli.CommandLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.edu.agh.turek.rozprochy.warcaba.commons.runner.AbstractWarcabaRunner;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Map;

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
        final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        ctx.registerShutdownHook();
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
    protected Map<String, String> getAdditionalProperties(CommandLine commandLine, Map<String, String> propMap) {
        propMap.put("java.rmi.server.hostname", commandLine.getOptionValue("server_ip"));
        return propMap;
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
