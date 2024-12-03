package battleships.ships;

import battleships.Board;
import battleships.Ship;

public abstract class SimpleShip extends Ship {


    private boolean horizontal;

    public SimpleShip(String name, String code, int squareCount) {
        super(name, code, squareCount);
    }

    public void rotate(){
        this.horizontal = !this.horizontal;
    }

    public int getWidth(){
        if(this.horizontal) {
            return this.squareCount;
        }else {
            return 1;
        }

    }

    public int getHeight(){
        if(!this.horizontal) {
            return this.squareCount;
        }else {
            return 1;
        }
    }

    public void addToBoard(final Board board){
        if (this.horizontal){
            for (int x = 0;x < this.squareCount;x++){
                board.getSquare(x + this.x,this.y).setShip(this);
            }
        }else{
            for (int y = 0;y < this.squareCount; y++){
                board.getSquare(this.x, y + this.y).setShip(this);
            }
        }
    }
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        if (this.horizontal) {
            for(int x = 0; x < this.squareCount; x++){
                builder.append("O");
            }
            builder.append("\n");
        }
        else {
            for (int y =0;y < this.squareCount;y++){
                builder.append("O\n");
            }
        }
        return builder.toString();
    }
}
