package edu.chalmers.projecttemplate.model.snake22model;

public class GameConfiguration {
    private int snakePartSize = 10;
    private int boardWidth = 640;
    private int boardHeight = 640;
    private int gameSpeed = 80;    // milis for game loop thread sleep
    private int startPointX = boardWidth/2;
    private int startPointY = boardHeight/2;
    private Direction startDirection = Direction.RIGHT;
    private int startingSnakeLength = 5;
    private boolean gameOverOnWallCollision = false;


    public int getSnakePartSize() {
        return snakePartSize;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public int getGameSpeed() {
        return gameSpeed;
    }

    public int getStartPointX() {
        return startPointX;
    }

    public int getStartPointY() {
        return startPointY;
    }

    public Direction getStartDirection() {
        return startDirection;
    }

    public int getStartingSnakeLength() {
        return startingSnakeLength;
    }

    public boolean getGameOverOnWallCollision() {
        return gameOverOnWallCollision;
    }

    public void setGameSpeed(int gameSpeed) {
        this.gameSpeed = gameSpeed;
    }

    public void setGameOverOnWallCollision(boolean gameOverOnWallCollision) {
        this.gameOverOnWallCollision = gameOverOnWallCollision;
    }

    public void setBoardWidth(int boardWidth) {
        this.boardWidth = boardWidth;
    }

    public void setBoardHeight(int boardHeight) {
        this.boardHeight = boardHeight;
    }
}
