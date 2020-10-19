package edu.chalmers.projecttemplate.model.spaceInvadersModel;

import edu.chalmers.projecttemplate.model.common.IRectangle;
import edu.chalmers.projecttemplate.model.common.IRepresentable;
import edu.chalmers.projecttemplate.model.common.Rectangle;

/**
 * Represents an abstract movable object for the old retro game Space Invaders
 *
 */
public abstract class MovableObject implements IHitable, IRepresentable {

    private String type;
    private IRectangle hitbox;
    private int direction;
    private int speed;

    /**
     * Constructs a movableObject with specified position, size, speed and type
     *
     * @param xPos the start x position of the movableObject
     * @param yPos the start y position of the movableObject
     * @param width the width of the movableObject
     * @param height the height of the movableObject
     * @param speed the speed of the movableObject
     * @param type the type of movableObject used for identification
     */
    protected MovableObject(int xPos, int yPos, int width, int height, int speed, String type) {
        this.speed = speed;
        hitbox = new Rectangle(xPos, yPos, width, height);
        this.type = type;
    }

    /**
     * Abstract move method used for movement logic
     *
     */
    protected abstract void move();

    /**
     * Returns the x position of the movableObject
     *
     * @return int x position of the movableObject
     */
    public int getX(){
        return hitbox.getX();
    }

    /**
     * Returns the y position of the movableObject
     *
     * @return int y position of the movableObject
     */
    public int getY(){
        return hitbox.getY();
    }

    /**
     * Returns the width of the movableObject
     *
     * @return int width of the movableObject
     */
    public int getWidth(){
        return hitbox.getWidth();
    }

    /**
     * Returns the height of the movableObject
     *
     * @return int height of the movableObject
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
     * Returns the rectangle representing the hitbox of the movableObject
     *
     * @return rectangle hitbox of the movableObject
     */
    public IRectangle getHitbox(){
        return hitbox;
    }

    /**
     * Returns the variable representing the speed of the movableObject
     *
     * @return int speed for how fast a movableObject is moving
     */
    public int getSpeed(){
        return speed;
    }

    /**
     * Returns the variable representing direction in which the movableObject is facing
     *
     * @return int direction of the movableObject
     */
    public int getDirection(){
        return direction;
    }

    /**
     * Sets the direction which the movableObject is facing
     *
     */
    protected void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * sets the x position of the movableObject, used by the movement function
     *
     */
    protected void setX(int deltaX){
        hitbox.incX(deltaX);
    }

    /**
     * sets the x position of the movableObject, used by the movement functions
     *
     */
    protected void setY(int deltaY){
        hitbox.incY(deltaY);
    }

}
