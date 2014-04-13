package pl.edu.agh.turek.rozprochy.warcaba.client;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay.IWarGame;
import pl.edu.agh.turek.rozprochy.warcaba.client.flow.IGameRunner;
import pl.edu.agh.turek.rozprochy.warcaba.client.setup.IGameFactory;
import pl.edu.agh.turek.rozprochy.warcaba.commons.runner.AbstractWarcabaRunner;

import java.util.Map;
import java.util.Scanner;

/**
 * Author: Piotr Turek
 */
public class ClientRunner extends AbstractWarcabaRunner {

    private static final Logger LOG = LoggerFactory.getLogger(ClientRunner.class);

    public static void main(String[] args) {
        final ClientRunner runner = new ClientRunner();
        runner.run(args);
    }

    @Override
    protected void doRun() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Scanner scanner = new Scanner(System.in);
        do {
            runOneGame(ctx, scanner);
            LOG.info("Game has finished");
        } while (keepPlaying(scanner));
        LOG.info("Exiting now...");
    }

    private void runOneGame(ApplicationContext ctx, Scanner scanner) {
        IGameFactory gameFactory;
        System.out.println(">>> Type 'm' if you want to play with human players. Type 's' if you want to play with a bot");
        final String decision = scanner.next();
        gameFactory = resolveGameFactory(ctx, decision);
        final IWarGame game = gameFactory.create();
        final IGameRunner gameRunner = (IGameRunner) ctx.getBean("gameRunner");
        gameRunner.play(game);
    }

    private IGameFactory resolveGameFactory(ApplicationContext ctx, String decision) {
        IGameFactory gameFactory;
        if (decision.toLowerCase().equals("m")) {
            gameFactory = (IGameFactory) ctx.getBean("multiplayerGameFactory");
        } else {
            gameFactory = (IGameFactory) ctx.getBean("singleplayerGameFactory");
        }
        return gameFactory;
    }

    private boolean keepPlaying(Scanner scanner) {
        System.out.println(">>> Should I 'stay' or should I 'go'?");
        final String decision = scanner.next();
        return decision.toLowerCase().equals("stay");
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
        return new Option[]{nick};
    }

    @Override
    protected boolean validateAdditionalArguments(CommandLine commandLine) {
        return commandLine.hasOption("nick");
    }

    @Override
    protected Map<String, String> getAdditionalProperties(CommandLine commandLine, Map<String, String> propMap) {
        propMap.put("warcaba.client.nick", commandLine.getOptionValue("nick"));
        return propMap;
    }

}
