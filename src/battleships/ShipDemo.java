package battleships;

import battleships.ships.*;

public class ShipDemo {
    public static void main(String[] args) {
        //   Ship s = new SimpleShip("battleship","B",5);
        Ship s = new Tank();

        System.out.println(s);
        s.rotate();
        System.out.println(s);
        s.rotate();
        System.out.println(s);
        s.rotate();
        System.out.println(s);
    }
}
