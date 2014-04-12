package pl.edu.agh.turek.rozprochy.warcaba.api.runner;

import org.apache.commons.cli.*;
import org.slf4j.Logger;

import java.util.Map;

/**
 * Author: Piotr Turek
 */
public abstract class AbstractWarcabaRunner {
    protected abstract String getRunnerName();

    protected abstract Logger getLogger();

    protected abstract Option[] buildAdditionalOptions();

    protected abstract boolean validateArguments(CommandLine commandLine);

    protected abstract Map<String, String> getAdditionalProperties(CommandLine commandLine);

    protected abstract void doRun();

    public void run(String[] args) {
        prepareExecution(args);
        doRun();
    }

    private void prepareExecution(String[] args) {
        final Options options = buildOptions();
        final HelpFormatter helpFormatter = new HelpFormatter();
        final CommandLine commandLine = parseCommandLine(args, options, helpFormatter);
        if (!validateArguments(commandLine)) {
            helpFormatter.printHelp(getRunnerName(), options);
            System.exit(-1);
        }
        argsToSystemProperties(commandLine);
    }

    private CommandLine parseCommandLine(String[] args, Options options, HelpFormatter helpFormatter) {
        final CommandLineParser parser = new GnuParser();
        CommandLine commandLine = null;
        try {
            commandLine = parser.parse(options, args);
        } catch (ParseException e) {
            getLogger().warn("Unable to parse arguments. Cause: ", e.getMessage());
            helpFormatter.printHelp(getRunnerName(), options);
            System.exit(-1);
        }
        return commandLine;
    }

    private Options buildOptions() {
        final Option serverIP = OptionBuilder.withArgName("server_ip").withDescription("IP of server").hasArg()
                .create("server_ip");
        final Option serverPort = OptionBuilder.withArgName("server_port").withDescription("Port of a server")
                .hasArg().withType(Integer.class).create("server_port");

        final Options options = new Options();
        options.addOption(serverIP);
        options.addOption(serverPort);
        for (Option option : buildAdditionalOptions()) {
            options.addOption(option);
        }
        return options;
    }

    private void argsToSystemProperties(CommandLine commandLine) {
        final String serverIP = commandLine.getOptionValue("server_ip");
        final Integer serverPort = (Integer) commandLine.getOptionObject("server_port");
        System.setProperty("warcaba.server.ip", serverIP);
        System.setProperty("warcaba.server.port", String.valueOf(serverPort));
        System.setProperty("java.rmi.server.hostname", serverIP);
        final Map<String, String> additionalProperties = getAdditionalProperties(commandLine);
        for (Map.Entry<String, String> entry : additionalProperties.entrySet()) {
            System.setProperty(entry.getKey(), entry.getValue());
        }
    }
}
