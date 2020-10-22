package edu.chalmers.projecttemplate.model.snake22model;

public class SnakePart {
    private int snakePartPositionX;
    private int snakePartPositionY;
    private Direction currentDirection;

    public SnakePart(int snakePartPositionX, int snakePartPositionY, Direction currentDirection) {
        this.snakePartPositionX = snakePartPositionX;
        this.snakePartPositionY = snakePartPositionY;
        this.currentDirection = currentDirection;
    }

    public int getSnakePartPositionX() {
        return snakePartPositionX;
    }

    public void setSnakePartPositionX(int snakePartPositionX) {
        this.snakePartPositionX = snakePartPositionX;
    }

    public int getSnakePartPositionY() {
        return snakePartPositionY;
    }

    public void setSnakePartPositionY(int snakePartPositionY) {
        this.snakePartPositionY = snakePartPositionY;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }
}
