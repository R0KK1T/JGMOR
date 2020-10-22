package edu.chalmers.projecttemplate.controller.froggercontroller;

import edu.chalmers.projecttemplate.controller.controllerInterface.IController;
import edu.chalmers.projecttemplate.model.froggermodel.FroggerFacade;
import edu.chalmers.projecttemplate.model.froggermodel.IRepresentable;
import edu.chalmers.projecttemplate.view.froggerview.FroggerView;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class FroggerController implements IController {

    private FroggerFacade model;
    private FroggerView view;
    private AnimationTimer timer;
    private boolean pause = false;

    public FroggerController() throws Exception {
        //Create instance of view and model
        model = new FroggerFacade();
        view = new FroggerView(model.getWindowSizeX(), model.getWindowSizeY());

        //initialize the timer
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(!pause){
                    model.update();
                    callForRedraw();
                }
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

        view.drawLives(model.getCurrentLifeCount());

        view.displayScore(model.getPoints(), model.getLevel());
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
        if(!pause){
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
    }

    private void keyReleased(KeyEvent event) {
        KeyCode kc = event.getCode();
        switch (kc) {
            case ESCAPE:
                pause = !pause;
                break;
            default:
        }
    }

}
