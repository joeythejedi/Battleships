package battleships.ships;

import battleships.Ship;
import battleships.Terrain;

public class Battleship extends SimpleShip {

    public Battleship() {
        super("battleship", "B", 5);
    }

    @Override
    public boolean canBePlacedOn(Terrain terrain) {
        return terrain == Terrain.SEA;
    }
}
