package edu.chalmers.projecttemplate.model.breakoutmodel;

import edu.chalmers.projecttemplate.model.common.IPositionableInt;
import edu.chalmers.projecttemplate.model.common.IRectangle;
import edu.chalmers.projecttemplate.model.common.Rectangle;

public class GameMovableObject implements IPositionableInt {
    private IRectangle hitbox;
    private int velocity;
    private int dt;
    public GameMovableObject(int x, int y, int width, int height, int velocity) {
       hitbox = new Rectangle(x, y, width, height);
       this.velocity = velocity;
       this.dt = 1;
    }

    @Override
    public int getX() {
        return hitbox.getX();
    }

    @Override
    public int getY() {
        return hitbox.getX();
    }

    @Override
    public int getWidth() {
        return hitbox.getWidth();
    }

    @Override
    public int getHeight() {
        return hitbox.getHeight();
    }

    public IRectangle getHitbox() {
        return hitbox;
    }
    public int getVelocity() {
        return velocity;
    }
    public void setX(int i) {
        hitbox.incX(i);
    }
    public void setY(int i) {
        hitbox.incY(i);
    }
}
