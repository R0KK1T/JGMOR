package edu.chalmers.projecttemplate.model.spaceInvadersModel;

/**
 * Represents a projectile for the old retro game Space Invaders
 *
 */
class Projectile extends MovableObject {

    /**
     * Constructs a projectile with specified position and speed
     *
     * @param xPos the start x position of the projectile
     * @param yPos the start y position of the projectile
     * @param direction the direction of the projectile
     */
    protected Projectile(int xPos, int yPos,int direction) {
        super(xPos, yPos, 10, 20, 2, "Projectile");
        setDirection(direction);
    }

    /**
     * Moves the projectile vertically using the variables direction and speed found in super class MovableObject
     *
     */
    public void move() {
        //move in direction according to speed
        setY(getDirection() * getSpeed());
    }
}
