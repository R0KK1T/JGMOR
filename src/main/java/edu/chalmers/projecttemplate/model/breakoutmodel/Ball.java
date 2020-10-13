package edu.chalmers.projecttemplate.model.breakoutmodel;
/*
 * Model for Breakout ball - The class has a move() method that moves the ball on the Board.
 * If the ball hits the borders, the directions are changed accordingly.
 */
public class Ball extends Commons{
    private double dx;
    private double dy;
    private double dt;

    public Ball(double x, double y, double width, double height) {
        super(x, y, width, height);
        dx = 0.1;
        dy = 0.1;
        dt = 5;
    }
    /*
     * The ball bounces off the wall according to low of elastic collision
     */
    public void move() {
        if (x < 0 || (x+width > windowSizeX))
            dx = -dx;
        if (y < 0 || (y+height > windowSizeY))
            dy = -dy;
        //update position
        x = x + dt*dx;
        this.setX(x);
        y = y + dt*dy;
        this.setY(y);

    }

}
