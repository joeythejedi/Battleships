package battleships.gui;

import battleships.AbstractGame;
import battleships.Board;
import battleships.ComputerPlayerStrategy;
import battleships.Outcome;

public class GUIComputerPlayer extends GUIPlayer {

    private final ComputerPlayerStrategy strategy;

    public GUIComputerPlayer(final String name, final Board board, final ComputerPlayerStrategy strategy) {
        super(name, board);
        this.strategy = strategy;
    }

    @Override
    public void promptToTakeTurn(final AbstractGame game) {
        this.gamePanel.prompt(this);
        Utils.invokeLater(()->doTakeTurn(game), GUIGame.DELAY);
        // we want to delay so that human player gets a chance to see what is happening
    }

    private void doTakeTurn(final AbstractGame game) {
        final int[] move = this.strategy.chooseTarget(this.opponent.getBoard(), this.board);
        game.takeTurn(move[0], move[1]);
    }


    @Override
    public void displayResult(final Outcome result) {
        this.strategy.resultOfMove(result);
        this.gamePanel.displayResult(this, result);
    }

    @Override
    public boolean isHuman() {
        return false;
    }
}