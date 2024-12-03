package battleships;

import battleships.ships.*;

import java.util.ArrayList;
import java.util.List;

public class Fleet {

    private final List<Ship> ships= new ArrayList<>();

    public Fleet (int battleships, int destroyers, int submarines, int aeroplanes, int aircraftCarriers, int tanks, int bases, int ammoDrops){

        for (int i = 0; i < battleships; i++) {
            this.ships.add(new Battleship());
        }
        for (int i = 0; i < submarines; i++) {
            this.ships.add(new Submarine());
        }
        for (int i = 0; i < destroyers; i++) {
            this.ships.add(new Destroyer());
        }
        for (int i = 0; i < aeroplanes; i++) {
            this.ships.add(new Aeroplane());
        }
        for (int i = 0; i < aircraftCarriers; i++) {
            this.ships.add(new AircraftCarrier());
        }
        for (int i = 0; i < bases; i++) {
            this.ships.add(new Base());
        }
        for (int i = 0; i < tanks; i++) {
            this.ships.add(new Tank());
        }
        for (int i = 0; i < ammoDrops; i++) {
            this.ships.add(new AmmoDrop());
        }
    }

    public List<Ship> getShips(){
        return this.ships;
    }


}
