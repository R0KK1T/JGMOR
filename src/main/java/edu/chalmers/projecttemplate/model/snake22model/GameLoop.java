package edu.chalmers.projecttemplate.model.snake22model;

import edu.chalmers.projecttemplate.controller.snake22controller.GameConfiguration;
import edu.chalmers.projecttemplate.controller.snake22controller.InputListener;
import edu.chalmers.projecttemplate.view.snake22view.DrawModule;
import edu.chalmers.projecttemplate.view.snake22view.Snake22View;
import javafx.application.Platform;

public class GameLoop implements Runnable {
    private Thread currentThread;
    private boolean isRunning = true;
    private boolean isPaused = false;

    private DrawModule drawModule;
    private InputListener inputListener;
    private GameStateUpdater gameStateUpdater;
    private Snake snake;
    private Food food;
    private GameConfiguration gameConfiguration;
    private CollisionDetectionModule collisionDetectionModule;
    private Score gameScore;

    public GameLoop(DrawModule drawModule, InputListener inputListener, GameStateUpdater gameStateUpdater, Snake snake, Food food, GameConfiguration gameConfiguration, CollisionDetectionModule collisionDetectionModule, Score gameScore) {
        this.drawModule = drawModule;
        this.inputListener = inputListener;
        this.gameStateUpdater = gameStateUpdater;
        this.snake = snake;
        this.food = food;
        this.gameConfiguration = gameConfiguration;
        this.collisionDetectionModule = collisionDetectionModule;
        this.gameScore = gameScore;
    }

    @Override
    public void run() {
        this.currentThread = Thread.currentThread();

        // main game loop
        while (isRunning) {

            //read user input, update snake position etc.
            gameStateUpdater.updateState();

            //collision detection
            if (collisionDetectionModule.detectWallCollision()) {

                if (gameConfiguration.getGameOverOnWallCollision()) {
                    isRunning = false;
                    Platform.runLater(() -> Snake22View.gameOverMenu()); // normally can't open javaFX gui from new custom user thread. Need Platform.runLater method to do that
                } else {
                    collisionDetectionModule.goFromOtherSideOnWallCollision();
                }
            }

            if (collisionDetectionModule.detectOwnCollision()) {
                isRunning = false;
                Platform.runLater(() -> Snake22View.gameOverMenu());     // normally can't open javaFX gui from new custom user thread. Need Platform.runLater method to do that
            }

            if (collisionDetectionModule.detectFoodCollision()) {
                snake.addSnakePartToTail();
                gameScore.increaseScore();
                food.generatePosition();
            }

            // draw/clear background
            drawModule.drawBackGround();
            drawModule.drawScore();

            //draw snake based on updated state
            for (SnakePart snakePart : snake.getSnakeParts()) {
                drawModule.drawSnakePart(snakePart.getSnakePartPositionX(), snakePart.getSnakePartPositionY());
            }

            //draw food
            drawModule.drawFood(food.getFoodPositionX(), food.getFoodPositionY());

            if (inputListener.getWasEscPressed() == true) {
                isPaused = true;
                inputListener.setWasEscPressed(false);

                while (isPaused) {
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                    }
                }
            }

            try {
                Thread.sleep(gameConfiguration.getGameSpeed());
            } catch (InterruptedException e) {
            }
        }
    }

    // getters and setters
    public void setRunning(boolean running) {
        isRunning = running;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }
}
