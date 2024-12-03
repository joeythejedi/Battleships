package battleships.gui;

import battleships.AbstractPlayer;
import battleships.Board;
import battleships.BoardFactory;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {

    private final Board board;
    private final BoardButton[][] squares;

    private GamePanel gamePanel;
    private AbstractPlayer player;

    public BoardPanel(final AbstractPlayer player, GamePanel gamePanel) {
        super(new GridLayout(player.getBoard().getHeight(), player.getBoard().getWidth()));

        setBorder(BorderFactory.createTitledBorder(player.getName()));

        this.board = player.getBoard();
        this.gamePanel = gamePanel;
        this.player = player;
        this.squares = new BoardButton[board.getHeight()][board.getWidth()];
        for (int y = 0; y < this.squares.length; y++) {
            final int finalY = y;
            for (int x = 0; x < this.squares[y].length; x++) {
                final int finalX = x;
                final BoardButton button = new BoardButton(board.getSquare(x, y));
                button.addActionListener((ev)->handleButton(finalX,finalY));
                this.squares[y][x] = button;
                add(button);
            }
        }
    }
    private void handleButton(final int x, final int y) {
        if (!this.board.getSquare(x,y).getTried()) {
            this.gamePanel.bombDropped(this.player, x, y);
        }
    }
    public BoardButton getBoardSquareComponent(final int x, final int y) {
        return this.squares[y][x];
    }
    public void setShowShips(final boolean showShips) {
        for (int y = 0; y < this.squares.length; y++) {
            for (int x = 0; x < this.squares[y].length; x++) {
                this.squares[y][x].setShowShips(showShips);
            }
        }
    }

    @Override
    public void setEnabled(final boolean enabled) {
        for (int y = 0; y < this.squares.length; y++) {
            for (int x = 0; x < this.squares[y].length; x++) {
                this.squares[y][x].setEnabled(enabled);
            }
        }
    }
}