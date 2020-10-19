package edu.chalmers.projecttemplate.model.pongmodel;

import edu.chalmers.projecttemplate.model.common.IPositionableDouble;

public abstract class GameObject implements IPositionableDouble {
    private double x,y;
    private double height,width;
    private double velocity;

    public GameObject(double x, double y, double height, double width, double velocity){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.velocity = velocity;
    }

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

    public double getVelocity() {
        return velocity;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
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
