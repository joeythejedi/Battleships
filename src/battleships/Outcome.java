package battleships;

public class Outcome {
    private final int x;
    private final int y;
    private final boolean hit;
    private final Ship sunk;
    private final boolean gameWon;

    public Outcome(int x, int y, boolean hit, Ship sunk, boolean gameWon) {
        this.x = x;
        this.y = y;
        this.hit = hit;
        this.sunk = sunk;
        this.gameWon = gameWon;
    }

    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public boolean hit() { return this.hit; }

    /** Null if ship was not sunk by this bomb. */
    public Ship sunk() { return this.sunk; }

    public boolean gameWon() { return this.gameWon; }

}