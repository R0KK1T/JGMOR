package edu.chalmers.projecttemplate.model.breakoutmodel;
/*
 * Model for Breakout paddle - The paddle is controlled with left and right arrow keys.
 */
public class Paddle extends Commons {
    public Paddle(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    /*
     * The paddle moves only in the horizontal direction, so we only update the x
     */
    //Check collision with the wall
    public void move() {
       if (this.getX() <=0)
           this.setX(0);

       if (this.getX() >= this.getWindowSizeX() - this.getWidth())
           this.setX((this.getWindowSizeX() - this.getWidth()));
    }
}
