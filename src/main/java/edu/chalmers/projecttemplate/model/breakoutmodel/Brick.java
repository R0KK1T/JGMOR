package edu.chalmers.projecttemplate.model.breakoutmodel;

import edu.chalmers.projecttemplate.model.common.IPositionableInt;
import edu.chalmers.projecttemplate.model.common.IRectangle;
import edu.chalmers.projecttemplate.model.common.Rectangle;

/*
 * Model for Breakout brick - Represent the number of bricks in the game.
 */
public class Brick implements IPositionableInt {
    private IRectangle hitbox;
    private int point;
    private boolean isActive;
    private int hit;
    /**
     * Constructs a brick with specified position and size
     *
     * @param x the start x position of the brick
     * @param y the start y position of the brick
     * @param height the height of the brick
     * @param width the width of the brick
     * @param point the level (e.g: 1, 2, 3, 4) of the brick
     */
    public Brick(int x, int y, int width, int height, int point) {
        hitbox = new Rectangle(x, y, width, height);
        this.point = point;
        this.hit = point;
        isActive = true;
    }
    /*
     * Each brick has a specific score : Redbrick = 4points, Orangebrick = 3points, Greenbrick = 2 points,
     * yellowbrick = 1 point
     */
    //Return point
    public int getBrickPoint() {
        return point;
    }
    /*
     * Each brick must be hit a specific number of time to disappear from the game.
     * isActive = true ==> Brick still in the game
     * isActive = false ==> Brick has been hit as the rule says
     */
    //Returns brick hit
    public int getBrickHit() {
        return this.hit;
    }
    //Returns brick status
    public boolean getBrickStatus() {
        return this.isActive;
    }
    //Counts brick hit
    public void setBrickHit() {
        this.hit--;
    }
    //Sets brick status
    public void setBrickStatus() {
        if (this.hit<=0 || this.hit>4)
            this.isActive = false;
    }

    @Override
    public int getX() {
        return hitbox.getX();
    }

    @Override
    public int getY() {
        return hitbox.getY();
    }

    @Override
    public int getWidth() {
        return hitbox.getWidth();
    }

    @Override
    public int getHeight() {
        return hitbox.getHeight();
    }

    public IRectangle getHitbox() {
        return hitbox;
    }
}
