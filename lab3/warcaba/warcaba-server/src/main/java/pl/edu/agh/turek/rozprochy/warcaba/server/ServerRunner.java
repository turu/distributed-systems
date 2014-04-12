package pl.edu.agh.turek.rozprochy.warcaba.server;

import org.apache.commons.cli.*;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Author: Piotr Turek
 */
public class ServerRunner {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ServerRunner.class);

    public static void main(String[] args) {
        prepareExecution(args);
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    private static void prepareExecution(String[] args) {
        final Options options = buildOptions();
        final HelpFormatter helpFormatter = new HelpFormatter();
        final CommandLine commandLine = parseCommandLine(args, options, helpFormatter);
        if (!validateArguments(commandLine)) {
            helpFormatter.printHelp("WarcabaServer", options);
            System.exit(-1);
        }
        argsToSystemProperties(commandLine);
    }

    private static CommandLine parseCommandLine(String[] args, Options options, HelpFormatter helpFormatter) {
        final CommandLineParser parser = new GnuParser();
        CommandLine commandLine = null;
        try {
            commandLine = parser.parse(options, args);
        } catch (ParseException e) {
            LOG.warn("Unable to parse arguments. Cause: ", e.getMessage());
            helpFormatter.printHelp("WarcabaServer", options);
            System.exit(-1);
        }
        return commandLine;
    }

    private static Options buildOptions() {
        final Option serverIP = OptionBuilder.withArgName("server_ip").withDescription("IP of server").hasArg()
                .create("server_ip");
        final Option serverPort = OptionBuilder.withArgName("server_port").withDescription("Port of a server")
                .hasArg().withType(Integer.class).create("server_port");

        final Options options = new Options();
        options.addOption(serverIP);
        options.addOption(serverPort);
        return options;
    }

    private static boolean validateArguments(CommandLine commandLine) {
        return commandLine.hasOption("server_ip") && commandLine.hasOption("server_port");
    }

    private static void argsToSystemProperties(CommandLine commandLine) {
        final String serverIP = commandLine.getOptionValue("server_ip");
        final Integer serverPort = (Integer) commandLine.getOptionObject("server_port");
        System.setProperty("warcaba.server.ip", serverIP);
        System.setProperty("warcaba.server.port", String.valueOf(serverPort));
        System.setProperty("java.rmi.server.hostname", serverIP);
    }
}
