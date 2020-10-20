package edu.chalmers.projecttemplate.model.pongmodel;

import java.util.Random;

public class PongBall extends GameObject implements IMovable {
    double xDirection,yDirection;
    double initialX, initialY;
    double angle;
    public PongBall (double x, double y, double height, double width, double velocity){
        super(x, y, height, width, velocity);
        initialX = x;
        initialY = y;
    }
    @Override
    public void updatePosition() {
        setX(getX() + xDirection);
        setY(getY() + yDirection);
    }

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
