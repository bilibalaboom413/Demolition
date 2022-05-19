package demolition;

public class Broken extends Space {
    
    private boolean isBroken;

    /**
     * Constructor of the Broken
     * @param x the x-coordinate of the broken
     * @param y the y-coordinate of the broken
     */
    public Broken(int x, int y) {
        super(x, y);
        isBroken = false;
    }

    /**
     * Change the isBroken to true
     */
    public void broke() {
        isBroken = true;
    }

    /**
     * Get the state of isBroken
     * @return whether is Broken
     */
    public boolean getBroken() {
        return isBroken;
    }

    /**
     * Reset the state of broken wall
     */
    public void reset() {
        isBroken = false;
    }
}
