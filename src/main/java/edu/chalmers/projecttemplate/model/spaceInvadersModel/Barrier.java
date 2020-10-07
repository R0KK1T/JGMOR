package edu.chalmers.projecttemplate.model.spaceInvadersModel;

import edu.chalmers.projecttemplate.model.common.Rectangle;

public class Barrier implements IPositionable{
    private int health;
    private Rectangle hitbox;
    private String type;

    public Barrier(int xPos, int yPos, int width, int height) {
        health = 10;
        type = "Barrier";
        hitbox = new Rectangle(xPos, yPos, width, height);
    }

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


}
