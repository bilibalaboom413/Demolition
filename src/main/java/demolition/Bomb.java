package demolition;

public class Bomb extends GameObject{

    /**
     * Constructor of the bomb
     * @param x the x-coordinate of the bomb
     * @param y the y-coordinate of the bomb
     */
    public Bomb(int x, int y) {
        super(x, y);
    }

    public void tick() {}
    /**
     * Reset the bomb coordinate
     */
    public void reset() {
        x = -1000;
        y = -1000;
    }
}
