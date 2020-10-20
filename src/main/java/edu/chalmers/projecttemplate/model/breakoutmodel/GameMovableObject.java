package edu.chalmers.projecttemplate.model.breakoutmodel;
import edu.chalmers.projecttemplate.model.common.IPositionableInt;
import edu.chalmers.projecttemplate.model.common.IRectangle;
import edu.chalmers.projecttemplate.model.common.Rectangle;
/*
 * GameMovableObject is a super class for all movable object.
 */
public abstract class GameMovableObject implements IPositionableInt {
    private IRectangle hitbox;
    private int dx;
    private int dy;
    public GameMovableObject(int x, int y, int width, int height) {
       hitbox = new Rectangle(x, y, width, height);
       dx = 0;
       dy = 0;
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
    public void setDx(int i) {
        dx = i;
    }
    public void setDy(int i) {
        dy = i;
    }
    public int getDx() {
        return dx;
    }
    public int getDy() {
        return dy;
    }
}
