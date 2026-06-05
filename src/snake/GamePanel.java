package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel {

    private static final int WIDTH     = 400;
    private static final int HEIGHT    = 400;
    private static final int TILE_SIZE = 20;
    private static final int DELAY     = 150;

    private GameController controller;
    private Timer timer;

    public GamePanel() {
        controller = new GameController();

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);

        addKeyListener(controller);

        timer = new Timer(DELAY, e -> {
            controller.update();
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawGrid(g);

        controller.getSnake().draw(g);
        controller.getFood().draw(g);

        drawScore(g);

        if (!controller.isGameStarted()) {
            drawStartScreen(g);
        }

        if (controller.isGameOver()) {
            drawGameOver(g);
        }
    }

    private void drawGrid(Graphics g) {
        g.setColor(new Color(30, 30, 30));
        for (int x = 0; x < WIDTH; x += TILE_SIZE) {
            g.drawLine(x, 0, x, HEIGHT);
        }
        for (int y = 0; y < HEIGHT; y += TILE_SIZE) {
            g.drawLine(0, y, WIDTH, y);
        }
    }

    private void drawScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("Score: " + controller.getScore(), 10, 20);
    }

    private void drawStartScreen(Graphics g) {
        g.setColor(new Color(255, 255, 255, 200));
        g.setFont(new Font("Arial", Font.BOLD, 18));
        String msg = "Press an Arrow Key to Start";
        int msgWidth = g.getFontMetrics().stringWidth(msg);
        g.drawString(msg, (WIDTH - msgWidth) / 2, HEIGHT / 2);
    }

    private void drawGameOver(Graphics g) {
        g.setColor(new Color(0, 0, 0, 160));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        String overText = "GAME OVER";
        int overWidth = g.getFontMetrics().stringWidth(overText);
        g.drawString(overText, (WIDTH - overWidth) / 2, HEIGHT / 2 - 30);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        String scoreText = "Score: " + controller.getScore();
        int scoreWidth = g.getFontMetrics().stringWidth(scoreText);
        g.drawString(scoreText, (WIDTH - scoreWidth) / 2, HEIGHT / 2 + 10);

        g.setFont(new Font("Arial", Font.PLAIN, 16));
        String restartText = "Press R to Restart";
        int restartWidth = g.getFontMetrics().stringWidth(restartText);
        g.drawString(restartText, (WIDTH - restartWidth) / 2, HEIGHT / 2 + 45);
    }
}
