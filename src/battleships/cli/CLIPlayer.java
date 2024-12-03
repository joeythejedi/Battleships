package battleships.cli;

import battleships.*;

public abstract class CLIPlayer extends AbstractPlayer {
    public CLIPlayer(String name, Board board) {
        super(name, board);
    }
    /**
     * Only prints an initial prompt; subclasses must override this method
     * and invoke this version and then perform the turn-taking process.
     */
    @Override
    public void promptToTakeTurn(AbstractGame game) {
        System.out.println();
        System.out.println(getName() + "'s turn");
        displayBoards(false);
        System.out.println( getName() + " enter your target (row-column):");
    }

    @Override
    public void displayResult(Outcome result) {
        if (result.hit()) {
            System.out.print("****** HIT");
            Ship sunk = result.sunk();
            if (sunk != null) {
                System.out.print(" AND " + sunk.getName() + " DESTROYED");
                if (result.gameWon()) {
                    System.out.print(" AND WON THE GAME!");
                }
            }
            System.out.println(" ******");
            if (result.gameWon()) {
                displayBoards(true); // display boards with all ships showing
            }
        } else {
            System.out.println("------ MISS ------");
        }
        // short pause to help follow the game!
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
    }
    protected void displayBoards(boolean allShips) {

        boolean hideAllShips = this.opponent.isHuman();

        final String[] y = this.board.toStringArray(!hideAllShips || allShips);
        final String[] o = this.opponent.getBoard().toStringArray(allShips);
        final int leftColumn = Math.max(y[0].length() + 6, getName().length() + 4);

        final StringBuilder heading = new StringBuilder(getName());
        while(heading.length() < leftColumn - 2) { heading.append(" "); }
        heading.append(this.opponent.getName());
        System.out.println(heading.toString());

        final StringBuilder letters = new StringBuilder("  ");
        for(int i = 0; i < y[0].length(); i++) {
            letters.append((char) (i+97));
        }
        while(letters.length() < leftColumn) { letters.append(" "); }
        for(int i = 0; i < y[0].length(); i++) {
            letters.append((char) (i+97));
        }
        System.out.println(letters);

        for(int i = 0; i < y.length; i++) {
            StringBuilder line = new StringBuilder();
            line.append(Character.toString((char) (i+97)));
            line.append(" ");
            line.append(y[i]);
            while(line.length() < leftColumn) { line.append(" "); }
            line.append(o[i]);
            System.out.println(line.toString());
        }
    }

    @Override
    public void quitGame() {
        System.out.println("QUITTER!");
        displayBoards(true); // display boards with all ships showing
    }
}
