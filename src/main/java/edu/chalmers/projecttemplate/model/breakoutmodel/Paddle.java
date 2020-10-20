package edu.chalmers.projecttemplate.model.breakoutmodel;
/*
 * Model for Breakout paddle - The paddle is controlled with left and right arrow keys.
 */
public class Paddle extends GameMovableObject {
    private int dx;

    public Paddle(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    /*
     * The paddle moves only in the horizontal direction, so we only update the x
     */
    @Override
    protected void move() {
        this.setX(this.getVelocity());
    }
    public void moveRight() {
        this.setVelocity(5);
    }
    public void moveLeft() {
        this.setVelocity(-5);
    }

}
