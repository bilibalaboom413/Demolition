package demolition;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GoalTest {
    
    Goal goal;

    public GoalTest() {
        goal = new Goal(32, 32);
    }

    /**
    * Test the constructor method
    */
    @Test
    public void testConstructor() {
        assertEquals(32, goal.getX());
        assertEquals(32, goal.getY());
    }
}
