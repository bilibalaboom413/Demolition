package demolition;

import processing.core.PImage;
import processing.core.PApplet;

public abstract class GameObject {
    /**
     * The x-coordinate of the game object
     */
    protected int x;
    
    /**
     * The y-coordinate of the game object
     */
    protected int y;

    /**
     * The time for changing the state
     */
    protected int time;

    /**
     * The timer for changing the state
     */
    protected int timer;

    /**
     * Whether game object can move left
     */
    protected boolean canMoveLeft;

    /**
     * Whether game object can move up
     */
    protected boolean canMoveUp;

    /**
     * Whether game object can move right
     */
    protected boolean canMoveRight;

    /**
     * Whether game object can move down
     */
    protected boolean canMoveDown;

    /**
     * The initial x-coordinate
     */
    protected int initialX;

    /**
     * The initial y-coordinate
     */
    protected int initialY;

    private PImage sprite;

    /**
     * Constructor of the game object
     * @param x the x-coordinate of the game object
     * @param y the y-coordinate of the game object
     */
    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
        canMoveLeft = false;
        canMoveUp = false;
        canMoveRight = false;
        canMoveDown = false;
        timer = 0;
        initialX = x;
        initialY = y;
    }

    /**
     * Set the game object whether can move left
     * @param bool whether can move left
     */
    public void setLeft(boolean bool) {
        canMoveLeft = bool;
    }

    /**
     * Set the game object whether can move right
     * @param bool whether can move right
     */
    public void setRight(boolean bool) {
        canMoveRight = bool;
    }

    /**
     * Set the game object whether can move up
     * @param bool whether can move up
     */
    public void setUp(boolean bool) {
        canMoveUp = bool;
    }

    /**
     * Set the game object whether can move down
     * @param bool whether can move down
     */
    public void setDown(boolean bool) {
        canMoveDown = bool;
    }

    /**
     * Set the initial position of the game object
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public void setInitial(int x, int y) {
        this.initialX = x;
        this.initialY = y;
    }

    /**
     * Get the initial x-coordinate
     * @return the initial x-coordinate
     */
    public int getInitialX() {
        return this.initialX;
    }

    /**
     * Get the initial y-coordinate
     * @return the initial y-coordinate
     */
    public int getInitialY() {
        return this.initialX;
    }

    /**
     * Get whether the game object can move left
     * @return whether the game object can move left
     */
    public boolean getLeft() {
        return canMoveLeft;
    }

    /**
     * Get whether the game object can move right
     * @return whether the game object can move right
     */
    public boolean getRight() {
        return canMoveRight;
    }

    /**
     * Get whether the game object can move up
     * @return whether the game object can move up
     */
    public boolean getUp() {
        return canMoveUp;
    }

    /**
     * Get whether the game object can move down
     * @return whether the game object can move down
     */
    public boolean getDown() {
        return canMoveDown;
    }

    /**
     * Set the sprite of the game object
     * @param sprite the sprite of the game object
     */
    public void setSprite(PImage sprite) {
        this.sprite = sprite;
    }

    /**
     * Update the game object every frame
     */
    public abstract void tick();

    /**
     * Reset the game object
     */
    public abstract void reset();

    /**
     * Draw the game object
     * @param app the app to be drawn
     */
    public void draw(PApplet app) {
        app.image(sprite, x, y);
    }

    /**
     * Set the coordinate
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public void setCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
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
     * Remove the game object
     */
    public void remove() {
        x = -1000;
        y = -1000;
    }

    /**
     * check whether character collide with wall
     */
    public void checkCollision() {
        int CharacterX = x;
        int CharacterY = y + 11;
        int pixel = 32;
        for (SolidWall solid : App.getLevel().getSolidWall()) {
            int solidX = solid.getX();
            int solidY = solid.getY();
            if (CharacterX == solidX + pixel && CharacterY == solidY) {
                canMoveLeft = false;
            } else if (CharacterX + pixel == solidX && CharacterY == solidY) {
                canMoveRight = false;
            } else if (CharacterX == solidX && CharacterY == solidY + pixel) {
                canMoveUp = false;
            } else if (CharacterX == solidX && CharacterY + pixel == solidY) {
                canMoveDown = false;
            }
        }

        for (Broken broken : App.getLevel().getBroken()) {
            int brokenX = broken.getX();
            int brokenY = broken.getY();
            if (CharacterX == brokenX + pixel && CharacterY == brokenY) {
                if (broken.getBroken()) {
                    canMoveLeft = true;
                } else {
                    canMoveLeft = false;
                }
            } else if (CharacterX + pixel == brokenX && CharacterY == brokenY) {
                if (broken.getBroken()) {
                    canMoveRight = true;
                } else {
                    canMoveRight = false;
                }
            } else if (CharacterX == brokenX && CharacterY == brokenY + pixel) {
                if (broken.getBroken()) {
                    canMoveUp = true;
                } else {
                    canMoveUp = false;
                }
            } else if (CharacterX == brokenX && CharacterY + pixel == brokenY) {
                if (broken.getBroken()) {
                    canMoveDown = true;
                } else {
                    canMoveDown = false;
                }
            }
        }
    }
}
