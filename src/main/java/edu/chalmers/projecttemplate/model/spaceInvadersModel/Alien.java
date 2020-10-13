package edu.chalmers.projecttemplate.model.spaceInvadersModel;

import java.util.Random;

class Alien extends MovableObject{
    private int timeBetweenShots;
    private int timeSinceLastShot = 0;
    Random rng = new Random();

    protected Alien(int xPos, int yPos, int size, int speed) {
        super(xPos, yPos, size, size, speed, "Alien");
        setDirection(1);
        timeBetweenShots = rng.nextInt(3000 - 500 + 1) + 500;
    }

    @Override
    public void move() {
        //move in direction according to speed
        setX(getDirection() * getSpeed());
    }

    public void moveDown(){
        //move in direction according to speed
        setY(getSpeed() + getHeight());
        setDirection(getDirection() * -1);
    }

    public int getTimeBetweenShots() {
        return timeBetweenShots;
    }

    public int getTimeSinceLastShot() {
        return timeSinceLastShot;
    }

    public void resetTimeScinceLastShot(){
        timeSinceLastShot = 0;
        //TODO change time between shots after every shoot
    }

    public void incTimeScinceLastShot(){
        timeSinceLastShot++;
    }
}
