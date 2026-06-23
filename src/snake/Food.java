package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Food extends GameObject {

    private Color color;

    private static final int TILE_SIZE = 20;

    public Food(int x, int y) {
        super(x, y);
        this.color = Color.RED;
    }

    public void respawn(int maxColumns, int maxRows) {
        Random random = new Random();
        this.x = random.nextInt(maxColumns) * TILE_SIZE;
        this.y = random.nextInt(maxRows) * TILE_SIZE;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, TILE_SIZE, TILE_SIZE);
    }
}
