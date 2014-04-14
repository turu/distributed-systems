package pl.edu.agh.turek.rozprochy.warcaba.client.domain.model;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay.command.*;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IGameBoard;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarGameToken;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Author: Piotr Turek
 */
public class TuiMoveFactory implements IMoveFactory {

    public static final String COMMAND_PATTERN = "\\s*[amAM]\\s+[0-9]+\\s+[0-9]+\\s+-?\\d+\\s+-?\\d+\\s*";

    @Override
    public IWarCommand create(IGameBoard board, IWarGameToken gameToken) {
        final String line = receiveCommandLine();

        final String[] tokens = line.split("\\s");
        final String kind = tokens[0].trim().toLowerCase();
        final int sourceX = Integer.parseInt(tokens[1].trim());
        final int sourceY = Integer.parseInt(tokens[2].trim());
        final int dirX = Integer.parseInt(tokens[3].trim());
        final int dirY = Integer.parseInt(tokens[4].trim());

        return doCreate(kind, sourceX, sourceY, dirX, dirY);
    }

    private String receiveCommandLine() {
        Scanner scanner = new Scanner(System.in);
        String line = "";
        do {
            try {
                displayHelp();
                line = scanner.nextLine();
            } catch (NoSuchElementException ignored) {
            }
        } while (!line.matches(COMMAND_PATTERN));
        return line;
    }

    private IWarCommand doCreate(String kind, int sourceX, int sourceY, int dirX, int dirY) {
        switch (kind) {
            case "a":
                return new AttackCommand(new Location(sourceX, sourceY), new Direction(dirX, dirY));
            case "m":
                return new MoveCommand(new Location(sourceX, sourceY), new Direction(dirX, dirY));
            default:
                return new AttackCommand(new Location(sourceX, sourceY), new Direction(dirX, dirY));
        }
    }

    private void displayHelp() {
        System.out.println("Enter your move in a form: <kind> <sourceX> <sourceY> <dirX> <dirY>\n" +
                "<kind> - 'a' (attack) | 'm' (move)\n" +
                "<sourceX|Y> - integer from 0 to width/height - 1 (note that field 0,0 is in upper left corner)\n" +
                "<dirX|Y> - positive or negative integer describing displacement vector (negative means left/up)");
    }
}
