package edu.chalmers.projecttemplate.model.spaceInvadersModel;

public class Spaceship extends MovableObject{


    public Spaceship(int xPos, int yPos, int width, int height, int speed) {
        super(xPos, yPos, width, height, speed, "Spaceship");
    }

    @Override
    public void move() {
        setX(getDirection() * getSpeed());
    }

    public void shoot(){

    }

}
