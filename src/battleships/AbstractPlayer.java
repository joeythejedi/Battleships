package battleships;

public abstract class AbstractPlayer {
    private final String name;
    protected final Board board;
    protected AbstractPlayer opponent;

    public AbstractPlayer(String name, Board board) {
        this.name = name;
        this.board = board;
    }

    public String getName() {
        return this.name;
    }

    public Board getBoard() {
        return this.board;
    }

    public void setOpponent(AbstractPlayer other) {
        this.opponent = other;
    }
    public AbstractPlayer getOpponent() {
        return this.opponent;
    }

    public abstract boolean isHuman();

    public abstract void promptToTakeTurn(AbstractGame game);

    public abstract void displayResult(Outcome result);

    public abstract void quitGame();
}