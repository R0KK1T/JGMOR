package edu.chalmers.projecttemplate.model.froggermodel;

import edu.chalmers.projecttemplate.model.common.IRectangle;
import edu.chalmers.projecttemplate.model.common.IRepresentable;
import edu.chalmers.projecttemplate.model.common.Rectangle;

public class Obstacle implements IRepresentable {
    private IRectangle hitbox;
    private int velocity;
    private ObstacleType type;

    public Obstacle(int x, int y, int width, int height, int velocity, ObstacleType type){
        hitbox = new Rectangle(x, y, width, height);
        this.velocity = velocity;
        this.type = type;
    }

    public IRectangle getHitbox() {
        return hitbox;
    }
    public int getX(){ return getHitbox().getX(); }
    public int getY(){ return getHitbox().getY(); }
    public int getWidth(){ return getHitbox().getWidth(); }
    public int getHeight(){ return getHitbox().getHeight(); }
    public int getVelocity(){ return velocity;}
    public String getType(){
        return type.toString();
    }

    public void move(){
        hitbox.incX(getVelocity());
    }
    public void moveTo(int x){
        if(x > getX()){
            hitbox.incX(x - getX());
        }
        else if (x < getX()){
            hitbox.decX(getX() - x);
        }
    }
}
