package demolition;

import java.util.Random;

public class RedEnemy extends Enemy {

    private Random random;

    /**
     * Constructor of RedEnemy 
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public RedEnemy(int x, int y) {
        super(x, y);
        random = new Random(25);
        move = random.nextInt(4);
        initialize();
    }

    /**
     * Reset the red enemy position and move direction
     */
    public void reset() {
        x = initialX;
        y = initialY;
        move = random.nextInt(4);
        alive = true;
    }
    
    /**
     * Update the move of red enemy every second
     */
    public void move() {
        timer++;
        if (this.timer > SECONDS_BETWEEN_MOVES * App.FPS) {
            initialize();
            checkCollision();
            if (move == 0) {
                if (!canMoveUp) {
                    move = random.nextInt(4);
                } else {
                    y -= 32;
                }
            } else if (move == 1) {
                if (!canMoveRight) {
                    move = random.nextInt(4);
                } else {
                    x += 32;
                }
            } else if (move == 2) {
                if (!canMoveDown) {
                    move = random.nextInt(4);
                } else {
                    y += 32;
                }
            } else if (move == 3) {
                if (!canMoveLeft) {
                    move = random.nextInt(4);
                } else {
                    x -= 32;
                }
            }
            timer = 0;
        }
    }
}
