package demolition;

public class BombGuy extends GameObject {

    private boolean moveLeft;
    private boolean moveUp;
    private boolean moveRight;
    private boolean moveDown;
    private boolean horizontal;
    private boolean release;
    private boolean move;
    private boolean space;
    private boolean canBomb; 

    /**
     * Constructor of a bombGuy
     * @param x the x-coordinate of the bombGuy
     * @param y the y-coordinate of the bombGuy
     */
    public BombGuy(int x, int y) {
        super(x, y);
        moveLeft = false;
        moveUp = false;
        moveRight = false;
        moveDown = true;
        horizontal = false;
        release = true;
        move = false;
        space = false;
        canBomb = true;
    }

    /**
     * Reset the bombGuy position
     */
    public void reset() {
        moveDown = true;
        this.setCoordinate(initialX, initialY);
    }

    /**
     * Check whether bombGuy moves left
     * @return moveLeft
     */
    public boolean getMoveLeft() {
        return moveLeft;
    }

    /**
     * Check whether bombGuy moves right
     * @return moveRight
     */
    public boolean getMoveRight() {
        return moveRight;
    }

    /**
     * Check whether bombGuy moves up
     * @return moveUp
     */
    public boolean getMoveUp() {
        return moveUp;
    }

    /**
     * Check whether bombGuy moves down
     * @return moveDown
     */
    public boolean getMoveDown() {
        return moveDown;
    }

    /**
     * Check whether player presses space
     * @return space
     */
    public boolean getSpace() {
        return space;
    }

    /**
     * Check whether bombGuy can bomb
     * @return canBomb
     */
    public boolean getCanBomb() {
        return canBomb;
    }
    
    /**
     * Get whether the key has been released
     * @return whether the key has been released
     */
    public boolean getRelease() {
        return release;
    }

    /**
     * Reset the space
     * @param space the state of the space
     */
    public void setSpace(boolean space) {
        this.space = space;
    }

    /**
     * Reset the canBomb
     * @param bool whether bombGuy can bomb
     */
    public void setCanBomb(boolean bool) {
        this.canBomb = bool;
    }

    /**
     * Update the keyRelease
     * @param bool whether key has been released
     */
    public void setRelease(boolean bool) {
        release = bool;
    }

    /**
     * Updates the bombGuy every frame.
     */
    public void tick() {
        checkCollision();
        // If move, horizontal and canMoveLeft is true, move left by decrementing x
        if (move) {
            if (horizontal && canMoveLeft) {
                moveLeft = true;
                moveRight = false;
                moveUp = false;
                moveDown = false;
                x -= 32;
            } else if (horizontal && canMoveRight){
                // Move right by incrementing x
                moveLeft = false;
                moveRight = true;
                moveUp = false;
                moveDown = false;
                x += 32;
            } else if (!horizontal && canMoveUp) {
                // Move up by decrementing y
                moveUp = true;
                moveLeft = false;
                moveRight = false;
                moveDown = false;
                y -= 32;
            } else if (!horizontal && canMoveDown) {
                // Move down by incrementing y
                moveUp = false;
                moveDown = true;
                moveLeft = false;
                moveRight = false;
                y += 32;
            }
            move = false;
            release = false;
        }
    }

    /**
     * Called in App when the left key is pressed.
     */
    public void pressLeft() {
        horizontal = true;
        move = true;
        canMoveLeft = true;
        canMoveRight = false;
    }

    /**
     * Called in App when the right key is pressed.
     */
    public void pressRight() {
        horizontal = true;
        move = true;
        canMoveLeft = false;
        canMoveRight = true;
    }

    /**
     * Called in App when the up key is pressed.
     */
    public void pressUp() {
        horizontal = false;
        move = true;
        canMoveUp = true;
        canMoveDown = false;
    }

    /**
     * Called in App when the down key is pressed.
     */
    public void pressDown() {
        horizontal = false;
        move = true;
        canMoveUp = false;
        canMoveDown = true;
    }

    /**
     * Called in App when the space key is pressed
     * bombGuy have infinite bombs
     * but only one bomb can be placed once a time
     * because placing multiple bombs once a time is unreasonable
     */
    public void pressSpace() {
        space = true;
    }

}