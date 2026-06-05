package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class Snake extends GameObject {

    private LinkedList<int[]> body;
    private Direction direction;
    private boolean alive;

    private static final int TILE_SIZE = 20;

    public Snake(int startX, int startY) {
        super(startX, startY);

        body = new LinkedList<>();
        body.add(new int[]{startX, startY});
        body.add(new int[]{startX - TILE_SIZE, startY});
        body.add(new int[]{startX - 2 * TILE_SIZE, startY});

        direction = Direction.RIGHT;
        alive = true;
    }

    public void move() {
        int[] head = body.getFirst();
        int newX = head[0];
        int newY = head[1];

        switch (direction) {
            case UP:    newY -= TILE_SIZE; break;
            case DOWN:  newY += TILE_SIZE; break;
            case LEFT:  newX -= TILE_SIZE; break;
            case RIGHT: newX += TILE_SIZE; break;
        }

        body.addFirst(new int[]{newX, newY});
        body.removeLast();
    }

    public void grow() {
        int[] tail = body.getLast();
        body.addLast(new int[]{tail[0], tail[1]});
    }

    public boolean hitWall(int panelWidth, int panelHeight) {
        int[] head = body.getFirst();
        return head[0] < 0 || head[0] >= panelWidth
            || head[1] < 0 || head[1] >= panelHeight;
    }

    public boolean hitSelf() {
        int[] head = body.getFirst();
        for (int i = 1; i < body.size(); i++) {
            if (body.get(i)[0] == head[0] && body.get(i)[1] == head[1]) {
                return true;
            }
        }
        return false;
    }

    public boolean ateFood(Food food) {
        int[] head = body.getFirst();
        return head[0] == food.getX() && head[1] == food.getY();
    }

    @Override
    public void draw(Graphics g) {
        for (int i = 0; i < body.size(); i++) {
            int[] segment = body.get(i);
            if (i == 0) {
                g.setColor(new Color(0, 180, 0));
            } else {
                g.setColor(new Color(50, 220, 50));
            }
            g.fillRect(segment[0], segment[1], TILE_SIZE, TILE_SIZE);
            g.setColor(Color.BLACK);
            g.drawRect(segment[0], segment[1], TILE_SIZE, TILE_SIZE);
        }
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getLength() {
        return body.size();
    }

    public LinkedList<int[]> getBody() {
        return body;
    }
}
