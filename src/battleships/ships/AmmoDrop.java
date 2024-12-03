package battleships.ships;
import battleships.Terrain;
public class AmmoDrop extends TemplateShip{
    private static final int[][] pattern = new int[][]{
            new int[] { 1,1,1},
            new int[] { 0,1,0},
            new int[] { 1,1,1}
    };
    public AmmoDrop() {
        super("ammo drop", "H", pattern.clone());
    }
    @Override
    public boolean canBePlacedOn(Terrain terrain) {
        return terrain == Terrain.LAND;
    }
}