package edu.chalmers.projecttemplate.model.breakoutmodel;
/*
 * Model for Breakout paddle - The paddle is controlled with left and right arrow keys.
 */
public class Paddle extends Commons {
    private double dx;
    public Paddle(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.dx = 0;
    }
    /*
     * The paddle moves only in the horizontal direction, so we only update the x
     */
    //Move paddle by update x via dx
    public void move() {
       x += dx;
       this.setX(x);
       if (x <=0)
           x = 0;
       if (x >= windowSizeX - width)
           x = windowSizeX - width;
    }
    public void setDx(double x) { this.dx = x; }
    public double getDx() { return dx; }

}
