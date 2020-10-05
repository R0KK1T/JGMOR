package edu.chalmers.projecttemplate.controller.pongcontroller;

import edu.chalmers.projecttemplate.model.pongmodel.PongModel;
import edu.chalmers.projecttemplate.model.spaceInvadersModel.SpaceInvadersModel;
import edu.chalmers.projecttemplate.view.pongview.PongView;
import edu.chalmers.projecttemplate.view.spaceInvadersView.SpaceInvadersView;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class PongController {
    AnimationTimer timer;
    PongModel model;
    PongView view;
    public PongController() throws Exception {
        model = new PongModel();
        view = new PongView(model.getSizeX(), model.getSizeY());

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

        //draw Ball
        view.draw(model.getPongBall().getX(), model.getPongBall().getY(), model.getPongBall().getWidth(), model.getPongBall().getHeight());

        //draw Right Paddle
        view.draw(model.getPongRightPaddle().getX(), model.getPongRightPaddle().getY(), model.getPongRightPaddle().getWidth(), model.getPongRightPaddle().getHeight());

        //draw Left Paddle
        view.draw(model.getPongLeftPaddle().getX(), model.getPongLeftPaddle().getY(), model.getPongLeftPaddle().getWidth(), model.getPongLeftPaddle().getHeight());

    }

    public void startGame(){
        timer.start();
    }

    public Scene getScene(){
        return view.getScene();
    }
    private void keyPressed(KeyEvent event) {
        KeyCode kc = event.getCode();
        switch (kc) {
            case W:
                break;
            case S:
                break;
            default:  // Nothing
        }
    }

    private void keyReleased(KeyEvent event) {
        KeyCode kc = event.getCode();
        switch (kc) {
            case S:
            case W:
                break;
            default: // Nothing
        }
    }
}
