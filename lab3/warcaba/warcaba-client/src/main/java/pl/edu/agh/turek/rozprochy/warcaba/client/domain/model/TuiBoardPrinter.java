package pl.edu.agh.turek.rozprochy.warcaba.client.domain.model;

import com.google.common.base.Optional;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IChecker;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IGameBoard;
import pl.edu.agh.turek.rozprochy.warcaba.api.domain.model.IWarPlayerToken;

/**
 * Author: Piotr Turek
 */
public class TuiBoardPrinter implements IBoardPrinter {
    private static final String MY_FIELD = "M";
    private static final String HIS_FIELD = "V";
    private static final String EMPTY_FIELD = "O";

    private IWarPlayerToken myToken;

    public TuiBoardPrinter(IWarPlayerToken myToken) {
        this.myToken = myToken;
    }

    @Override
    public void print(IGameBoard board) {
        displayLegend();
        System.out.println("Board:\n------------------------------");
        displayBoard(board);
        System.out.println("--------------------------------------");
    }

    private void displayBoard(IGameBoard board) {
        final int height = board.height();
        final int width = board.width();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                String field = EMPTY_FIELD;
                final Optional<IChecker> opt = board.checkerAt(i, j);
                if (opt.isPresent()) {
                    field = fieldByOwner(opt);
                }
                System.out.print(field);
            }
            System.out.println();
        }
    }

    private String fieldByOwner(Optional<IChecker> opt) {
        String field;
        final IChecker checker = opt.get();
        if (checker.owner().equals(myToken)) {
            field = MY_FIELD;
        } else {
            field = HIS_FIELD;
        }
        return field;
    }

    private void displayLegend() {
        System.out.println("Fields description:\n" +
                MY_FIELD + " - my checker\n" +
                HIS_FIELD + " - enemy's checker\n" +
                EMPTY_FIELD + " - empty field");
    }
}
