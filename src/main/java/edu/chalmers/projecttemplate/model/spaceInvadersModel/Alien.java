package edu.chalmers.projecttemplate.model.spaceInvadersModel;

import java.util.Random;

/**
 * Represents an enemy Alien for the old retro game Space Invaders
 *
 */
class Alien extends GameObject implements IMovable{
    private int direction;
    private final int speed;

    private int timeBetweenShots;
    private int timeSinceLastShot = 0;
    Random rng = new Random();

    /**
     * Constructs an Alien with specified position, size and speed
     *
     * @param xPos the start x position of the Alien
     * @param yPos the start y position of the Alien
     * @param size the height and width of an Alien
     * @param speed the speed of the Alien
     */
    protected Alien(int xPos, int yPos, int size, int speed) {
        super(xPos, yPos, size, size, "Alien");
        direction = 1;
        this.speed = speed;
        timeBetweenShots = rng.nextInt(3000 - 500 + 1) + 500;
    }

    /**
     * Moves the Alien horizontally using the variables direction and speed found in super class MovableObject
     *
     */
    public void move() {
        //move in direction according to speed
        getHitbox().incX(speed * direction);
    }

    /**
     * Moves the Alien vertically using the variables speed and height found in super class MovableObject
     *
     */
    public void moveDown(){
        //move in direction according to speed
        getHitbox().incY(speed + getHeight());
    }

    /**
     * Returns the variable representing direction in which the Projectile is facing
     *
     * @return int direction of the Projectile
     */
    public int getDirection(){
        return direction;
    }

    /**
     * Sets the direction which the Alien is facing
     *
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * Returns the variable representing the time between each shot the Alien can do
     *
     * @return int for how long Alien has to wait between every shot
     */
    public int getTimeBetweenShots() {
        return timeBetweenShots;
    }

    /**
     * Returns the variable representing the time since Alien last shot a projectile
     *
     * @return int for how long it's been since last projectile was fired by Alien
     */
    public int getTimeSinceLastShot() {
        return timeSinceLastShot;
    }

    /**
     * Resets the variable timeSinceLastShot by assigning it the value 0 and then randomizes the timeBetweenShots
     * variable between 500 to 3000 for
     *
     */
    public void resetTimeSinceLastShot(){
        timeSinceLastShot = 0;
        timeBetweenShots = rng.nextInt(3000 - 500 + 1) + 500;
    }

    /**
     * Increases the int variable timeSinceLastShot by 1
     *
     */
    public void incTimeSinceLastShot(){
        timeSinceLastShot++;
    }
}
