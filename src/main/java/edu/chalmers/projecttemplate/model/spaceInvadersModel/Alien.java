package edu.chalmers.projecttemplate.model.spaceInvadersModel;

import java.util.Random;

/**
 * Represents an enemy alien for the old retro game Space Invaders
 *
 */
class Alien extends MovableObject{
    private int timeBetweenShots;
    private int timeSinceLastShot = 0;
    Random rng = new Random();

    /**
     * Constructs an alien with specified position, size and speed
     *
     * @param xPos the start x position of the alien
     * @param yPos the start y position of the alien
     * @param size the height and width of an alien
     * @param speed the speed of the alien
     */
    protected Alien(int xPos, int yPos, int size, int speed) {
        super(xPos, yPos, size, size, speed, "Alien");
        setDirection(1);
        timeBetweenShots = rng.nextInt(3000 - 500 + 1) + 500;
    }

    /**
     * Moves the alien horizontally using the variables direction and speed found in super class MovableObject
     *
     */
    public void move() {
        //move in direction according to speed
        setX(getDirection() * getSpeed());
    }

    /**
     * Moves the alien vertically using the variables speed and height found in super class MovableObject
     *
     */
    public void moveDown(){
        //move in direction according to speed
        setY(getSpeed() + getHeight());
    }

    /**
     * Returns the variable representing the time between each shot the alien can do
     *
     * @return int for how long alien has to wait between every shot
     */
    public int getTimeBetweenShots() {
        return timeBetweenShots;
    }

    /**
     * Returns the variable representing the time since alien last shot a projectile
     *
     * @return int for how long it's been since last projectile was fired by alien
     */
    public int getTimeSinceLastShot() {
        return timeSinceLastShot;
    }

    /**
     * Resets the variable timeSinceLastShot by assigning it the value 0 and then randomizes the timeBetweenShots
     * variable between 500 to 3000 for
     *
     */
    public void resetTimeScinceLastShot(){
        timeSinceLastShot = 0;
        timeBetweenShots = rng.nextInt(3000 - 500 + 1) + 500;
    }

    /**
     * Increases the int variable timeSinceLastShot by 1
     *
     */
    public void incTimeScinceLastShot(){
        timeSinceLastShot++;
    }
}
