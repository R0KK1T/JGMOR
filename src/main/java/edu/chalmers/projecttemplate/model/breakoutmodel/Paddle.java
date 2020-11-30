package edu.chalmers.projecttemplate.model.breakoutmodel;
/*
 * Model for Breakout paddle - The paddle is controlled with Q (move to left) and W (move to right).
 */
public class Paddle extends GameMovableObject {
    /**
     * Constructs a paddle with specified position and size
     *
     * @param x the start x position of the paddle
     * @param y the start y position of the paddle
     * @param height the height of the paddle
     * @param width the width of the paddle
     */
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
