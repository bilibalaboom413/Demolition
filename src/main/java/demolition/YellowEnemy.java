package demolition;

public class YellowEnemy extends Enemy {
    
    /**
     * Constructor of the YellowEnemy
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public YellowEnemy(int x, int y) {
        super(x, y);
    }

    /**
     * Reset the yellow enemy position and move direction
     */
    public void reset() {
        x = initialX;
        y = initialY;
        move = 0;
        alive = true;
    }

    /**
     * Update the move of yellow enemy every second
     */
    public void move() {
        timer++;
        if (this.timer > SECONDS_BETWEEN_MOVES * App.FPS) {
            initialize();
            checkCollision();
            if (move % 4 == 0) {
                if (!canMoveUp) {
                    move++;
                } else {
                    y -= 32;
                }
            } else if (move % 4 == 1) {
                if (!canMoveRight) {
                    move++;
                } else {
                    x += 32;
                }
            } else if (move % 4 == 2) {
                if (!canMoveDown) {
                    move++;
                } else {
                    y += 32;
                }
            } else if (move % 4 == 3) {
                if (!canMoveLeft) {
                    move++;
                } else {
                    x -= 32;
                }
            }
            timer = 0;
        }
    }
}
