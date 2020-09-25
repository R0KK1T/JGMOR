package edu.chalmers.projecttemplate.model.pongmodel;

public abstract class GameObject implements IPositionable {
    protected double x,y;
    protected double height,width;
    protected double velocity;

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getWidth() {
        return width;
    }
    public boolean intersects (GameObject other){
        if (other.x >= x - other.getWidth() && other.x <= x + this.getWidth() && other.y >= y - other.getHeight() && other.y <= y + this.getWidth()){
            return true;
        }
        return false;
    }
}
