package edu.chalmers.projecttemplate.model.snake22model;

import edu.chalmers.projecttemplate.controller.snake22controller.GameConfiguration;
import edu.chalmers.projecttemplate.controller.snake22controller.InputListener;

public class GameStateUpdater {
    private InputListener inputListener;
    private Snake snake;
    private GameConfiguration gc;

    public GameStateUpdater(InputListener inputListener, Snake snake, GameConfiguration gameConfiguration) {
        this.inputListener = inputListener;
        this.snake = snake;
        this.gc = gameConfiguration;
    }

    public void updateState() {
        updateSnakePartPosition();

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
