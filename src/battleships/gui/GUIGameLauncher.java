package battleships.gui;

import battleships.*;

import javax.swing.*;

public class GUIGameLauncher {
    public static AbstractGame createGame() {
        Board[] boards = BoardFactory.getBigBoards();
        //Board[] boards = BoardFactory.getSmallBoards();
        //Board[] boards = BoardFactory.getTinyBoards();
        GUIPlayer player1 = null;
        GUIPlayer player2 = null;
        final ComputerPlayerStrategy strategy1 = new RandomStrategy();
        final ComputerPlayerStrategy strategy2 = new RandomStrategy();
        final GameType gameType = GameType.HUMAN_V_COMPUTER;
        switch(gameType) {
            case COMPUTER_V_COMPUTER:
                player1 = new GUIComputerPlayer("Computer1", boards[0], strategy1);
                player2 = new GUIComputerPlayer("Computer2", boards[1], strategy2);
                break;
            case HUMAN_V_COMPUTER:
                player1 = new GUIHumanPlayer("Human", boards[0]);
                player2 = new GUIComputerPlayer("Computer", boards[1], strategy2);
                break;
            case HUMAN_V_HUMAN:
                player1 = new GUIHumanPlayer("Human1", boards[0]);
                player2 = new GUIHumanPlayer("Human2", boards[1]);
                break;
            default:
                break;
        }
        return new GUIGame(player1, player2);

    }

    public static void launch(final String[] args) {
        final AbstractGame game = createGame();

        final GamePanel gp = new GamePanel(game);
        final JFrame f = new JFrame();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.add(gp);
        f.pack();
        f.setVisible(true);
    }
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(() -> launch(args));
    }
}
