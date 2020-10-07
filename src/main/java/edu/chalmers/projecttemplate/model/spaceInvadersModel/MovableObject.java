package edu.chalmers.projecttemplate.model.spaceInvadersModel;

import edu.chalmers.projecttemplate.model.common.Rectangle;

public abstract class MovableObject implements IPositionable{

    private String type;
    private Rectangle hitbox;
    private int direction;
    private int speed;

    public MovableObject(int xPos, int yPos, int width, int height, int speed, String type) {
        this.speed = speed;
        hitbox = new Rectangle(xPos, yPos, width, height);
        this.type = type;
    }

    protected abstract void move();

    public int getXpos(){
        return hitbox.getX();
    }

    public int getYpos(){
        return hitbox.getY();
    }

    public int getWidth(){
        return hitbox.getWidth();
    }

    public int getHeight(){
        return hitbox.getHeight();
    }

    public String getType() {
        return type;
    }

    public int getSpeed(){
        return speed;
    }

    public int getDirection(){
        return direction;
    }

    protected void setDirection(int direction) {
        this.direction = direction;
    }

    protected void setX(int deltaX){
        hitbox.incX(deltaX);
    }

    protected void setY(int deltaY){
        hitbox.incY(deltaY);
    }

}
