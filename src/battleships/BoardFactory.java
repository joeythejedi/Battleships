package battleships;

public class BoardFactory {
    public static Board getBigBoard() {
        Fleet fleet = new Fleet(1,2,3,1,1, 4,1,1);
        Board board = new Board(15,15);
        while(true) {
            try {
                board.setup(fleet);
                return board;
            } catch(FailedToPlaceShipException x) {
                System.err.println("FailedToPlaceShipException");
            }
        }
    }
    public static Board[] getBigBoards() {
        return new Board[] { BoardFactory.getBigBoard(), BoardFactory.getBigBoard() };
    }
    public static Board getSmallBoard() {
        Fleet fleet = new Fleet(1,1,0,1,1, 1, 1, 0);
        Board board = new Board(10,10);
        while(true) {
            try {
                board.setup(fleet);
                return board;
            } catch(FailedToPlaceShipException x) {
                System.err.println("FailedToPlaceShipException");
            }
        }
    }
    public static Board[] getSmallBoards() {
        return new Board[] { getSmallBoard(), getSmallBoard() };
    }
    public static Board getTinyBoard() {
        Fleet fleet = new Fleet(0,0,0,1,0, 2, 0, 0);
        Board board = new Board(5,5);
        while(true) {
            try {
                board.setup(fleet);
                return board;
            } catch(FailedToPlaceShipException x) {
                System.err.println("FailedToPlaceShipException");
            }
        }
    }
    public static Board[] getTinyBoards() {
        return new Board[] { getTinyBoard(), getTinyBoard() };
    }
}