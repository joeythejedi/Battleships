package battleships.cli;

import battleships.AbstractGame;
import battleships.Board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CLIHumanPlayer extends CLIPlayer {
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public CLIHumanPlayer(String name, Board board) {
        super(name, board);
    }

    @Override
    public boolean isHuman() {
        return true;
    }
    @Override
    public void promptToTakeTurn(AbstractGame game) {
        super.promptToTakeTurn(game);
        try {
            final Board opponentBoard = this.opponent.getBoard();
            while(true) {
                final String line = this.reader.readLine().trim();
                if (line.equalsIgnoreCase("quit")) {
                    game.quitGame();
                    break;
                } else {
                    try {
                        if (line.length() != 2) {
                            throw new RuntimeException("Bad input: enter just two characters such as 'ab'.");
                        }
                        final int y = line.charAt(0) - 97;
                        final int x = line.charAt(1) - 97;
                        if (opponentBoard.inBounds(x, y)) {
                            if (!opponentBoard.getSquare(x, y).getTried()) {
                                game.takeTurn(x, y); // calls back to displayResult
                            } else {
                                throw new RuntimeException("You already played '" + line + "'!");
                            }
                        } else {
                            throw new RuntimeException("'" + line + "' is not on the board!");
                        }
                        return;
                    } catch(final Exception x) {
                        System.out.println(x.getMessage());
                        System.out.println( "Enter yx where a <= y <= " + Character.toString((char) (opponentBoard.getHeight() + 96)) +
                                " and a <= x <= " + Character.toString((char) (opponentBoard.getWidth() + 96))  + " or enter 'quit' to quit.");
                    }
                }
            }
        } catch(final IOException x) {
            throw new RuntimeException(x);
        }
    }
}