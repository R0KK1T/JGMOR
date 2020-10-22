package edu.chalmers.projecttemplate.model.froggermodel;

import edu.chalmers.projecttemplate.model.common.IRectangle;
import edu.chalmers.projecttemplate.model.common.Rectangle;

/**
 * Represents an Obstacle from the old retro game Frogger
 */
public class Obstacle implements IRepresentable {
    private IRectangle hitbox;
    private int velocity;
    private ObstacleType type;

    /**
     * Constructs an instance of Frog with specified coordinates, size, velocity and type
     * @param x the initial x-coordinate of the object
     * @param y the initial y-coordinate of the object
     * @param width the width of the object
     * @param height the height of the object
     * @param velocity the velocity of the object / the distance travelled when moving
     * @param type the type of obstacle
     */
    public Obstacle(int x, int y, int width, int height, int velocity, ObstacleType type){
        hitbox = new Rectangle(x, y, width, height);
        this.velocity = velocity;
        this.type = type;
    }

    /**
     * Increases x-coordinate by the amount of velocity
     */
    public void move(){
        hitbox.incX(getVelocity());
    }

    /**
     * Sets x-coordinate to given variable x
     * @param x the value that Obstacle's x-coordinate is to take
     */
    public void moveTo(int x){
        hitbox.setX(x);
    }

    /**
     * Getter for variable hitbox
     * @return IRectangle, hitbox
     */
    public IRectangle getHitbox() {
        return hitbox;
    }
    /**
     * Getter for Obstacle's x-coordinate
     * @return int, hitbox's x
     */
    public int getX(){ return getHitbox().getX(); }

    /**
     * Getter for Obstacle's y-coordinate
     * @return int, hitbox's y
     */
    public int getY(){ return getHitbox().getY(); }

    /**
     * Getter for Obstacle's width
     * @return int, hitbox's width
     */
    public int getWidth(){ return getHitbox().getWidth(); }

    /**
     * Getter for Obstacle's height
     * @return int, hitbox's height
     */
    public int getHeight(){ return getHitbox().getHeight(); }

    /**
     * Getter for variable velocity
     * @return int, velocity
     */
    public int getVelocity(){ return velocity;}

    /**
     * Getter for Obstacle's type
     * @return string, type.toString()
     */
    public String getType(){
        return type.toString();
    }

}
