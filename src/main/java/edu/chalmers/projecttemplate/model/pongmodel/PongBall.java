package edu.chalmers.projecttemplate.model.pongmodel;

public class PongBall extends GameObject implements IMovable {
    double xDirection,yDirection;

    public PongBall (double x, double y, double height, double width, double velocity){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.velocity = velocity;
    }
    @Override
    public void updatePosition() {
        x += xDirection;
        y += yDirection;
    }

}
