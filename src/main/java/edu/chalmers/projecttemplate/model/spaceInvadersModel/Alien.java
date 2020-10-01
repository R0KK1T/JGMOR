package edu.chalmers.projecttemplate.model.spaceInvadersModel;

public class Alien extends MovableObject {

    public Alien(int xPos, int yPos, int size, int speed) {
        super(xPos, yPos, size, size, speed, "Alien");
        setDirection(1);
    }

    @Override
    public void move() {
        setX(getDirection() * getSpeed());
    }

    public void moveDown(){
        setY(getSpeed() + getHeight());
        setDirection(getDirection() * -1);
    }


}
