package pl.edu.agh.turek.rozprochy.warcaba.server;

import org.apache.commons.cli.CommandLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.edu.agh.turek.rozprochy.warcaba.commons.runner.AbstractWarcabaRunner;

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
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Override
    protected String getRunnerName() {
        return "WarcabaServer";
    }

    @Override
    protected Logger getLogger() {
        return LOG;
    }

    @Override
    protected Map<String, String> getAdditionalProperties(CommandLine commandLine, Map<String, String> propMap) {
        propMap.put("java.security.policy", "/server.policy");
        return propMap;
    }
}
