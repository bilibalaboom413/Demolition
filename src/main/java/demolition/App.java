package demolition;

import processing.core.PApplet;
import processing.core.PFont;
import processing.data.JSONObject;
import processing.data.JSONArray;

public class App extends PApplet {
    /**
     * The width of the window
     */
    public static final int WIDTH = 480;

    /**
     * The height of the window
     */
    public static final int HEIGHT = 480;

    /**
     * The FPS of the App
     */
    public static final int FPS = 60;

    /**
     * Every 0.2s change the sprite of characters
     */
    public static final double SECONDS_BETWEEN_FRAME = 0.2;

    /**
     * Every 0.25s change the sprite of the bomb
     */
    public static final double BOMB_SECONDS_BETWEEN_FRAME = 0.25;

    /**
     * Explosion remain for 0.5s
     */
    public static final double RESET_SECONDS_BETWEEN_FRAME = 0.5;

    private static Level[] levels;
    private static Level level;
    private static PlayerIcon playerIcon;
    private static int levelIndex;
    private static int bombIndex;
    private static Bomb bomb;
    private BombGuy bombGuy;
    private RedEnemy redEnemy;
    private YellowEnemy yellowEnemy;
    private Explosion[] explosion;
    private JSONObject jsonObject;
    private JSONArray jsonArray;
    private ClockIcon clockIcon;
    private WinScreen winScreen;
    private GameOver gameOver;
    private PFont pFont;
    private String font;
    private int levelOneTime;
    private int levelTwoTime;
    private int gameTime;
    private int lives;
    private String levelOnePath;
    private String levelTwoPath;
    private String jsonPath;
    private int characterIndex;
    private int characterTimer;
    private int bombTimer;
    private boolean change;
    private boolean win;

    /**
     * Constructor for the App, initialize some game objects
     */
    public App() { 
        bomb = new Bomb(-1000, -1000);
        playerIcon = new PlayerIcon(160, 20);
        clockIcon = new ClockIcon(288, 20);
        winScreen = new WinScreen(160, 224);
        gameOver = new GameOver(160, 224);
        explosion = new Explosion[9];
        font = "PressStart2P-Regular.ttf";
        levelIndex = 0;
        bombIndex = 0;
        characterIndex = 0;
        bombTimer = 0;
        characterTimer = 0;
        change = false;
        win = false;
        jsonPath = "config.json";
    }

    /**
     * Get the current level
     * @return current level
     */
    public static Level getLevel() {
        return level;
    }

    /**
     * Set the size of the window
     */
    public void settings() {
        size(WIDTH, HEIGHT);
    }

    /**
     * Set the config file path
     * @param path the config file path
     */
    public void setConfig(String path) {
        jsonPath = path;
    }
    
    /**
     * Load the configuration of the App
     */
    public void loadConfig() {
        jsonObject = this.loadJSONObject(jsonPath);
        lives = jsonObject.getInt("lives");
        playerIcon.setLives(lives);
        jsonArray = jsonObject.getJSONArray("levels");
        jsonObject = jsonArray.getJSONObject(0);
        levelOnePath = jsonObject.getString("path");
        levelOneTime = jsonObject.getInt("time");
        jsonObject = jsonArray.getJSONObject(1);
        levelTwoPath = jsonObject.getString("path");
        levelTwoTime = jsonObject.getInt("time");
    }

    /**
     * Load all the images of game objects
     * initialize the remaining game objects
     */
    public void setup() {
        // set the frameRate
        frameRate(FPS);
        // set the font style
        pFont = this.createFont(font, 20);
        this.textFont(pFont);
        // load the configuration
        this.loadConfig();
        // set the gameTime
        gameTime = levelOneTime;
        clockIcon.setTime(gameTime);
        // set the total levels
        levels = new Level[lives * 2];

        // generate the levelOne map
        for (int i = 0; i < lives; i++) {
            levels[i] = new Level();
            levels[i].readMap(levelOnePath);
            levels[i].generateMap(levels[i].getMap());
        }
        // generate the levelTwo map
        for (int i = lives; i < levels.length; i++) {
            levels[i] = new Level();
            levels[i].readMap(levelTwoPath);
            levels[i].generateMap(levels[i].getMap());
        }

        // load the images of levels 
        for (int i = 0; i < levels.length; i++) {
            for (EmptyTile e : levels[i].getEmptyTile()) {
                e.setSprite(this.loadImage("src/main/resources/empty/empty.png"));
            }
            for (SolidWall s : levels[i].getSolidWall()) {
                s.setSprite(this.loadImage("src/main/resources/wall/solid.png"));
            }
            for (Broken b : levels[i].getBroken()) {
                b.setSprite(this.loadImage("src/main/resources/broken/broken.png"));
            }
            levels[i].getGoal().setSprite(this.loadImage("src/main/resources/goal/goal.png")); 
        }

        // load the image of explosion
        for (int i = 0; i < explosion.length; i++) {
            explosion[i] = new Explosion(-1000, -1000);
        }
        explosion[0].setSprite(this.loadImage("src/main/resources/explosion/centre.png"));
        explosion[1].setSprite(this.loadImage("src/main/resources/explosion/end_bottom.png"));
        explosion[2].setSprite(this.loadImage("src/main/resources/explosion/end_left.png"));
        explosion[3].setSprite(this.loadImage("src/main/resources/explosion/end_right.png"));
        explosion[4].setSprite(this.loadImage("src/main/resources/explosion/end_top.png"));
        explosion[5].setSprite(this.loadImage("src/main/resources/explosion/horizontal.png"));
        explosion[6].setSprite(this.loadImage("src/main/resources/explosion/horizontal.png"));
        explosion[7].setSprite(this.loadImage("src/main/resources/explosion/vertical.png"));
        explosion[8].setSprite(this.loadImage("src/main/resources/explosion/vertical.png"));
        
        // initialize the remaining game objects
        level = levels[levelIndex];
        bombGuy = new BombGuy(level.getPlayerX(), level.getPlayerY());
        bombGuy.setInitial(level.getPlayerX(), level.getPlayerY());
        redEnemy = new RedEnemy(level.getRedX(), level.getRedY());
        yellowEnemy = new YellowEnemy(level.getYellowX(), level.getYellowY());
        bombGuy.setSprite(this.loadImage("src/main/resources/player/player1.png"));
        redEnemy.setSprite(this.loadImage("src/main/resources/red_enemy/red_down1.png"));
        yellowEnemy.setSprite(this.loadImage("src/main/resources/yellow_enemy/yellow_down1.png"));
        bomb.setSprite(this.loadImage("src/main/resources/bomb/bomb.png"));
        playerIcon.setSprite(this.loadImage("src/main/resources/icons/player.png"));
        clockIcon.setSprite(this.loadImage("src/main/resources/icons/clock.png"));
    }

    /**
     * Draw the images every frame
     */
    public void draw() {
        background(255, 127, 36);
        // check whether bombGuy has reached the goal
        if (bombGuy.getX() == level.getGoal().getX() && bombGuy.getY() + 11 == level.getGoal().getY() && !change) {
            change = true; // levelOne : false, levelTwo : true
            // change level and initialize the game object's position
            levelIndex = lives; 
            level = levels[levelIndex];
            bombGuy.setInitial(level.getPlayerX(), level.getPlayerY());
            redEnemy.setInitial(level.getRedX(), level.getRedY());
            yellowEnemy.setInitial(level.getYellowX(), level.getYellowY());
            bombGuy.reset();
            redEnemy.reset();
            yellowEnemy.reset();
            clockIcon.setTime(levelTwoTime);
        }

        // check whether bombGuy has reached the final goal
        if (change) {
            level = levels[levelIndex];
            if (bombGuy.getX() == level.getGoal().getX() && bombGuy.getY() + 11 == level.getGoal().getY()) {
                this.text("YOU WIN", winScreen.getX(), winScreen.getY());
                win = true;
            }
        }
        // draw the game objects if it is not game over
        if (playerIcon.getLives() != 0 && clockIcon.getTime() != 0 && !win) {
            // draw the UI
            this.text("" + playerIcon.getLives(), 200, 45);
            this.text(clockIcon.getTime() + "S", 325, 45);
            playerIcon.draw(this);
            clockIcon.tick();
            clockIcon.draw(this);
            // draw the map
            for (EmptyTile e : level.getEmptyTile()) {
                e.draw(this);
            }
            for (SolidWall s : level.getSolidWall()) {
                s.draw(this);
            }
            for (Broken b : level.getBroken()) {
                b.draw(this);
            }
            for (Explosion e : explosion) {
                e.draw(this);
            } 
            level.getGoal().draw(this);

            // draw the character
            bomb.draw(this);
            bombGuy.tick();
            bombGuy.draw(this);
            redEnemy.tick();
            redEnemy.draw(this);
            yellowEnemy.tick();
            yellowEnemy.draw(this);
        }

        // draw the animations of characters 
        characterTimer++;
        if (characterTimer > SECONDS_BETWEEN_FRAME * App.FPS) {
            if (bombGuy.getMoveDown()) {
                bombGuy.setSprite(this.loadImage("src/main/resources/player/player" + (characterIndex % 4 + 1) + ".png"));
            } else if (bombGuy.getMoveUp()) {
                bombGuy.setSprite(this.loadImage("src/main/resources/player/player_up" + (characterIndex % 4 + 1) + ".png"));
            } else if (bombGuy.getMoveLeft()) {
                bombGuy.setSprite(this.loadImage("src/main/resources/player/player_left" + (characterIndex % 4 + 1) + ".png"));
            } else if (bombGuy.getMoveRight()) {
                bombGuy.setSprite(this.loadImage("src/main/resources/player/player_right" + (characterIndex % 4 + 1) + ".png"));    
            }
            
            if (redEnemy.getMove() == 0) {
                redEnemy.setSprite(this.loadImage("src/main/resources/red_enemy/red_up" + (characterIndex % 4 + 1) + ".png"));
            } else if (redEnemy.getMove() == 1) {
                redEnemy.setSprite(this.loadImage("src/main/resources/red_enemy/red_right" + (characterIndex % 4 + 1) + ".png"));
            } else if (redEnemy.getMove() == 2) {
                redEnemy.setSprite(this.loadImage("src/main/resources/red_enemy/red_down" + (characterIndex % 4 + 1) + ".png"));
            } else if (redEnemy.getMove() == 3) {
                redEnemy.setSprite(this.loadImage("src/main/resources/red_enemy/red_left" + (characterIndex % 4 + 1) + ".png"));
            }
            
            if (yellowEnemy.getMove() % 4 == 0) {
                yellowEnemy.setSprite(this.loadImage("src/main/resources/yellow_enemy/yellow_up" + (characterIndex % 4 + 1) + ".png"));
            } else if (yellowEnemy.getMove() % 4  == 1) {
                yellowEnemy.setSprite(this.loadImage("src/main/resources/yellow_enemy/yellow_right" + (characterIndex % 4 + 1) + ".png"));
            } else if (yellowEnemy.getMove() % 4  == 2) {
                yellowEnemy.setSprite(this.loadImage("src/main/resources/yellow_enemy/yellow_down" + (characterIndex % 4 + 1) + ".png"));
            } else if (yellowEnemy.getMove() % 4  == 3) {
                yellowEnemy.setSprite(this.loadImage("src/main/resources/yellow_enemy/yellow_left" + (characterIndex % 4 + 1) + ".png"));
            }
            characterTimer = 0;
            characterIndex++;
        }

        // place a bomb
        if (bombGuy.getSpace() && bombGuy.getCanBomb()) {
            int x = bombGuy.getX();
            int y = bombGuy.getY();
            bomb.setCoordinate(x, y + 11);
            bombGuy.setCanBomb(false);
            bombGuy.setSpace(false);
        }
        // draw the animations of the bomb
        if (!bombGuy.getCanBomb()) {
            bombGuy.setSpace(false);
            bombTimer++;
            if (bombTimer > BOMB_SECONDS_BETWEEN_FRAME * App.FPS) {
                bomb.setSprite(this.loadImage("src/main/resources/bomb/bomb" + (bombIndex + 1) + ".png"));
                bombIndex++;
                bombTimer = 0;
            }
        }
        // draw the animations of the explosion
        if (bombIndex == 8) {
            Explosion.explore(bomb, explosion, level, bombGuy, yellowEnemy, redEnemy);
            bombIndex = 0;
        }

        // check whether bombGuy lost lives and the broken wall can be broken
        lostLive(bombGuy, redEnemy, yellowEnemy);
        Explosion.checkExplosion(explosion);
        // check whether it's game over
        if ((playerIcon.getLives() == 0 || clockIcon.getTime() == 0) && !win) {
            this.text("GAME OVER", gameOver.getX(), gameOver.getY());
        }
    }

    /**
     * Check whether bombGuy lost a live
     * @param bombGuy the bombGuy
     * @param redEnemy the redEnemy
     * @param yellowEnemy the yellowEnemy
     */
    public static void lostLive(BombGuy bombGuy, RedEnemy redEnemy, YellowEnemy yellowEnemy) {
        // lives must > 0
        if (playerIcon.getLives() > 0) {
            // check whether bombGuy collides with enemy or the bomb is going to explore
            if ((bombGuy.getX() == redEnemy.getX() && bombGuy.getY() == redEnemy.getY()) || 
                    (bombGuy.getX() == yellowEnemy.getX() && bombGuy.getY() == yellowEnemy.getY()) ||
                        bombIndex == 8) {
                // reset the game object
                bombGuy.reset();
                redEnemy.reset();
                yellowEnemy.reset();
                bomb.reset();
                playerIcon.reset();
                // reload the new map
                if (levelIndex != levels.length - 1) 
                    levelIndex++;
                level = levels[levelIndex];
            } 
        }
    }

    /**
     * Update the keyPress of player
     */
    public void keyPressed() {
        if (this.bombGuy.getRelease()) {
            if (this.keyCode == 37) {
                this.bombGuy.pressLeft();
            } else if (this.keyCode == 39) {
                this.bombGuy.pressRight();
            } else if (this.keyCode == 38) {
                this.bombGuy.pressUp();
            } else if (this.keyCode == 40) {
                this.bombGuy.pressDown();
            }
        }
        
        if (this.keyCode == 32) {
            this.bombGuy.pressSpace();
        }
    }

    /**
     * Release the key
     */
    public void keyReleased() {
        this.bombGuy.setRelease(true);
    }
    
    public static void main(String[] args) {
        PApplet.main("demolition.App");
    }
}
