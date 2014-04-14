package pl.edu.agh.turek.rozprochy.warcaba.server.domain.setup.gamefactories;

import pl.edu.agh.turek.rozprochy.warcaba.api.domain.gameplay.IWarGame;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IPlayerPair;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarGameToken;
import pl.edu.agh.turek.rozprochy.warcaba.server.domain.IPairingManager;
import pl.edu.agh.turek.rozprochy.warcaba.server.domain.gameplay.IFinishRule;
import pl.edu.agh.turek.rozprochy.warcaba.server.domain.gameplay.SimpleCheckersGameBuilder;
import pl.edu.agh.turek.rozprochy.warcaba.server.domain.gameplay.command.validate.IValidationStrategy;
import pl.edu.agh.turek.rozprochy.warcaba.server.domain.model.IBoardFactory;
import pl.edu.agh.turek.rozprochy.warcaba.server.domain.setup.IGameFactory;

/**
 * Author: Piotr Turek
 */
public class SimpleCheckersGameFactory implements IGameFactory {
    private final IPairingManager pairingManager;
    private final IBoardFactory boardFactory;
    private final IFinishRule finishRule;
    private final IValidationStrategy validationStrategy;

    public SimpleCheckersGameFactory(IPairingManager pairingManager, IBoardFactory boardFactory,
                                     IFinishRule finishRule, IValidationStrategy validationStrategy) {
        this.pairingManager = pairingManager;
        this.boardFactory = boardFactory;
        this.finishRule = finishRule;
        this.validationStrategy = validationStrategy;
    }

    @Override
    public IWarGame createForToken(IWarGameToken gameToken) {
        final IPlayerPair players = pairingManager.pairForRequest(gameToken.gameRequest());
        return new SimpleCheckersGameBuilder()
                .setToken(gameToken)
                .setPlayers(players)
                .setBoard(boardFactory.create())
                .setFinishRule(finishRule)
                .setValidationStrategy(validationStrategy)
                .createSimpleCheckersGame();
    }
}
