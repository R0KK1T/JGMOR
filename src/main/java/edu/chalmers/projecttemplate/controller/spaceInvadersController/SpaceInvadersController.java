package edu.chalmers.projecttemplate.controller.spaceInvadersController;

import edu.chalmers.projecttemplate.model.spaceInvadersModel.SpaceInvadersModel;
import edu.chalmers.projecttemplate.view.spaceInvadersView.SpaceInvadersView;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class SpaceInvadersController {
    SpaceInvadersModel model;
    SpaceInvadersView view;
    AnimationTimer timer;

    // direction int for knowing if the keys A and D are released properly
    int currentPlayerDir = 0;

    public SpaceInvadersController() throws Exception {
        //Create instance of view and model
        model = new SpaceInvadersModel();
        view = new SpaceInvadersView(model.getWindowSizeX(), model.getWindowSizeY());

        //initialize the timer
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                model.update();
                callForRedraw();
            }
        };
    }

    private void callForRedraw(){
        //clear
        view.clearDrawingArea();

        //draw all positionables in the game
        for (int i = 0; i < model.getPositionables().size(); i++) {
            view.draw(model.getPositionables().get(i).getXpos(), model.getPositionables().get(i).getYpos(),
                    model.getPositionables().get(i).getWidth(), model.getPositionables().get(i).getHeight(),
                    model.getPositionables().get(i).getType());
        }
    }

    public void startGame(){
        //start updating the game
        timer.start();
        view.getScene().setOnKeyPressed(this::keyPressed);
        view.getScene().setOnKeyReleased(this::keyReleased);
    }

    public Scene getScene(){
        //returns the scene from SpaceInvadersView
        return view.getScene();
    }

    private void keyPressed(KeyEvent event) {
        KeyCode kc = event.getCode();
        switch (kc) {
            case A:
                model.setPlayerDirection(-1);
                currentPlayerDir = -1;
                break;
            case D:
                model.setPlayerDirection(1);
                currentPlayerDir = 1;
                break;
            case SPACE:
                model.playerShoot();
                break;
            default:
        }
    }

    private void keyReleased(KeyEvent event) {
        KeyCode kc = event.getCode();
        switch (kc) {
            case A:
                if (currentPlayerDir == -1){
                    model.setPlayerDirection(0);
                }
                break;
            case D:
                if (currentPlayerDir == 1){
                    model.setPlayerDirection(0);
                }
                break;
            default:
        }
    }
}
