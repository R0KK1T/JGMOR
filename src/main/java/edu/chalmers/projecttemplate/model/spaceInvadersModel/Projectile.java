package edu.chalmers.projecttemplate.model.spaceInvadersModel;

public class Projectile extends MovableObject {

    public Projectile(int xPos, int yPos,int direction) {
        super(xPos, yPos, 10, 20, 2, "Projectile");
        setDirection(direction);
    }

    @Override
    public void move() {
        //move in direction according to speed
        setY(getDirection() * getSpeed());
    }
}
