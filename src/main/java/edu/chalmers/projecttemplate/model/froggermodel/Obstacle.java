package edu.chalmers.projecttemplate.model.froggermodel;

import edu.chalmers.projecttemplate.model.common.Rectangle;

public abstract class Obstacle {
    private Rectangle hitbox;

    public Obstacle(int x, int y, int width, int height){
        hitbox = new Rectangle(x, y, width, height);
    }
    
    //Very much alike Frog. Might be smart to refactor with superclass or other
    //In order to minimize duplication of code.
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
