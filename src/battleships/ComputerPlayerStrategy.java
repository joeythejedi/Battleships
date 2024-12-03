package battleships;

public interface ComputerPlayerStrategy {

    /**
     * Choose and return the coordinate of a square on the opponent's board to attack.
     * The returned square should be a valid move (not already played).
     * Implementations could conceivably want to know the state of the computer's own
     * board, perhaps to use a more desperate approach if near to losing, but most
     * likely this would not be used.
     *
     * @param opponentBoard
     * @param ownBoard
     * @return
     */
    int[] chooseTarget(Board opponentBoard, Board ownBoard);
    /**
     * After returning a chosen target from the above method, this method will be called
     * with the Outcome object for that target.
     *
     * @param outcome
     */
    void resultOfMove(Outcome outcome);

}