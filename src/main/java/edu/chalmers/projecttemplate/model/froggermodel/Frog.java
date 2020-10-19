package edu.chalmers.projecttemplate.model.froggermodel;

import edu.chalmers.projecttemplate.model.common.Rectangle;

public class Frog implements IRepresentable{
    private Rectangle hitbox;
    private Obstacle riverObs;
    private int velocity;

    private boolean hasBounds = false;
    private int[][] bounds;
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

    public void setBounds(int xMin, int yMin, int xMax, int yMax){
        bounds = new int[2][2];
        bounds[0][0] = xMin;
        bounds[0][1] = yMin;
        bounds[1][0] = xMax;
        bounds[1][1] = yMax;
        hasBounds = true;
    }

    public void moveRight(){
        if(hasBounds){
            if(getX() + getWidth() + getVelocity() < bounds[1][0]){
                hitbox.incX(getVelocity());
            }
            else{
                hitbox.incX(bounds[1][0] - (getX() + getWidth()));
            }
        }
        else{
            hitbox.incX(getVelocity());
        }
    }
    public void moveLeft(){
        if(hasBounds){
            if(getX() - getVelocity() >= bounds[0][0]){
                hitbox.decX(getVelocity());
            }
            else{
                hitbox.decX(getX() - bounds[0][0]);
            }
        }
        else{
            hitbox.decX(getVelocity());
        }
    }
    public void moveUp(){
        if(hasBounds){
            if(getY() - getVelocity() >= bounds[0][1]){
                hitbox.decY(getVelocity());
            }
            else{
                hitbox.decY(getY() - bounds[0][1]);
            }
        }
        else{
            hitbox.decY(getVelocity());
        }

    }
    public void moveDown(){
        if(hasBounds){
            if(getY() + getHeight() + getVelocity() < bounds[1][1]){
                hitbox.incY(getVelocity());
            }
            else{
                hitbox.incY(bounds[1][1] - (getY() + getHeight()));
            }
        }
        else{
            hitbox.incY(getVelocity());
        }
    }

    private void moveWhileAttached(){
        if(hasBounds){
            if(riverObs.getVelocity() > 0){
                if(getX() + getWidth() + riverObs.getVelocity() < bounds[1][0]){
                    hitbox.incX(riverObs.getVelocity());
                }
                else{
                    hitbox.incX(bounds[1][0] - (getX() + getWidth()));
                }
            }
            else{
                if(getX() + riverObs.getVelocity() >= bounds[0][0]){
                    hitbox.incX(riverObs.getVelocity());
                }
                else{
                    hitbox.decX(getX() - bounds[0][0]);
                }
            }
        }
        else {
            hitbox.incX(riverObs.getVelocity());
        }
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
