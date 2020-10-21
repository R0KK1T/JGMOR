package edu.chalmers.projecttemplate.model.spaceInvadersModel;

/**
 * Represents a protective barrier for the old retro game Space Invaders
 *
 */
public class Barrier extends GameObject {
    private int health;

    /**
     * Constructs a barrier with specified position and size
     *
     * @param xPos the start x position of the barrier
     * @param yPos the start y position of the barrier
     * @param width the width of the barrier
     * @param height the height of the barrier
     */
    protected Barrier(int xPos, int yPos, int width, int height) {
        super(xPos, yPos, width, height, "Barrier");
        health = 10;
    }

    /**
     * Returns the health of the barrier
     *
     * @return int health of the barrier
     */
    public int getHealth(){
        return health;
    }

    /**
     * Decreases the health variable of the barrier by 1
     *
     */
    public void decreaseHealth(){
        health--;
    }


}
