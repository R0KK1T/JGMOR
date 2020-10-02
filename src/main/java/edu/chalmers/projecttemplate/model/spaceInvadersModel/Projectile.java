package edu.chalmers.projecttemplate.model.spaceInvadersModel;

public class Projectile extends MovableObject {


    public Projectile(int xPos, int yPos, int width, int height, int speed) {
        super(xPos, yPos, width, height, speed, "Projectile");
    }

    @Override
    public void move() {
        //move in direction according to speed
        setY(getDirection() * getSpeed());
    }
}
