package battleships.gui;

import battleships.Square;
import battleships.Terrain;

import javax.swing.*;
import java.awt.*;

public class BoardButton extends JButton {

    private Square square;
    private boolean showShips;

    public BoardButton(final Square square) {
        this.square = square;
        setPreferredSize(new Dimension(16, 16));
    }

    public void setShowShips(boolean showShips) {
        this.showShips = showShips;
    }

    @Override
    public void paintComponent(Graphics g) {
        g = g.create();

        if (this.square.getTerrain() == Terrain.LAND) {
            g.setColor(new Color(255, 160, 80));
        } else {
            g.setColor(Color.BLUE);
        }
        g.fillRect(0, 0, getWidth(), getHeight());

        // Then draw ships or bomb hits
        if (this.square.getTried()) {
            if (this.square.isHit()) {
                // hit ship
                g.setColor(Color.DARK_GRAY);
                g.fillRect(0, 0, getWidth(), getHeight());
                if (this.square.getShip().isSunk()) {
                    g.setColor(new Color(192, 0, 0)); // Dark Red for sunk ships
                } else {
                    g.setColor(Color.ORANGE);
                }
                g.fillOval(4, 4, getWidth() - 8, getHeight() - 8);
            } else if (this.square.isMiss()) {
                // sea miss
                g.setColor(Color.WHITE);
                g.drawOval(4, 4, getWidth() - 8, getHeight() - 8);
            }
        } else {
            if (this.showShips && (this.square.getShip() != null)) {
                g.setColor(Color.DARK_GRAY);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        }
    }
}
