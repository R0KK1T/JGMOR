package edu.chalmers.projecttemplate.model.snakemodel;

import java.util.List;
import java.util.Random;

public class Food {

    private int foodPositionX;
    private int foodPositionY;

    Random random = new Random();

    public void  generatePosition(){
        //Food can be generated on top of Snake's position.
    }

    public int getFoodPositionX(){return foodPositionX;}

    public int getFoodPositionY(){return foodPositionY;}
}