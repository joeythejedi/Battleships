package battleships.gui;

import battleships.AbstractGame;
import battleships.AbstractPlayer;
import battleships.Outcome;
import battleships.Ship;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private AbstractGame game;

    private BoardPanel player1BoardPanel;
    private BoardPanel player2BoardPanel;

    private final JLabel messageLabel = new JLabel("---------- BATTLESHIPS ----------");
    private final JButton controlButton = new JButton("Start game");

    private enum State {WAITING_TO_START, RUNNING_GAME, GAME_OVER}

    private State state = State.WAITING_TO_START;

    public GamePanel(final AbstractGame game) {
        super(new BorderLayout());
        setGame(game);

        this.messageLabel.setFont(Font.decode("SANS_SERIF-BOLD-20"));
        this.messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(this.messageLabel, BorderLayout.NORTH);

        this.controlButton.addActionListener((ev) -> controlButtonPressed());

        final JPanel buttonPanel = new JPanel();
        buttonPanel.add(this.controlButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void setGame(final AbstractGame game) {
        this.game = game;

        if (this.player1BoardPanel != null) {
            remove(this.player1BoardPanel);
        }
        if (this.player2BoardPanel != null) {
            remove(this.player2BoardPanel);
        }

        ((GUIPlayer) this.game.getPlayer1()).setGamePanel(this);
        ((GUIPlayer) this.game.getPlayer2()).setGamePanel(this);
        this.player1BoardPanel = new BoardPanel(game.getPlayer1(), this);
        this.player2BoardPanel = new BoardPanel(game.getPlayer2(), this);

        if (this.game.getPlayer1().isHuman() == this.game.getPlayer2().isHuman()) {
            // two humans or two computers; show no ships
        } else {
            this.player1BoardPanel.setShowShips(this.game.getPlayer1().isHuman());
            this.player2BoardPanel.setShowShips(this.game.getPlayer2().isHuman());
        }
        this.player1BoardPanel.setEnabled(false);
        this.player2BoardPanel.setEnabled(false);
        add(this.player1BoardPanel, BorderLayout.WEST);
        add(this.player2BoardPanel, BorderLayout.EAST);
        this.messageLabel.setText(game.playerWhoStarted().getName() + " to go first");
        repaint();
    }

    private BoardPanel getBoardPanel(final AbstractPlayer player) {
        if (player == this.game.getPlayer1()) {
            return this.player1BoardPanel;
        } else {
            return this.player2BoardPanel;
        }
    }

    private void controlButtonPressed() {
        switch (this.state) {
            case GAME_OVER:
                setGame(GUIGameLauncher.createGame());
                this.state = State.WAITING_TO_START;
                this.controlButton.setText("Start game");
                break;
            case RUNNING_GAME:
                // give up button is pressed
                this.controlButton.setText("New game");
                this.state = State.GAME_OVER;
                this.game.quitGame();
                break;
            case WAITING_TO_START:
                // start game button is pressed
                this.controlButton.setText("Give up");
                this.controlButton.setEnabled(false);
                this.state = State.RUNNING_GAME;
                this.game.startGame();
                break;
        }
    }

    public void prompt(final AbstractPlayer player) {
        if (player == this.game.getPlayer1()) {
            this.messageLabel.setText(">>> " + player.getName() + " to bomb " + player.getOpponent().getName() + "! >>>");
            this.player1BoardPanel.setEnabled(false);
            this.player2BoardPanel.setEnabled(this.game.getPlayer1().isHuman());
        } else {
            this.messageLabel.setText("<<< " + player.getName() + " to bomb " + player.getOpponent().getName() + "! <<<");
            this.player1BoardPanel.setEnabled(this.game.getPlayer2().isHuman());
            this.player2BoardPanel.setEnabled(false);
        }
        this.controlButton.setEnabled(player.isHuman());
    }

    public void bombDropped(final AbstractPlayer victim, final int x, final int y) {
        this.controlButton.setEnabled(false);
        this.player1BoardPanel.setEnabled(false);
        this.player2BoardPanel.setEnabled(false);
        this.game.takeTurn(x, y); // calls back to display result
    }

    public void displayResult(final AbstractPlayer bomber, final Outcome result) {
        final BoardPanel boardPanel = getBoardPanel(bomber.getOpponent());
        final BoardButton b = boardPanel.getBoardSquareComponent(result.getX(), result.getY());
        if (result.hit()) {
            final Ship s = result.sunk();
            if (s == null) {
                this.messageLabel.setText("HIT!");
            } else {
                boardPanel.repaint(); // repaint whole board to color sunk ship
                if (result.gameWon()) {
                    this.messageLabel.setText("HIT and " + s.getName() + " destroyed and " + bomber.getName() + " wins!");
                    this.player1BoardPanel.setEnabled(false);
                    this.player2BoardPanel.setEnabled(false);
                    this.player1BoardPanel.setShowShips(true);
                    this.player2BoardPanel.setShowShips(true);
                    this.state = State.GAME_OVER;
                    this.controlButton.setText("New game");
                    this.controlButton.setEnabled(true);
                } else {
                    this.messageLabel.setText("HIT and " + s.getName() + " destroyed!");
                }
            }
        } else {
            this.messageLabel.setText("MISS!");
        }
        b.repaint(); // button repaints with current square status
    }

    public void playerQuit(final AbstractPlayer player) {
        this.messageLabel.setText(player.getName() + " quit.");
        this.player1BoardPanel.setEnabled(false);
        this.player2BoardPanel.setEnabled(false);
        this.player1BoardPanel.setShowShips(true);
        this.player2BoardPanel.setShowShips(true);
    }
}
