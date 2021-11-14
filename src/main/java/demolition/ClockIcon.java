package demolition;

public class ClockIcon extends GameObject {

    private int time;

    /**
     * Constructor of the ClockIcon
     * @param x the x-coordinate of the clockIcon
     * @param y the y-coordinate of the clockIcon
     */
    public ClockIcon(int x, int y) {
        super(x, y);
    }

    /**
     * Decrementing the game time every 1 second
     */
    public void tick() {
        timer++;
        if (timer > Enemy.SECONDS_BETWEEN_MOVES * App.FPS) {
            time--;
            timer = 0;
        }
    }

    /**
     * Set the game time
     * @param time time to be set
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * Get the game time
     * @return the game time
     */
    public int getTime() {
        return time;
    }
    
    public void reset() {}
}
