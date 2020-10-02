package edu.chalmers.projecttemplate.model.froggermodel;

import edu.chalmers.projecttemplate.model.common.Rectangle;

public class Frog {
    private Rectangle hitbox;
    public Frog(int x, int y, int width, int height){
        hitbox = new Rectangle(x, y, width, height);
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
    public int getX(){ return getHitbox().getX(); }
    public int getY(){ return getHitbox().getY(); }
    public int getWidth(){ return getHitbox().getWidth(); }
    public int getHeight(){ return getHitbox().getHeight(); }

    public void moveRight(){
        hitbox.incX();
    }
    public void moveLeft(){
        hitbox.decX();
    }
    public void moveUp(){
        hitbox.decY();
    }
    public void moveDown(){
        hitbox.incY();
    }
}
