package edu.chalmers.projecttemplate.model.breakoutmodel;


import edu.chalmers.projecttemplate.model.common.Rectangle;

public class Brick extends Rectangle {
    private int point;
    private boolean isActive;
    private int hit;

    public Brick(int x, int y, int width, int height, int point) {
        super(x, y, width, height);
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
    //Return brick hit
    public int getBrickHit() {
        return this.hit;
    }
    //Return brick status
    public boolean getBrickStatus() {
        return this.isActive;
    }
    //Count brick hit
    public void setBrickHit() {
        this.hit--;
    }
    //Set brick status
    public void setBrickStatus() {
        if (this.hit<=0 || this.hit>4)
            this.isActive = false;
    }

}
