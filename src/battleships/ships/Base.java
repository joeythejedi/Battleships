package battleships.ships;
import battleships.Terrain;
public class Base extends TemplateShip{
    private static final int[][] pattern = new int[][]{
            new int[] { 1,1},
            new int[] { 1,1}
    };
    public Base() {
        super("base", "B", pattern.clone());
    }
    @Override
    public boolean canBePlacedOn(Terrain terrain) {
        return terrain == Terrain.LAND;
    }
}