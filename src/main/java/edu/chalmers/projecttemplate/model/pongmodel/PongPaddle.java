package edu.chalmers.projecttemplate.model.pongmodel;

public class PongPaddle extends GameObject implements IMovable {
    private double maxY,minY;

    public PongPaddle (double x, double y, double height, double width, double maxY, double minY){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.maxY = maxY;
        this.minY = minY;
    }

    @Override
    public void updatePosition() {
        y += velocity;
        if (y < minY){
            y = 0;
        }
        if ((y + height) > maxY){
            y = maxY - height;
        }
    }
    public void moveUp (double paddleVelocity){
        setVelocity(-paddleVelocity);
    }

    public void moveDown (double paddleVelocity){
        setVelocity(paddleVelocity);
    }

    public void stopMoving(){
        setVelocity(0);
    }

    public void setVelocity(double velocity){
        this.velocity = velocity;
    }

    public void setMaxY(double maxY) {
        this.maxY = maxY;
    }

    public void setMinY(double minY) {
        this.minY = minY;
    }

    public double getMaxY() {
        return maxY;
    }

    public double getMinY() {
        return minY;
    }
}
