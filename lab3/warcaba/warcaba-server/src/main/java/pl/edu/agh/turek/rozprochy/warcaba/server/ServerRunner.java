package pl.edu.agh.turek.rozprochy.warcaba.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.edu.agh.turek.rozprochy.warcaba.api.runner.AbstractWarcabaRunner;

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

}
