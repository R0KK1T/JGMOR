package edu.chalmers.projecttemplate.model.spaceInvadersModel;

import java.awt.*;

public abstract class MovableObject{

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
        return hitbox.x;
    }

    public int getYpos(){
        return hitbox.y;
    }

    public int getWidth(){
        return hitbox.width;
    }

    public int getHeight(){
        return hitbox.height;
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
        hitbox.x += deltaX;
    }

    protected void setY(int deltaY){
        hitbox.y += deltaY;
    }

}
