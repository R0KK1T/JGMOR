package edu.chalmers.projecttemplate.model.pongmodel;

public class PongPaddle extends GameObject implements IMovable {
    private double maxY,minY;

    public PongPaddle (double x, double y, double height, double width, double maxY, double minY){
        super(x, y, height, width,0);
        this.maxY = maxY;
        this.minY = minY;
    }

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
    public void moveUp (double paddleVelocity){
        setVelocity(-paddleVelocity);
    }

    public void moveDown (double paddleVelocity){
        setVelocity(paddleVelocity);
    }

    public void stopMoving(){
        setVelocity(0);
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
