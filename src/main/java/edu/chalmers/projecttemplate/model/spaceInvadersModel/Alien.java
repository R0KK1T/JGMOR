package edu.chalmers.projecttemplate.model.spaceInvadersModel;

public class Alien extends MovableObject {

    public Alien(int xPos, int yPos, int size, int spaceToNext) {
        super(xPos, yPos, size, size, size + spaceToNext, "Alien");
        setDirection(1);
    }

    @Override
    public void move() {
        setX(getDirection() * getSpeed());
    }

    public void moveDown(){
        setY(getSpeed());
        setDirection(getDirection() * -1);
    }
}
