package edu.chalmers.projecttemplate.model.breakoutmodel;
/*
 * Model for Breakout paddle - The paddle is controlled with Q (move to left) and W (move to right).
 */
public class Paddle extends GameMovableObject {
    public Paddle(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    /*
     * The paddle moves only in the horizontal direction, so we only update the x
     */
    @Override
    protected void move() {
        this.setX(this.getDx());
    }
    public void moveRight() {
        this.setDx(3);
    }
    public void moveLeft() {
        this.setDx(-3);
    }

}
