package snake;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

    public GameFrame() {
        GamePanel panel = new GamePanel();

        setTitle("Snake Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        add(panel);
        pack();
        setLocationRelativeTo(null);
    }
}
