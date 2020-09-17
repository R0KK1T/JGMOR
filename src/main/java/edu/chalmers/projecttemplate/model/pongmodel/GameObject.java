package edu.chalmers.projecttemplate.model.pongmodel;

public abstract class GameObject implements IPositionable {
    private double x,y;
    private double height,width;

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
}
