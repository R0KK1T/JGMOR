package edu.chalmers.projecttemplate.model.spaceInvadersModel;

import edu.chalmers.projecttemplate.model.common.Rectangle;

/**
 * Represents a protective barrier for the old retro game Space Invaders
 *
 */
class Barrier implements GameObject, IHitable{
    private int health;
    private Rectangle hitbox;
    private String type;

    /**
     * Constructs a barrier with specified position and size
     *
     * @param xPos the start x position of the barrier
     * @param yPos the start y position of the barrier
     * @param width the width of the barrier
     * @param height the height of the barrier
     */
    protected Barrier(int xPos, int yPos, int width, int height) {
        health = 10;
        type = "Barrier";
        hitbox = new Rectangle(xPos, yPos, width, height);
    }

    /**
     * Returns the x position of the barrier
     *
     * @return int x position of the barrier
     */
    public int getX(){
        return hitbox.getX();
    }

    /**
     * Returns the y position of the barrier
     *
     * @return int y position of the barrier
     */
    public int getY(){
        return hitbox.getY();
    }

    /**
     * Returns the width of the barrier
     *
     * @return int width of the barrier
     */
    public int getWidth(){
        return hitbox.getWidth();
    }

    /**
     * Returns the height of the barrier
     *
     * @return int height of the barrier
     */
    public int getHeight(){
        return hitbox.getHeight();
    }

    /**
     * Returns the string type representing what type of object this is
     *
     * @return string type for identifying object
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the rectangle representing the hitbox of the barrier
     *
     * @return rectangle hitbox of the barrier
     */
    public Rectangle getHitbox() {
        return hitbox;
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
