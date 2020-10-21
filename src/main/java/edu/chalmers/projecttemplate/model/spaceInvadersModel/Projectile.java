package edu.chalmers.projecttemplate.model.spaceInvadersModel;

/**
 * Represents a projectile for the old retro game Space Invaders
 *
 */
class Projectile extends GameObject implements IMovable{
    private final int direction;
    private final int speed;

    /**
     * Constructs a projectile with specified position and speed
     *
     * @param xPos the start x position of the projectile
     * @param yPos the start y position of the projectile
     * @param direction the direction of the projectile
     * @param speed the speed of the projectile
     */
    protected Projectile(int xPos, int yPos,int direction, int speed) {
        super(xPos, yPos, 10, 20, "Projectile");
        this.direction = direction;
        this.speed = speed;
    }

    /**
     * Moves the projectile vertically using the variables direction and speed found in super class MovableObject
     *
     */
    public void move() {
        //move in direction according to speed
        getHitbox().incY(speed * direction);
    }

    /**
     * Returns the variable representing direction in which the Projectile is facing
     *
     * @return int direction of the Projectile
     */
    public int getDirection(){
        return direction;
    }
}
