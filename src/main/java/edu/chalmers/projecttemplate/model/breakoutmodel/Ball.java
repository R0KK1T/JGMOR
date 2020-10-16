package edu.chalmers.projecttemplate.model.breakoutmodel;
/*
 * Model for Breakout ball - The class has a move() method that moves the ball on the Board.
 * If the ball hits the borders, the directions are changed accordingly.
 */
public class Ball extends Commons{
    private int dx;
    private int dy;
    private int dt;

    public Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
        dx = 1;
        dy = -1;
        dt = 1;
    }
    /*
     * The ball bounces off the wall according to low of elastic collision
     */
    public void move() {
        if (this.getX() < 0 || (this.getX()+this.getWidth() > this.getWindowSizeX()))
            dx = -dx;
        if (this.getY() < 0 || (this.getY()+this.getHeight() > this.getWindowSizeY()))
            dy = -dy;
        //update position
        //x = x + dt*dx;
        this.incX((dt*dx));
        //y = y + dt*dy;
        this.incY((dt*dy));

    }

}
