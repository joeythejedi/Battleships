package battleships.gui;
import javax.swing.*;

public class Utils {

    public static void invokeLater(final Runnable runnable, final int delay) {
        // we want to delay so that human player gets a chance to see what is happening
        new Thread() {
            @Override
            public void run() {
                try { Thread.sleep(delay); } catch(final InterruptedException x) {}
                SwingUtilities.invokeLater(runnable);
            }
        }.start();
    }

}