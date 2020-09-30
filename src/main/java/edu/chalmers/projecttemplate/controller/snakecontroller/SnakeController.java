package edu.chalmers.projecttemplate.controller.snakecontroller;

import edu.chalmers.projecttemplate.model.snakemodel.Snake;

public class SnakeController {


    private boolean started = false;
    private boolean finished = false;
    private int score = 0;
    private int seconds = 0;
    private boolean pause = false;
    private boolean startMenu = true;

    private String menuSelection = "Start game";

    private Snake snake;
    private SnakeController snakeGame;


    public Snake getSnake() {
        return snake;
    }


    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public boolean isStarted() {
        return started;
    }

    public boolean isFinished() {
        return finished;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setSnakeGame(SnakeController snakeGame) {
        this.snakeGame = snakeGame;
    }

    public boolean isPause() {
        return pause;
    }


    public void setPause(boolean pause) {
        this.pause = pause;
    }


    public String getMenuSelection() {
        return menuSelection;
    }


    public void setMenuSelection(String menuSelection) {
        this.menuSelection = menuSelection;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }


    public boolean isStartmenu() {
        return startMenu;
    }

}
