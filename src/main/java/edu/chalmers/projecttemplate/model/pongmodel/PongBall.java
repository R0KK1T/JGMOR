package edu.chalmers.projecttemplate.model.pongmodel;

import java.util.Random;

public class PongBall extends GameObject implements IMovable {
    double xDirection,yDirection;
    double initialX, initialY;
    double angle;
    public PongBall (double x, double y, double height, double width, double velocity){
        this.x = x;
        this.y = y;
        initialX = x;
        initialY = y;
        this.height = height;
        this.width = width;
        this.velocity = velocity;
    }
    @Override
    public void updatePosition() {
        x += xDirection;
        y += yDirection;
    }

    public void resetBall() {
        x = initialX;
        y = initialY;
        Random random = new Random();
        angle = random.nextDouble() * (Math.PI / 1.8);
        //angle -= 30;
        angle -= Math.PI / 3.6;
        angle += Math.PI * random.nextInt((2));
        xDirection = velocity * Math.cos(angle);
        //dx = dx * (-1 + rand.nextInt(2));
        yDirection = velocity * Math.sin(angle);
    }

}
