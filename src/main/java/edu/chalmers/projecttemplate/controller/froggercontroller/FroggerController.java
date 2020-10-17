package edu.chalmers.projecttemplate.controller.froggercontroller;

import edu.chalmers.projecttemplate.controller.controllerInterface.IController;
import edu.chalmers.projecttemplate.model.froggermodel.FroggerModel;
import edu.chalmers.projecttemplate.model.froggermodel.IRepresentable;
import edu.chalmers.projecttemplate.view.froggerview.FroggerView;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class FroggerController implements IController {

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
        for (IRepresentable rep: model.getRepresents()) {
            view.draw(rep.getX(), rep.getY(), rep.getWidth(), rep.getHeight(), rep.getType());
        }
    }

    public void startGame(){
        //start updating the game
        timer.start();
        view.getScene().setOnKeyPressed(this::keyPressed);
        view.getScene().setOnKeyReleased(this::keyReleased);
    }

    public Scene getScene(){
        //returns the scene from FroggerView
        return view.getScene();
    }

    private void keyPressed(KeyEvent event) {
        KeyCode kc = event.getCode();
        switch (kc) {
            case UP:
                model.movePlayer(1);
                break;
            case DOWN:
                model.movePlayer(2);
                break;
            case RIGHT:
                model.movePlayer(3);
                break;
            case LEFT:
                model.movePlayer(4);
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
