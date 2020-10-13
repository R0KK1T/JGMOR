package edu.chalmers.projecttemplate.controller.froggercontroller;

import edu.chalmers.projecttemplate.model.froggermodel.FroggerModel;
import edu.chalmers.projecttemplate.model.spaceInvadersModel.SpaceInvadersModel;
import edu.chalmers.projecttemplate.view.froggerview.FroggerView;
import edu.chalmers.projecttemplate.view.spaceInvadersView.SpaceInvadersView;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class FroggerController {

    FroggerModel model;
    FroggerView view;
    AnimationTimer timer;

    public FroggerController() throws Exception {
        //Create instance of view and model
        model = new FroggerModel();
        view = new FroggerView(model.getWindowSizeX(), model.getWindowSizeY());

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

        //draw all objects in the game
        for (int i = 0; i < model.getLanes().size(); i++) {
            for (int j = 0; j < model.getLanes().get(i).getObstacles().size(); j++) {
                view.draw(model.getLanes().get(i).getObstacles().get(j).getX(),
                        model.getLanes().get(i).getObstacles().get(j).getY(),
                        model.getLanes().get(i).getObstacles().get(j).getWidth(),
                        model.getLanes().get(i).getObstacles().get(j).getHeight());
            }
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
                break;
            case D:
                break;
            case SPACE:
                break;
            default:
        }
    }

    private void keyReleased(KeyEvent event) {
        KeyCode kc = event.getCode();
        switch (kc) {
            case A:
                break;
            case D:
                break;
            default:
        }
    }

}
