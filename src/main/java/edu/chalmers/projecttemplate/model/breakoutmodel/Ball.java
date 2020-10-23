package edu.chalmers.projecttemplate.model.breakoutmodel;
/*
 * Model for Breakout ball - The class has a move() method that moves the ball on the Board.
 * If the ball hits the borders, the directions are changed accordingly.
 */
public class Ball extends GameMovableObject{
    private int dt;

    public Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.setDx(1);
        this.setDy(-1);
        dt = 1;
    }
    /*
     * The ball bounces off the wall according to low of elastic collision
     */
    @Override
    protected void move() {
        //update position
        this.setX((getDx()*dt));
        this.setY((getDy()*dt));
    }
    /**
     * Reverse horizontal movement. If the ball was moving left change it to move
     * right and vice versa
     */
    public void reverseHorizontalMomentum() {
        setDx(-getDx());
    }

    /**
     * Reverse vertical movement. If the ball was moving upwards change it to move
     * downwards and vice versa
     */
    public void reverseVerticalMomentum() {
        setDy(-getDy());
    }
}
