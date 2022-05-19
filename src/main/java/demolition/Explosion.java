package demolition;

public class Explosion extends GameObject {
    
    private static int explosionTimer;
    private static boolean isExplosion;

    /**
     * Constructor for the explosion
     * @param x the x-coordinate of the explosion
     * @param y the y-coordinate of the explosion
     */
    public Explosion(int x, int y) {
        super(x, y);
        explosionTimer = 0;
        isExplosion = false;
    }

    public void tick() {}

    /**
     * Remove the explosion
     */
    public void reset() {
        x = -1000;
        y = -1000;
    }

    /**
     * check whether the bomb is explosion 
     * @return isExplosion
     */
    public static boolean getExplosion() {
        return isExplosion;
    }

    /**
     * Check the explosion range and whether can break the wall
     * @param bomb the bomb
     * @param explosion the explosion list
     * @param level the current level
     * @param bombGuy the bombGuy
     * @param yellowEnemy the yellowEnemy
     * @param redEnemy the redEnemy
     */
    public static void explore(Bomb bomb, Explosion[] explosion, Level level, BombGuy bombGuy, YellowEnemy yellowEnemy, RedEnemy redEnemy) {
        int x = bomb.getX();
        int y = bomb.getY();
        bomb.reset();
        bombGuy.setCanBomb(true);
        for (int i = 0; i < explosion.length; i++) {
            explosion[0].setCoordinate(x, y);
            explosion[1].setCoordinate(x, y + 64);
            explosion[2].setCoordinate(x - 64, y);
            explosion[3].setCoordinate(x + 64, y);
            explosion[4].setCoordinate(x, y - 64);
            explosion[5].setCoordinate(x + 32, y);
            explosion[6].setCoordinate(x - 32, y);
            explosion[7].setCoordinate(x, y + 32);
            explosion[8].setCoordinate(x, y - 32);
        }
        
        boolean hasLeft = false;
        boolean hasRight = false;
        boolean hasUp = false;
        boolean hasDown = false;
        boolean hasNext = false;
        for (SolidWall solid : level.getSolidWall()) {
            if (solid.getX() == x - 32 && solid.getY() == y) {
                explosion[6].reset();
                explosion[2].reset();
                hasLeft = true;
            } else if (solid.getX() == x + 32 && solid.getY() == y) {
                explosion[3].reset();
                explosion[5].reset();
                hasRight = true;
            } else if (solid.getX() == x && solid.getY() == y - 32) {
                explosion[4].reset();
                explosion[8].reset();
                hasUp = true;
            } else if (solid.getX() == x && solid.getY() == y + 32) {
                explosion[1].reset();
                explosion[7].reset();
                hasDown = true;
            } else if (solid.getX() == x - 64 && solid.getY() == y && !hasLeft) {
                explosion[2].reset();
            } else if (solid.getX() == x + 64 && solid.getY() == y && !hasRight) {
                explosion[3].reset();
            } else if (solid.getX() == x && solid.getY() == y - 64 && !hasUp) {
                explosion[4].reset();
            } else if (solid.getX() == x && solid.getY() == y + 64 && !hasDown) {
                explosion[1].reset();
            }
        }

        for (Broken b : level.getBroken()) {
            if (b.getX() == x - 32 && b.getY() == y) {
                explosion[2].reset();
                b.remove();
                b.broke();
                hasLeft = true;
            } else if (b.getX() == x + 32 && b.getY() == y) {
                explosion[3].reset();
                b.remove();
                b.broke();
                hasRight = true;
            } else if (b.getX() == x && b.getY() == y - 32) {
                explosion[4].reset();
                b.remove();
                b.broke();
                hasUp = true;
            } else if (b.getX() == x && b.getY() == y + 32) {
                explosion[1].reset();
                b.remove();
                b.broke();
                hasDown = true;
            } else if (b.getX() == x - 64 && b.getY() == y) {
                for (Broken b2 : level.getBroken()) {
                    if (b.getX() + 32 == b2.getX() && b.getY() == b2.getY()) {
                        hasNext = true;
                    }
                }
                if (!hasLeft && !hasNext) {
                    b.remove();
                    b.broke();
                }
            } else if (b.getX() == x + 64 && b.getY() == y) {
                if (!hasRight) {
                    b.remove();
                    b.broke();
                }
            } else if (b.getX() == x && b.getY() == y - 64) {
                for (Broken b2 : level.getBroken()) {
                    if (b.getX() == b2.getX() && b.getY() + 32 == b2.getY()) {
                        hasNext = true;
                    }
                }
                if (!hasUp && !hasNext) {
                    b.remove();
                    b.broke();
                }
            } else if (b.getX() == x && b.getY() == y + 64) {
                if (!hasDown) {
                    b.remove();
                    b.broke();
                }
            } 
        }

        int dis = 11;
        if ((((redEnemy.getX() >= x - 32 && redEnemy.getX() <= x) || (redEnemy.getX() <= x + 32 && redEnemy.getX() >= x)) && redEnemy.getY() + dis == y) ||
                (redEnemy.getX() == x && ((redEnemy.getY() + dis <= y + 32 && redEnemy.getY() + dis >= y) || (redEnemy.getY() + dis >= y - 32 && redEnemy.getY() + dis <= y)))) {
                    redEnemy.die();
        } else if (redEnemy.getX() == x - 64 && redEnemy.getY() + dis == y && !hasLeft) {
            redEnemy.die();
        } else if (redEnemy.getX() == x + 64 && redEnemy.getY() + dis == y && !hasRight) {
            redEnemy.die();
        } else if (redEnemy.getX() == x && redEnemy.getY() + dis == y + 64 && !hasDown) {
            redEnemy.die();
        } else if (redEnemy.getX() == x && redEnemy.getY() + dis == y - 64 && !hasUp) {
            redEnemy.die();
        }

        if ((((yellowEnemy.getX() >= x - 32 && yellowEnemy.getX() <= x) || (yellowEnemy.getX() <= x + 32 && yellowEnemy.getX() >= x)) && yellowEnemy.getY() + dis == y) ||
                (yellowEnemy.getX() == x && ((yellowEnemy.getY() + dis <= y + 32 && yellowEnemy.getY() + dis >= y) || (yellowEnemy.getY() + dis >= y - 32 && yellowEnemy.getY() + dis <= y)))) {
                    yellowEnemy.die();
        } else if (yellowEnemy.getX() == x - 64 && yellowEnemy.getY() + dis == y && !hasLeft) {
            yellowEnemy.die();
        } else if (yellowEnemy.getX() == x + 64 && yellowEnemy.getY() + dis == y && !hasRight) {
            yellowEnemy.die();
        } else if (yellowEnemy.getX() == x && yellowEnemy.getY() + dis == y + 64 && !hasDown) {
            yellowEnemy.die();
        } else if (yellowEnemy.getX() == x && yellowEnemy.getY() + dis == y - 64 && !hasUp) {
            yellowEnemy.die();
        }
        
        if ((((bombGuy.getX() >= x - 32 && bombGuy.getX() <= x) || (bombGuy.getX() <= x + 32 && bombGuy.getX() >= x)) && bombGuy.getY() + dis == y) ||
                (bombGuy.getX() == x && ((bombGuy.getY() + dis <= y + 32 && bombGuy.getY() + dis >= y) || (bombGuy.getY() + dis >= y - 32 && bombGuy.getY() + dis <= y)))) {
                    App.lostLive(bombGuy, redEnemy, yellowEnemy); 
        } else if (bombGuy.getX() == x - 64 && bombGuy.getY() + dis == y && !hasLeft) {
            App.lostLive(bombGuy, redEnemy, yellowEnemy); 
        } else if (bombGuy.getX() == x + 64 && bombGuy.getY() + dis == y && !hasRight) {
            App.lostLive(bombGuy, redEnemy, yellowEnemy); 
        } else if (bombGuy.getX() == x && bombGuy.getY() + dis == y + 64 && !hasDown) {
            App.lostLive(bombGuy, redEnemy, yellowEnemy); 
        } else if (bombGuy.getX() == x && bombGuy.getY() + dis == y - 64 && !hasUp) {
            App.lostLive(bombGuy, redEnemy, yellowEnemy); 
        }

        isExplosion = true;
    }
    
    /**
     * Remove the explosion after 0.5s
     * @param explosion the explosion list
     */
    public static void checkExplosion(Explosion[] explosion) {
        if (isExplosion) {
            explosionTimer++;
            if (explosionTimer > App.RESET_SECONDS_BETWEEN_FRAME * App.FPS) {
                for (Explosion e : explosion) {
                    e.reset();
                }
                isExplosion = false;
                explosionTimer = 0;
            }   
        }
    }
}
