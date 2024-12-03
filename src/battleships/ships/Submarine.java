package battleships.ships;

import battleships.Ship;
import battleships.Terrain;

public class Submarine extends SimpleShip {

    public Submarine() {
        super("submarine", "S",3);
    }
    @Override
    public boolean canBePlacedOn(Terrain terrain) {
        return terrain == Terrain.SEA;
    }
}
