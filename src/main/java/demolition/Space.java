package demolition;

import processing.core.PImage;
import processing.core.PApplet;

public class Space {
    /**
     * The x-coordinate
     */
    protected int x;
    
    /**
     * The y-coordinate
     */
    protected int y;
    
    private PImage sprite;
    
    /**
     * Constructor of the Space
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public Space(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Set the sprite of space
     * @param sprite the sprite
     */
    public void setSprite(PImage sprite) {
        this.sprite = sprite;
    }

    /**
     * Draw the sprite 
     * @param app the app to be drawn
     */
    public void draw(PApplet app) {
        app.image(sprite, x, y);
    }

    /**
     * Get the x-coordinate
     * @return the x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Get the y-coordinate
     * @return the y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Remove the space
     */
    public void remove() {
        x = -1000;
        y = -1000;
    }
    
}
