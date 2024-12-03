package battleships.cli;

import battleships.AbstractGame;
import battleships.AbstractPlayer;

public class CLIGame extends AbstractGame {
    public CLIGame(AbstractPlayer player1, AbstractPlayer player2) {
        super(player1, player2);
    }
    @Override
    public void startGame() {
        while(!this.gameOver) {
            nextTurn();
        }
    }
}
