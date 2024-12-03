package battleships.ships;

import battleships.Ship;
import battleships.Terrain;

public class Tank extends SimpleShip {

    public Tank() {
        super("tank", "T",1);
    }
    @Override
    public boolean canBePlacedOn(Terrain terrain) {
        return terrain == Terrain.LAND;
    }
}