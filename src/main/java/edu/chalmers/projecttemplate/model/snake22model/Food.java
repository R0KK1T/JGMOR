package edu.chalmers.projecttemplate.model.snake22model;

import java.util.List;
import java.util.Random;

public class Food {
    private int foodPositionX;
    private int foodPositionY;

    private GameConfiguration gc;
    private List<SnakePart> snakeParts;

    Random random = new Random();

    public Food(GameConfiguration gc, List<SnakePart> snakeParts) {
        this.gc = gc;
        this.snakeParts = snakeParts;
        generatePosition();
    }

    public void generatePosition(){
        boolean foodGeneratedOnSnake;

        do {
            foodGeneratedOnSnake = true;
            this.foodPositionX = random.nextInt((gc.getBoardWidth()/10)) * 10;
            this.foodPositionY = random.nextInt((gc.getBoardHeight()/10)) * 10;

            for (SnakePart snakePart : snakeParts) {
                if (snakePart.getSnakePartPositionX() == foodPositionX && snakePart.getSnakePartPositionY() == foodPositionY){
                    foodGeneratedOnSnake = false;
                    this.foodPositionX = random.nextInt((gc.getBoardWidth()/10)) * 10;
                    this.foodPositionY = random.nextInt((gc.getBoardHeight()/10)) * 10;
                    System.out.println("Food generated on snake!");
                    break;
                }
            }
        } while (!foodGeneratedOnSnake);

    }

    public int getFoodPositionX() {
        return foodPositionX;
    }

    public int getFoodPositionY() {
        return foodPositionY;
    }
}
