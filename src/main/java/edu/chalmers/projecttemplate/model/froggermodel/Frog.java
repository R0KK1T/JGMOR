package edu.chalmers.projecttemplate.model.froggermodel;

import edu.chalmers.projecttemplate.model.common.IRectangle;
import edu.chalmers.projecttemplate.model.common.IRepresentable;
import edu.chalmers.projecttemplate.model.common.Rectangle;

/**
 * Represents the frog for the old retro game Frogger
 */
public class Frog implements IRepresentable {
    private IRectangle hitbox;
    private Obstacle riverObs;
    private int velocity;

    private boolean hasBounds = false;
    private int[][] bounds;

    /**
     * Constructs an instance of Frog with specified coordinates, size and velocity
     * @param x the initial x-coordinate of the object
     * @param y the initial y-coordinate of the object
     * @param width the width of the object
     * @param height the height of the object
     * @param velocity the velocity of the object / the distance travelled when moving
     */
    public Frog(int x, int y, int width, int height, int velocity){
        hitbox = new Rectangle(x, y, width, height);
        this.velocity = velocity;
    }

    /**
     * Updates the status of whether is intersecting with riverObs and either calls
     * function moveWhileAttached or detach.
     */
    public void update(){
        if(riverObs != null){
            if(hitbox.intersect(riverObs.getHitbox())){
                moveWhileAttached();
            }
            else{
                detach();
            }
        }
    }

    /**
     * Increases x-coordinate by velocity restricted by bounds if any.
     */
    public void moveRight(){
        if(hasBounds){
            if(getX() + getWidth() + getVelocity() < bounds[1][0]){
                hitbox.incX(getVelocity());
            }
            else{
                hitbox.incX(bounds[1][0] - (getX() + getWidth()));
            }
        }
        else{
            hitbox.incX(getVelocity());
        }
    }

    /**
     * Decreases x-coordinate by velocity restricted by bounds if any.
     */
    public void moveLeft(){
        if(hasBounds){
            if(getX() - getVelocity() >= bounds[0][0]){
                hitbox.decX(getVelocity());
            }
            else{
                hitbox.decX(getX() - bounds[0][0]);
            }
        }
        else{
            hitbox.decX(getVelocity());
        }
    }

    /**
     * Decreases y-coordinate by velocity restricted by bounds if any.
     */
    public void moveUp(){
        if(hasBounds){
            if(getY() - getVelocity() >= bounds[0][1]){
                hitbox.decY(getVelocity());
            }
            else{
                hitbox.decY(getY() - bounds[0][1]);
            }
        }
        else{
            hitbox.decY(getVelocity());
        }

    }

    /**
     * Increases y-coordinate by velocity restricted by bounds if any.
     */
    public void moveDown(){
        if(hasBounds){
            if(getY() + getHeight() + getVelocity() < bounds[1][1]){
                hitbox.incY(getVelocity());
            }
            else{
                hitbox.incY(bounds[1][1] - (getY() + getHeight()));
            }
        }
        else{
            hitbox.incY(getVelocity());
        }
    }

    /**
     * Increases x-coordinate by riverObs' velocity restricted by bounds if any.
     */
    private void moveWhileAttached(){
        if(hasBounds){
            if(riverObs.getVelocity() > 0){
                if(getX() + getWidth() + riverObs.getVelocity() < bounds[1][0]){
                    hitbox.incX(riverObs.getVelocity());
                }
                else{
                    hitbox.incX(bounds[1][0] - (getX() + getWidth()));
                }
            }
            else{
                if(getX() + riverObs.getVelocity() >= bounds[0][0]){
                    hitbox.incX(riverObs.getVelocity());
                }
                else{
                    hitbox.decX(getX() - bounds[0][0]);
                }
            }
        }
        else {
            hitbox.incX(riverObs.getVelocity());
        }
    }

    /**
     * Overrides function toString() from Object with a String of the class name in capital letters
     * @return string, "FROG"
     */
    @Override
    public String toString() {
        return "FROG";
    }

    /**
     * Sets boundaries of what values frog's coordinate can be. Saves boundaries in variable bounds
     * @param xMin the minimum value frog's x-coordinate can take
     * @param yMin the minimum value frog's y-coordinate can take
     * @param xMax the maximum value frog's x-coordinate can take
     * @param yMax the maximum value frog's y-coordinate can take
     */
    public void setBounds(int xMin, int yMin, int xMax, int yMax){
        bounds = new int[2][2];
        bounds[0][0] = xMin;
        bounds[0][1] = yMin;
        bounds[1][0] = xMax;
        bounds[1][1] = yMax;
        hasBounds = true;
    }

    /**
     * Setter for variable riverObs
     * @param obs the obstacle riverObs should be set to
     */
    public void attach(Obstacle obs){riverObs = obs;}

    /**
     * Sets riverObs to null
     */
    private void detach(){
        riverObs = null;
    }

    /**
     * Getter for variable hitbox
     * @return IRectangle, hitbox
     */
    public IRectangle getHitbox() {
        return hitbox;
    }

    /**
     * Getter for frog's x-coordinate
     * @return int, hitbox's x
     */
    public int getX(){ return getHitbox().getX(); }

    /**
     * Getter for frog's y-coordinate
     * @return int, hitbox's y
     */
    public int getY(){ return getHitbox().getY(); }

    /**
     * Getter for frog's width
     * @return int, hitbox's width
     */
    public int getWidth(){ return getHitbox().getWidth(); }

    /**
     * Getter for frog's height
     * @return int, hitbox's height
     */
    public int getHeight(){ return getHitbox().getHeight(); }

    /**
     * Getter for frog's type
     * @return string, frog.toString()
     */
    public String getType(){
        return this.toString();
    }

    /**
     * Getter for variable velocity
     * @return int, velocity
     */
    public int getVelocity(){ return velocity; }

    /**
     * Getter for riverObs, the obstacle frog is attached to
     * @return Obstacle, riverObs
     */
    public Obstacle getRiverObs() {
        return riverObs;
    }
}
