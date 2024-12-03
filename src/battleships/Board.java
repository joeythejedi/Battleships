package battleships;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {

    private final int height;
    private final int width;
    private List<Ship> ships = new ArrayList<>();
    private final Square[][] board;

    public Board(final int height, final int width) {
        this.height = height;
        this.width = width;

        this.board = new Square[height][width];
        for (int i = 0; i < this.board.length; i++) {
            Square[] row = this.board[i];
            for (int j = 0; j < row.length; j++) {
                row[j] = new Square(Terrain.SEA);
            }
        }

        setupLand();
    }

    private void setupLand() {
        for (int i = 0; i < 15; i++) {
            Random random = new Random();
            int random_number = random.nextInt(4);
            for (int j = 7+random_number; j < 15; j++) {
                this.board[i][j].setTerrain(Terrain.LAND);
            }
        }
    }


    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
    public void setup (Fleet fleet){
        for(Ship s: fleet.getShips()){
            placeShip(s);
        }
    }


    public void placeShip(Ship ship) {
        Random random = new Random();
        final int BREAK_THRESHOLD = 1000;

        final int rotations = random.nextInt(4);
        for (int i = 0; i < rotations; i++) {
            ship.rotate();
        }

        int breakCount = 0;
        boolean collision = true;
        while (collision) {
            if (breakCount > BREAK_THRESHOLD) {
                for(int i = 0;i<this.board.length;i++){
                    Square[] row = this.board[i];
                    for (int j = 0;j<row.length;j++){
                        row[j].setShip(null);
                    }
                }
                for (Ship s: this.ships){
                    s.setLocation(0,0);
                }
                this.ships.clear();
                throw new FailedToPlaceShipException();
            } else {

                ship.rotate();

                final int x = random.nextInt(this.width - ship.getWidth());
                final int y = random.nextInt(this.height - ship.getHeight());
                ship.setLocation(x, y);

                collision = false;
                for (final Ship s : this.ships) {
                    if (s.overlap(ship)) {
                        collision = true;
                        break;
                    }
                }

                if (!collision){
                    for (int i = 0; i < ship.getHeight(); i++) {
                        for (int j = 0; j < ship.getWidth(); j++) {
                            int cx = x + j;
                            int cy = y + i;
                            if (cx < 0 || cy < 0 || cx >= this.width || cy >= this.height) {
                                collision = true;
                                break;
                            }
                            Terrain terrain = this.board[cy][cx].getTerrain();
                            if (!ship.canBePlacedOn(terrain)) {
                                collision = true;
                                break;
                            }
                        }
                        if (collision) break;
                    }
                }
                breakCount++;
            }
        }
        ship.addToBoard(this);
        ships.add(ship);
    }

    public boolean inBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < this.width && y < this.height;
    }

    public Square getSquare(int x, int y) {
        return this.board[y][x];
    }


    public Outcome dropBomb(final int x, final int y) {
        Square square = getSquare(x, y);
        if (!square.getTried()) {
            square.setTried();
            Ship sunkShip = null;
            boolean gameWon = false;
            if (square.isHit()) {
                if (square.getShip().isSunk()) {
                    sunkShip = square.getShip();
                    gameWon = true;
                    for(Ship ship : this.ships) {
                        if (!ship.isSunk()) {
                            gameWon = false;
                            break;
                        }
                    }
                }
            }
            return new Outcome(x, y, square.isHit(), sunkShip, gameWon);
        } else {
            // this is a wasted turn - perhaps an exception would be a better idea?
            throw new IllegalStateException("Square already played!");
        }
    }

    public String[] toStringArray(final boolean showShips) {
        final String[] array = new String[this.height];
        for (int y = 0; y < this.height; y++) {
            final StringBuilder builder = new StringBuilder(this.width);
            for (int x = 0; x < this.width; x++) {
                Square square = getSquare(x, y);
                builder.append(square.getCodeCharacter(showShips));
            }
            array[y] = builder.toString();
        }
        return array;

    }

    @Override
    public String toString() {
        final String[] array = toStringArray(true);
        final StringBuilder builder = new StringBuilder();
        for (int y = 0; y < this.height; y++) {
            builder.append(array[y]);
            builder.append("\n");
        }
        return builder.toString();
    }

}
