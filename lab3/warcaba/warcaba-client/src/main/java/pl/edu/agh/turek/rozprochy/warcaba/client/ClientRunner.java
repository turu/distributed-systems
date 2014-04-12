package pl.edu.agh.turek.rozprochy.warcaba.client;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.edu.agh.turek.rozprochy.warcaba.commons.runner.AbstractWarcabaRunner;

import java.util.Map;

/**
 * Author: Piotr Turek
 */
public class ClientRunner extends AbstractWarcabaRunner {

    private static final Logger LOG =  LoggerFactory.getLogger(ClientRunner.class);

    public static void main(String[] args) {
        final ClientRunner runner = new ClientRunner();
        runner.run(args);
    }

    @Override
    protected void doRun() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Override
    protected String getRunnerName() {
        return "WarcabaClient";
    }

    @Override
    protected Logger getLogger() {
        return LOG;
    }

    @Override
    protected Option[] buildAdditionalOptions() {
        final Option nick = OptionBuilder.withArgName("nick").withDescription("Your name").hasArg()
                .create("nick");
        return new Option[] {nick};
    }

    @Override
    protected boolean validateAdditionalArguments(CommandLine commandLine) {
        return commandLine.hasOption("nick");
    }

    @Override
    protected Map<String, String> getAdditionalProperties(CommandLine commandLine, Map<String, String> propMap) {
        propMap.put("warcaba.client.nick", commandLine.getOptionValue("nick"));
        propMap.put("java.security.policy", "/client.policy");
        return propMap;
    }

}
