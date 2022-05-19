package demolition;

public class PlayerIcon extends GameObject {

    private int lives;
    
    /**
     * Constructor of the PlayerIcon
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public PlayerIcon(int x, int y) {
        super(x, y);
    }

    public void tick() {}

    /**
     * Set the lives of the bombGuy
     * @param lives the lives of the bombGuy
     */
    public void setLives(int lives) {
        this.lives = lives; 
    }

    /**
     * Get the lives of the bombGuy
     * @return the lives of the bombGuy
     */
    public int getLives() {
        return lives;
    }

    /**
     * Decrementing the live of the bombGuy
     */
    public void reset() {
        this.lives--;
    }
}
