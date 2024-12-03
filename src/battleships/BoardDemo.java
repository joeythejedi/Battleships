package battleships;

import battleships.ships.Battleship;
import battleships.ships.Destroyer;
import battleships.ships.Submarine;

public class BoardDemo {

    public static void main(String[] args) {

        Board b1 = new Board(20, 20);

        Fleet fleet = new Fleet(1,1,2, 2, 1, 4, 2, 1);
        b1.setup(fleet);

        System.out.println(b1);

        System.out.println("NOW BOMBING 2 ROWS");
        for(int y = 3;y<5;y++){
            for(int x = 0; x<b1.getWidth();x++){
                System.out.println("BOMBING SQUARE X = " + x + " Y = " + y);
                b1.dropBomb(x, y);
                System.out.println(b1);
                System.out.println();
            }
        }
//
//        //b1.setup();
//
//        System.out.println(b1);
//        System.out.println();
//
//        System.out.println("Bombing square x=2 y=0");
//        b1.dropBomb(2,0);
//        System.out.println(b1);
//        System.out.println();
//
//        System.out.println("bombing square x=2, y=1");
//        b1.dropBomb (2, 1);
//        System.out.println(b1);
//        System.out.println();
//
//        System.out.println("bombing square x=2, y=2");
//        b1.dropBomb (2, 2);
//        System.out.println(b1);
//        System.out.println();
//
//
//        System.out.println("showing the board with the ships hidden");
//        String[] b = b1.toStringArray(false);
//        for(String r:b) {
//            System.out.println(r);
//        }
//    }
    }
}
