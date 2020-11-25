package edu.chalmers.projecttemplate.model.pongmodel;

import java.util.Random;

/**
 * Represents the ball for the game Pong
 */
public class PongBall extends GameObject implements IMovable {
    double xDirection,yDirection;
    double initialX, initialY;
    double angle;

    /**
     * Constructs an instance of the PongBall with specified dimensions,
     * position and velocity
     * @param x the current (and initial) position on the x axis
     * @param y the current (and initial) position on the y axis
     * @param height the height of the object
     * @param width the width of the object
     * @param velocity the velocity factor of the object
     */
    public PongBall (double x, double y, double height, double width, double velocity){
        super(x, y, height, width, velocity);
        initialX = x;
        initialY = y;
    }

    /**
     * Updates the position of the ball
     */
    @Override
    public void updatePosition() {
        setX(getX() + xDirection);
        setY(getY() + yDirection);
    }

    /**
     * Resets the ball to its initial position and sets the direction to a random angle
     */
    public void resetBall() {
        //Resets the ball to the initial coordinates and sets the direction to a random one
        setX(initialX);
        setY(initialY);
        Random random = new Random();
        angle = random.nextDouble() * (Math.PI / 1.8);
        angle -= Math.PI / 3.6;
        angle += Math.PI * random.nextInt((2));
        xDirection = getVelocity() * Math.cos(angle);
        yDirection = getVelocity() * Math.sin(angle);
    }

}
