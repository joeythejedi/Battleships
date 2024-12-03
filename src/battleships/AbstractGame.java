package battleships;

public abstract class AbstractGame {
    protected final AbstractPlayer player1;
    protected final AbstractPlayer player2;

    private final AbstractPlayer playerToGoFirst;
    protected AbstractPlayer currentPlayer = null;

    protected boolean gameOver = false;

    public AbstractGame(AbstractPlayer player1, AbstractPlayer player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.player1.setOpponent(player2);
        this.player2.setOpponent(player1);
        if (Math.random() >= 0.5) {
            this.currentPlayer = player2;
            this.playerToGoFirst = player1;
        } else {
            this.currentPlayer = player1;
            this.playerToGoFirst = player2;
        }
    }
    public AbstractPlayer playerWhoStarted() {
        return this.playerToGoFirst;
    }

    public AbstractPlayer getPlayer1() { return this.player1; }
    public AbstractPlayer getPlayer2() { return this.player2; }

    public abstract void startGame();

    public void nextTurn() {
        if (this.currentPlayer == this.player1) {
            this.currentPlayer = this.player2;
        } else {
            this.currentPlayer = this.player1;
        }
        this.currentPlayer.promptToTakeTurn(this);
        // this should result in a call back to either takeTurn or quitGame
    }
    /**
     * Drops a bomb at the given location for the current player, displays the results
     * for each player, delays a little for the results to be digested and then invokes
     * the next turn.
     *
     * @param x
     * @param y
     */
    public void takeTurn(int x, int y) {
        Outcome result = this.currentPlayer.getOpponent().getBoard().dropBomb(x, y);
        this.currentPlayer.displayResult(result);
        if (result.gameWon()) {
            this.gameOver = true;
        }
    }

    public void quitGame() {
        this.currentPlayer.quitGame();
        this.gameOver = true;
    }

}