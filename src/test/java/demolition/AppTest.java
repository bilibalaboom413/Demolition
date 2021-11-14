package demolition;

import processing.core.PApplet;
import org.junit.jupiter.api.Test;

// import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test 
    public void basicTest() {
        // Create an instance of your application
        App app = new App();

        // Set the program to not loop automatically
        app.noLoop();

        // Set the path of the config file to use
        app.setConfig("src/test/resources/config.json");

        // Tell PApplet to create the worker threads for the program
        PApplet.runSketch(new String[] {"App"}, app);

        // Call App.setup() to load in sprites
        app.setup();

        // Set a 1 second delay to ensure all resources are loaded
        app.delay(1000);

        // Call draw to update the program.
        app.draw();

        // Call keyPressed() or similar
        app.keyCode = 32;
        app.keyPressed();
        app.keyReleased();

        // Call draw again to move onto the next frame
        for (int i = 0; i < 120; i++)
            app.draw();

        // Verify the new state of the program with assertions
    }
    
    /**
    * Test the lostLive() method
    */
    @Test
    public void lostLiveTest() {
        BombGuy bombGuy = new BombGuy(32, 85);
        RedEnemy redEnemy = new RedEnemy(256, 213);
        YellowEnemy yellowEnemy = new YellowEnemy(160, 341);
        App.lostLive(bombGuy, redEnemy, yellowEnemy);
    }
}
