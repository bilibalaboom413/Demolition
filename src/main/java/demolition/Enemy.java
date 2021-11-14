package demolition;

public abstract class Enemy extends GameObject {
    /**
     * The moving direction of the enemy
     * 0:up 1:right 2:down 3:left
     */
    protected int move;

    /**
     * Whether the enemy is alive
     */
    protected boolean alive;

    /**
     * Enemy moves every second
     */
    public static final int SECONDS_BETWEEN_MOVES = 1;

    /**
     * Constructor of the Enemy
     * @param x the x-coordinate of the enemy
     * @param y the y-coordinate of the enemy
     */
    public Enemy(int x, int y) {
        super(x, y);
        move = 0;
        timer = 0;
        alive = true;
        initialX = x;
        initialY = y;
    }

    /**
     * Get the move direction
     * @return move
     */
    public int getMove() {
        return move;
    }

    /**
     * Initialize the enemy
     */
    public void initialize() {
        canMoveDown = true;
        canMoveUp = true;
        canMoveLeft = true;
        canMoveRight = true;
    }

    /**
     * Change the enemy state every frame
     */
    public void tick() {
        if (alive) {
            move();
        }
    }

    /**
     * Remove the enemy if it's dead
     */
    public void die() {
        x = -1000;
        y = -1000;
        alive = false;
    }

    /**
     * The abstract method for moving
     */
    public abstract void move();  
}
