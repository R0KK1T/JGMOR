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
        if (this.getY() < 0)
            dy = -dy;
        //update position
        this.incX((dt*dx));
        this.incY((dt*dy));

    }
    /**
     * Reverse horizontal movement. If ball was moving left change it to move
     * right and vice versa
     */
    public void reverseHorizontalMomentum() {
        dx = -dx;
    }

    /**
     * Reverse vertical movement. If ball was moving upwards change it to move
     * downwards and vice versa
     */
    public void reverseVerticalMomentum() {
        dy = -dy;
    }
    //init har han 5 0ch -5
    public void setDx(int i) {
        this.dx = i;
    }
    public void setDy(int i) {
        this.dy = i;
    }
    public int getDx() {
        return dx;
    }
    public int getDy() {
        return dy;
    }
}
