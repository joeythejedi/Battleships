package battleships.cli;

import battleships.AbstractGame;
import battleships.Board;
import battleships.ComputerPlayerStrategy;
import battleships.Outcome;

public class CLIComputerPlayer extends CLIPlayer {

    private final ComputerPlayerStrategy strategy;

    public CLIComputerPlayer(String name, Board board, ComputerPlayerStrategy strategy) {
        super(name, board);
        this.strategy = strategy;
    }
    @Override
    public boolean isHuman() {
        return false;
    }

    @Override
    public void promptToTakeTurn(AbstractGame game) {
        super.promptToTakeTurn(game);
        int[] move = this.strategy.chooseTarget(this.opponent.getBoard(), this.board);
        System.out.println(getName() + " plays '" + (char)(97 + move[1]) + (char)(97 + move[0]) + "'.");
        game.takeTurn(move[0], move[1]);
    }

    @Override
    public void displayResult(Outcome result) {
        this.strategy.resultOfMove(result);
        super.displayResult(result);
    }
}
