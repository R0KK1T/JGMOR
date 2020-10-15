package edu.chalmers.projecttemplate.controller.froggercontroller;

import edu.chalmers.projecttemplate.model.froggermodel.FroggerModel;
import edu.chalmers.projecttemplate.model.froggermodel.IPositionable;
import edu.chalmers.projecttemplate.view.froggerview.FroggerView;
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
        for (IPositionable pos: model.getPositionables()) {
            view.draw(pos.getX(), pos.getY(), pos.getWidth(), pos.getHeight());
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
            case UP:
                model.getPlayer().moveUp();
                break;
            case DOWN:
                model.getPlayer().moveDown();
                break;
            case RIGHT:
                model.getPlayer().moveRight();
                break;
            case LEFT:
                model.getPlayer().moveLeft();
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
