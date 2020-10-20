package edu.chalmers.projecttemplate.model.breakoutmodel;
import edu.chalmers.projecttemplate.model.common.IPositionableInt;
import edu.chalmers.projecttemplate.model.common.IRectangle;
import edu.chalmers.projecttemplate.model.common.Rectangle;

public abstract class GameMovableObject implements IPositionableInt {
    private IRectangle hitbox;
    private int velocity;
    public GameMovableObject(int x, int y, int width, int height) {
       hitbox = new Rectangle(x, y, width, height);
       velocity = 0;
    }
    protected abstract void move();

    @Override
    public int getX() {
        return hitbox.getX();
    }

    @Override
    public int getY() {
        return hitbox.getY();
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
    public void setX(int i) {
        hitbox.incX(i);
    }
    public void setY(int i) {
        hitbox.incY(i);
    }
    public void initX(int i) { hitbox.setX(i);}
    public void initY(int i) { hitbox.setY(i);}
    public void setVelocity(int i) {
        velocity = i;
    }
    public int getVelocity() {
        return velocity;
    }
}
