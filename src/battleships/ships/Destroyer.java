package battleships.ships;

import battleships.Ship;
import battleships.Terrain;

public class Destroyer extends SimpleShip {

    public Destroyer() {
        super("destroyer", "D", 4);
    }

    @Override
    public boolean canBePlacedOn(Terrain terrain) {
        return terrain == Terrain.SEA;
    }
}
