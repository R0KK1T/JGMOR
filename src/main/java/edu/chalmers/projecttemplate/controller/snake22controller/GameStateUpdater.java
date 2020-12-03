package edu.chalmers.projecttemplate.controller.snake22controller;

import edu.chalmers.projecttemplate.model.snake22model.*;
import edu.chalmers.projecttemplate.view.snake22view.Snake22View;
import javafx.application.Platform;

public class GameStateUpdater {
    private InputListener inputListener;
    private Snake snake;
    private GameConfiguration gc;
    private CollisionDetectionModule collisionDetectionModule;

    public GameStateUpdater(InputListener inputListener, Snake snake, GameConfiguration gameConfiguration) {
        this.inputListener = inputListener;
        this.snake = snake;
        this.gc = gameConfiguration;
        this.collisionDetectionModule = new CollisionDetectionModule(Snake22View.gameConfiguration);
    }

    public boolean updateState(GameConfiguration gameConfiguration, Score gameScore, Food food) {
        updateSnakePartPosition();
        return updateCollisions(gameConfiguration, gameScore, food);
    }

    private boolean updateCollisions(GameConfiguration gameConfiguration, Score gameScore, Food food) {
        boolean isRunning = true;

        //collision detection
        if (collisionDetectionModule.detectWallCollision(snake)) {

            if (gameConfiguration.getGameOverOnWallCollision()) {
                isRunning = false;
                Platform.runLater(Snake22View::gameOverMenu); // normally can't open javaFX gui from new custom user thread. Need Platform.runLater method to do that
            } else {
                collisionDetectionModule.goFromOtherSideOnWallCollision(snake);
            }
        }

        if (collisionDetectionModule.detectOwnCollision(snake)) {
            isRunning = false;
            Platform.runLater(Snake22View::gameOverMenu);     // normally can't open javaFX gui from new custom user thread. Need Platform.runLater method to do that
        }

        collisionDetectionModule.detectFoodCollision(snake, gameScore, food);
        return isRunning;
    }

    public void updateSnakePartPosition() {
        if (!inputListener.isOppositeDirectionPressed()){
            snake.getSnakeHead().setCurrentDirection(inputListener.getDirection());
            inputListener.setDirectionLock(false);
        }
        inputListener.setOppositeDirectionPressed(false);

        int tempHeadPosX = snake.getSnakeHead().getSnakePartPositionX();
        int tempHeadPosY = snake.getSnakeHead().getSnakePartPositionY();

        for (int i = snake.getSnakeParts().size() - 1; i > 0; i--) {
            snake.getSnakeParts().get(i).setSnakePartPositionX(snake.getSnakeParts().get(i - 1).getSnakePartPositionX());
            snake.getSnakeParts().get(i).setSnakePartPositionY(snake.getSnakeParts().get(i - 1).getSnakePartPositionY());
        }

        switch (snake.getSnakeHead().getCurrentDirection()) {
            case UP:
                snake.getSnakeHead().setSnakePartPositionY(tempHeadPosY - gc.getSnakePartSize());
                break;
            case RIGHT:
                snake.getSnakeHead().setSnakePartPositionX(tempHeadPosX + gc.getSnakePartSize());
                break;
            case DOWN:
                snake.getSnakeHead().setSnakePartPositionY(tempHeadPosY + gc.getSnakePartSize());
                break;
            case LEFT:
                snake.getSnakeHead().setSnakePartPositionX(tempHeadPosX - gc.getSnakePartSize());
                break;
        }
    }
}
