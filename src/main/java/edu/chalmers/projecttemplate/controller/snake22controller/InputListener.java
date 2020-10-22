package edu.chalmers.projecttemplate.controller.snake22controller;

import edu.chalmers.projecttemplate.model.snake22model.Direction;
import edu.chalmers.projecttemplate.view.snake22view.Snake22View;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class InputListener {
    private Scene scene;
    private Direction direction = Direction.RIGHT;
    private boolean oppositeDirectionPressed = false;
    private boolean directionLock = false;      // solves problem with snake biting itself during fast direction change (player changing direction faster, than position update). Drawback of direction lock, is that when too fast key presses, there will be no direction change at all.
    private boolean wasEscPressed;

    public InputListener(Scene scene) {
        this.scene = scene;
    }

    public void getUserInput() {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.UP && directionLock == false) {
                    if (direction == Direction.DOWN) {
                        oppositeDirectionPressed = true;
                    } else {
                        direction = Direction.UP;
                        directionLock = true;
                    }
                }

                if (keyEvent.getCode() == KeyCode.DOWN && directionLock == false) {
                    if (direction == Direction.UP) {
                        oppositeDirectionPressed = true;
                    } else {
                        direction = Direction.DOWN;
                        directionLock = true;
                    }
                }

                if (keyEvent.getCode() == KeyCode.LEFT && directionLock == false) {
                    if (direction == Direction.RIGHT) {
                        oppositeDirectionPressed = true;
                    } else {
                        direction = Direction.LEFT;
                        directionLock = true;
                    }
                }

                if (keyEvent.getCode() == KeyCode.RIGHT && directionLock == false) {
                    if (direction == Direction.LEFT) {
                        oppositeDirectionPressed = true;
                    } else {
                        direction = Direction.RIGHT;
                        directionLock = true;
                    }
                }

                if (keyEvent.getCode() == KeyCode.ESCAPE || keyEvent.getCode() == KeyCode.P) {
                    wasEscPressed = true;
                    Snake22View.pauseMenu();
                }
            }
        });
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean getWasEscPressed() {
        return wasEscPressed;
    }

    public boolean isOppositeDirectionPressed() {
        return oppositeDirectionPressed;
    }

    public boolean isDirectionLock() {
        return directionLock;
    }

    public void setDirectionLock(boolean directionLock) {
        this.directionLock = directionLock;
    }

    public void setOppositeDirectionPressed(boolean oppositeDirectionPressed) {
        this.oppositeDirectionPressed = oppositeDirectionPressed;
    }

    public void setWasEscPressed(boolean wasEscPressed) {
        this.wasEscPressed = wasEscPressed;
    }
}