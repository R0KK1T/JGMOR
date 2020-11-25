package edu.chalmers.projecttemplate.model.pongmodel;

/**
 * Represents the paddles for the game Pong
 */
public class PongPaddle extends GameObject implements IMovable {
    private double maxY,minY;

    /**
     * Constructs an instance of the PongBall with specified dimensions,
     * position and movement constraints
     * @param x the current (and initial) position on the x axis
     * @param y the current (and initial) position on the y axis
     * @param height the height of the object
     * @param width the width of the object
     * @param maxY the max value that y can have
     * @param minY the max value that y can have
     */
    public PongPaddle (double x, double y, double height, double width, double maxY, double minY){
        super(x, y, height, width,0);
        this.maxY = maxY;
        this.minY = minY;
    }

    /**
     * Updates the position of the paddle
     */
    @Override
    public void updatePosition() {
        setY(getVelocity()+getY());
        if (getY() < minY){
            setY(minY);
        }
        if ((getY() + getHeight()) > maxY){
            setY(maxY - getHeight());
        }
    }

    /**
     * Sets the velocity factor so that the Paddle moves upwards
     * @param paddleVelocity the velocity factor value
     */
    public void moveUp (double paddleVelocity){
        setVelocity(-paddleVelocity);
    }

    /**
     * Sets the velocity factor so that the Paddle moves downwards
     * @param paddleVelocity the velocity factor value
     */
    public void moveDown (double paddleVelocity){
        setVelocity(paddleVelocity);
    }

    /**
     * Sets the velocity factor to 0
     */
    public void stopMoving(){
        setVelocity(0);
    }

    public double getMaxY() {
        return maxY;
    }

    public double getMinY() {
        return minY;
    }
}
