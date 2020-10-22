package edu.chalmers.projecttemplate.model.snake22model;

import edu.chalmers.projecttemplate.controller.snake22controller.GameConfiguration;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<SnakePart> snakeParts;
    private GameConfiguration gc;


    public Snake(GameConfiguration gameConfiguration) {
        this.snakeParts = new ArrayList<>();
        this.gc = gameConfiguration;
        snakeParts.add(new SnakePart(gameConfiguration.getStartPointX(),gameConfiguration.getStartPointY(),gameConfiguration.getStartDirection()));

        for (int i = 0; i < gc.getStartingSnakeLength(); i++) {
            addSnakePartToTail();
        }

    }

    public void addSnakePartToTail(){
        int lastSnakePartIndex = snakeParts.size()-1;
        int lastSnakePartPosX = snakeParts.get(lastSnakePartIndex).getSnakePartPositionX();
        int lastSnakePartPosY = snakeParts.get(lastSnakePartIndex).getSnakePartPositionY();
        Direction lastSnakePartDirection = snakeParts.get(lastSnakePartIndex).getCurrentDirection();

        snakeParts.add(new SnakePart(lastSnakePartPosX,lastSnakePartPosY,lastSnakePartDirection));
    }

    // getters and setters
    public List<SnakePart> getSnakeParts() {
        return snakeParts;
    }

    public SnakePart getSnakeHead(){
        return snakeParts.get(0);
    }
}
