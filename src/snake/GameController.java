package snake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GameController extends KeyAdapter {

    private Snake snake;
    private List<Food> foods;
    private int score;
    private boolean gameOver;
    private boolean gameStarted;

    private static final int PANEL_WIDTH  = 400;
    private static final int PANEL_HEIGHT = 400;
    private static final int TILE_SIZE    = 20;
    private static final int FOOD_COUNT = 2;

    public GameController() {
        initGame();
    }

    public void initGame() {
        snake = new Snake(200, 200);
        foods  = new ArrayList<>();
        for(int i = 0; i < FOOD_COUNT; i++){
            Food f = new Food(0,0);
            f.respawn((PANEL_WIDTH/TILE_SIZE)-1,(PANEL_HEIGHT/TILE_SIZE)-1);
            foods.add(f);
        }
        score = 00;
        gameOver    = false;
        gameStarted = false;
    }

    public void update() {
        if (!gameStarted || gameOver) return;

        snake.move();

        if (snake.hitWall(PANEL_WIDTH, PANEL_HEIGHT)) {
            gameOver = true;
            snake.setAlive(false);
            return;
        }

        if (snake.hitSelf()) {
            gameOver = true;
            snake.setAlive(false);
            return;
        }
        for(Food food : foods) {
            if (snake.ateFood(food)) {
                snake.grow();
                score += 10;
                food.respawn(
                        (PANEL_WIDTH / TILE_SIZE) - 1,
                        (PANEL_HEIGHT / TILE_SIZE) - 1
                );
                break;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (!gameStarted) {
            if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN
             || key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
                gameStarted = true;
            }
        }

        if (key == KeyEvent.VK_UP && snake.getDirection() != Direction.DOWN) {
            snake.setDirection(Direction.UP);
        } else if (key == KeyEvent.VK_DOWN && snake.getDirection() != Direction.UP) {
            snake.setDirection(Direction.DOWN);
        } else if (key == KeyEvent.VK_LEFT && snake.getDirection() != Direction.RIGHT) {
            snake.setDirection(Direction.LEFT);
        } else if (key == KeyEvent.VK_RIGHT && snake.getDirection() != Direction.LEFT) {
            snake.setDirection(Direction.RIGHT);
        }

        if (key == KeyEvent.VK_R && gameOver) {
            initGame();
        }
    }

    public Snake getSnake() {
        return snake;
    }

    public List<Food> getFood() {
        return foods;
    }

    public int getScore() {
        return score;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }
}
