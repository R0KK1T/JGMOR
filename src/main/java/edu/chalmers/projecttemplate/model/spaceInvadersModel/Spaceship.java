package edu.chalmers.projecttemplate.model.spaceInvadersModel;

/**
 * Represents a player spaceship for the old retro game Space Invaders
 *
 */
class Spaceship extends MovableObject{

    /**
     * Constructs a spaceship with specified position, size and speed
     *
     * @param xPos the start x position of the spaceship
     * @param yPos the start y position of the spaceship
     * @param width the width of the spaceship
     * @param height the height of the spaceship
     * @param speed the speed of the spaceship
     */
    protected Spaceship(int xPos, int yPos, int width, int height, int speed) {
        super(xPos, yPos, width, height, speed, "Spaceship");
    }

    /**
     * Moves the spaceship horizontally using the variables direction and speed found in super class MovableObject
     *
     */
    public void move() {
        //move in direction according to speed
        setX(getDirection() * getSpeed());
    }

}
