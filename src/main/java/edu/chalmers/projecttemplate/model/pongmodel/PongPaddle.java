package edu.chalmers.projecttemplate.model.pongmodel;

public class PongPaddle extends GameObject implements IMovable {

    public PongPaddle (double x, double y, double height, double width){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }
    public void setVelocity(double velocity){
        this.velocity = velocity;
    }
    @Override
    public void updatePosition() {
        y += velocity;
    }
}
