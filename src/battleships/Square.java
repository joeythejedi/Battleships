package battleships;

public class Square {
    private boolean tried = false;
    private Ship ship;
    private Terrain terrain;  // New attribute for terrain type

    // Constructor
    public Square(Terrain terrain) {
        this.terrain = terrain;
    }

    public Terrain getTerrain() {
        return this.terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Ship getShip() {
        return this.ship;
    }

    public void setTried() {
        this.tried = true;
        if (this.ship != null){
            this.ship.incrementHitCount();
        }
    }

    public boolean getTried() {
        return this.tried;
    }

    public boolean isHit() {
        return this.tried && this.ship != null;
    }

    public boolean isMiss() {
        return this.tried && this.ship == null;
    }

    public String getCodeCharacter(boolean showShips) {
        if (this.tried) {
            if (this.isHit()) {
                return "*";
            } else if (this.isMiss()) {
                return "'";
            }
        } else {
            if (showShips && this.ship != null) {
                return this.ship.getCode();
            }
        }
        return "~";
    }
}