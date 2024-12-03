package battleships.gui;

import battleships.AbstractGame;
import battleships.Board;
import battleships.Outcome;

public class GUIHumanPlayer extends GUIPlayer {

    public GUIHumanPlayer(final String name, final Board board) {
        super(name, board);
    }

    @Override
    public void promptToTakeTurn(final AbstractGame game) {
        this.gamePanel.prompt(this);
    }

    @Override
    public void displayResult(final Outcome result) {
        this.gamePanel.displayResult(this, result);
    }

    @Override
    public void quitGame() {
        this.gamePanel.playerQuit(this);
    }
    @Override
    public boolean isHuman() {
        return true;
    }
}