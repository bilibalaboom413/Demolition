package demolition;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Level {

    private List<SolidWall> solid;
    private List<EmptyTile> empty;
    private List<Broken> broke;
    private Goal goal;
    private List<List<Character>> map;
    private int playerX;
    private int playerY;
    private int redX;
    private int redY;
    private int yellowX;
    private int yellowY;

    /**
     * Constructor of the Level
     */
    public Level() {
        playerX = -1000;
        playerY = -1000;
        redX = -1000;
        redY = -1000;
        yellowX = -1000;
        yellowY = -1000;
    }

    /**
     * Get the player x-coordinate
     * @return the player x-coordinate
     */
    public int getPlayerX() {
        return playerX;
    }

    /**
     * Get the player y-coordinate
     * @return the player y-coordinate
     */
    public int getPlayerY() {
        return playerY;
    }

    /**
     * Get the red enemy x-coordinate
     * @return the red enemy x-coordinate
     */
    public int getRedX() {
        return redX;
    }

    /**
     * Get the red enemy y-coordinate
     * @return the red enemy y-coordinate
     */
    public int getRedY() {
        return redY;
    }

    /**
     * Get the yellow enemy x-coordinate
     * @return the yellow enemy x-coordinate
     */
    public int getYellowX() {
        return yellowX;
    }

    /**
     * Get the yellow enemy y-coordinate
     * @return the yellow enemy y-coordinate
     */
    public int getYellowY() {
        return yellowY;
    }

    /**
     * Get the solid wall list
     * @return the solid wall list
     */
    public List<SolidWall> getSolidWall() {
        return solid;
    }

    /**
     * Get the empty wall list
     * @return the empty wall list
     */
    public List<EmptyTile> getEmptyTile() {
        return empty;
    }

    /**
     * Get the broken wall list
     * @return the broken wall list
     */
    public List<Broken> getBroken() {
        return broke;
    }

    /**
     * Get the goal
     * @return the goal
     */
    public Goal getGoal() {
        return goal;
    }

    /**
     * Get the map
     * @return the map
     */
    public List<List<Character>> getMap() {
        return map;
    }

    /**
     * Read the map file and generate map list
     * @param path the path of the map
     */
    public void readMap(String path) {
        File f = new File(path);
        map = new ArrayList<>();
        try {
            Scanner scan = new Scanner(f);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                List<Character> list = new ArrayList<>();
                for (int k = 0; k < line.length(); k++) {
                    list.add(line.charAt(k));
                }
                map.add(list);
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
        } 
    }

    /**
     * Generate the map according to the map list
     * @param map the map list
     */
    public void generateMap(List<List<Character>> map) {
        solid = new ArrayList<>();
        empty = new ArrayList<>();
        broke = new ArrayList<>();
        for (int i = 0; i < map.size(); i++) {
            List<Character> inner = map.get(i);
            for (int j = 0; j < inner.size(); j++) {
                int x = 32 * j;
                int y = 64 + 32 * i;
                if (inner.get(j) == 'W') {
                    solid.add(new SolidWall(x, y));
                } else if (inner.get(j) == 'B') {
                    broke.add(new Broken(x, y));
                    empty.add(new EmptyTile(x, y));
                } else if (inner.get(j) == 'G') {
                    goal = new Goal(x, y);
                    empty.add(new EmptyTile(x, y));
                } else if (inner.get(j) == 'P') {
                    playerX = x;
                    playerY = y - 11;
                    empty.add(new EmptyTile(x, y));
                } else if (inner.get(j) == 'R') {
                    redX = x;
                    redY = y - 11;
                    empty.add(new EmptyTile(x, y));
                } else if (inner.get(j) == 'Y') {
                    yellowX = x;
                    yellowY = y - 11;
                    empty.add(new EmptyTile(x, y));
                } else {
                    empty.add(new EmptyTile(x, y));
                }
            }
        }
    }
}
