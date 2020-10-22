package edu.chalmers.projecttemplate.model.spaceInvadersModel;

/**
 * Represents a player spaceship for the old retro game Space Invaders
 *
 */
public class Spaceship extends GameObject implements IMovable {
    private int direction;
    private final int speed;

    /**
     * Constructs a spaceship with specified position, size and speed
     *
     * @param xPos the start x position of the spaceship
     * @param yPos the start y position of the spaceship
     * @param width the width of the spaceship
     * @param height the height of the spaceship
     * @param speed the speed of the spaceship
     */
    public Spaceship(int xPos, int yPos, int width, int height, int speed) {
        super(xPos, yPos, width, height, "Spaceship");
        this.speed = speed;
        direction = 0;
    }

    /**
     * Moves the spaceship horizontally using the variables direction and speed found in super class MovableObject
     *
     */
    public void move() {
        //move in direction according to speed
        getHitbox().incX(speed * direction);
    }

    /**
     * Returns the variable representing direction in which the Spaceship is facing
     *
     * @return int direction of the Spaceship
     */
    public int getDirection(){
        return direction;
    }

    /**
     * Sets the direction which the Spaceship is facing
     *
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

}
