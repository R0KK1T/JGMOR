package edu.chalmers.projecttemplate.view.snake22view;

import edu.chalmers.projecttemplate.controller.snake22controller.GameStateUpdater;
import edu.chalmers.projecttemplate.controller.snake22controller.InputListener;
import edu.chalmers.projecttemplate.model.snake22model.*;

public class GameLoop implements Runnable {

    private boolean isRunning = true;
    private boolean isPaused = false;

    private final DrawModule drawModule;
    private final InputListener inputListener;
    private final GameStateUpdater gameStateUpdater;
    private final Snake snake;
    private final Food food;
    private final GameConfiguration gameConfiguration;
    private final Score gameScore;

    public GameLoop(DrawModule drawModule, InputListener inputListener) {
        this.drawModule = drawModule;
        this.inputListener = inputListener;
        this.snake = new Snake(Snake22View.gameConfiguration);
        this.food = new Food(Snake22View.gameConfiguration, snake.getSnakeParts());
        this.gameStateUpdater = new GameStateUpdater(inputListener, snake, Snake22View.gameConfiguration);
        this.gameConfiguration = Snake22View.gameConfiguration;
        this.gameScore = new Score();
    }

    @Override
    public void run() {

        // main game loop
        while (isRunning) {

            //read user input, update snake position, process collisions etc.
            isRunning = gameStateUpdater.updateState(gameConfiguration, gameScore, food);

            // draw/clear background
            drawModule.drawBackGround();
            drawModule.drawScore(gameScore);

            //draw snake based on updated state
            for (SnakePart snakePart : snake.getSnakeParts()) {
                drawModule.drawSnakePart(snakePart.getSnakePartPositionX(), snakePart.getSnakePartPositionY());
            }

            //draw food
            drawModule.drawFood(food.getFoodPositionX(), food.getFoodPositionY());

            if (inputListener.getWasEscPressed()) {
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
