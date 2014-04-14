package pl.edu.agh.turek.rozprochy.warcaba.server.domain.gameplay;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IGameBoard;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IPlayerPair;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarGameToken;
import pl.edu.agh.turek.rozprochy.warcaba.server.domain.gameplay.command.validate.IValidationStrategy;

public class SimpleCheckersGameBuilder {
    private IWarGameToken token;
    private IPlayerPair players;
    private IGameBoard board;
    private IFinishRule finishRule;
    private IValidationStrategy validationStrategy;

    public SimpleCheckersGameBuilder setToken(IWarGameToken token) {
        this.token = token;
        return this;
    }

    public SimpleCheckersGameBuilder setPlayers(IPlayerPair players) {
        this.players = players;
        return this;
    }

    public SimpleCheckersGameBuilder setBoard(IGameBoard board) {
        this.board = board;
        return this;
    }

    public SimpleCheckersGameBuilder setFinishRule(IFinishRule finishRule) {
        this.finishRule = finishRule;
        return this;
    }

    public SimpleCheckersGameBuilder setValidationStrategy(IValidationStrategy validationStrategy) {
        this.validationStrategy = validationStrategy;
        return this;
    }

    public SimpleCheckersGame createSimpleCheckersGame() {
        return new SimpleCheckersGame(token, players, board, finishRule, validationStrategy);
    }
}
