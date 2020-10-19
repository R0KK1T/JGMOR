package edu.chalmers.projecttemplate.model.pongmodel;

import edu.chalmers.projecttemplate.model.common.IPositionableDouble;

public abstract class GameObject implements IPositionableDouble {
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
        //Checks if another GameObject intersects with this one
        if (other.x >= x - other.getWidth() && other.x <= x + this.getWidth() && other.y >= y - other.getHeight() && other.y <= y + this.getWidth()){
            return true;
        }
        return false;
    }
}
