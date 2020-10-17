package edu.chalmers.projecttemplate.model.froggermodel;

import edu.chalmers.projecttemplate.model.common.Rectangle;

public class Frog implements IRepresentable{
    private Rectangle hitbox;
    private Obstacle riverObs;
    private int velocity;
    public Frog(int x, int y, int width, int height, int velocity){
        hitbox = new Rectangle(x, y, width, height);
        this.velocity = velocity;
    }

    @Override
    public String toString() {
        return "FROG";
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
    public int getX(){ return getHitbox().getX(); }
    public int getY(){ return getHitbox().getY(); }
    public int getWidth(){ return getHitbox().getWidth(); }
    public int getHeight(){ return getHitbox().getHeight(); }
    public String getType(){
        return this.toString();
    }
    public int getVelocity(){ return velocity; }

    public void moveRight(){
        hitbox.incX(getVelocity());
    }
    public void moveLeft(){
        hitbox.decX(getVelocity());
    }
    public void moveUp(){
        hitbox.decY(getVelocity());
    }
    public void moveDown(){
        hitbox.incY(getVelocity());
    }

    private void moveWhileAttached(){
        hitbox.incX(riverObs.getVelocity());
    }
    public void update(){
        if(riverObs != null){
            if(hitbox.intersect(riverObs.getHitbox())){
                moveWhileAttached();
            }
            else{
                detach();
            }
        }
    }
    public void attach(Obstacle obs){riverObs = obs;}
    private void detach(){
        riverObs = null;
    }
    public Obstacle getRiverObs() {
        return riverObs;
    }
}
