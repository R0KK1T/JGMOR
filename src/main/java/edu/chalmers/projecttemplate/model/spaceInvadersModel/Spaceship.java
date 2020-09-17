package edu.chalmers.projecttemplate.model.spaceInvadersModel;

import java.awt.*;

public class Spaceship extends Movables{

    private int moveSpeed;
    private int currentSpeed;



    @Override
    public void move() {
        position.x += currentSpeed;
    }

    public void setSpeed(int direction){
        currentSpeed = moveSpeed * direction;
    }


}
