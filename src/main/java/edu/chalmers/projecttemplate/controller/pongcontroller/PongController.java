package edu.chalmers.projecttemplate.controller.pongcontroller;

import edu.chalmers.projecttemplate.controller.controllerInterface.IController;
import edu.chalmers.projecttemplate.model.pongmodel.PongModel;
import edu.chalmers.projecttemplate.view.pongview.PongView;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class PongController implements IController {
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
                //view.setLeftScoreText(String.valueOf(model.getLeftPlayerScore()));
                //view.setRightScoreText(String.valueOf(model.getLeftPlayerScore()));
            }
        };
    }

    private void callForRedraw(){
        //clear
        view.clearDrawingArea();

        //draw all GameObjects in model
        for (int i = 0; i < model.getGameObjects().size(); i++){
            view.draw(model.getGameObjects().get(i).getX(), model.getGameObjects().get(i).getY(), model.getGameObjects().get(i).getWidth(), model.getGameObjects().get(i).getHeight());
        }

        /*//draw Ball
        view.draw(model.getPongBall().getX(), model.getPongBall().getY(), model.getPongBall().getWidth(), model.getPongBall().getHeight());

        //draw Right Paddle
        view.draw(model.getPongRightPaddle().getX(), model.getPongRightPaddle().getY(), model.getPongRightPaddle().getWidth(), model.getPongRightPaddle().getHeight());

        //draw Left Paddle
        view.draw(model.getPongLeftPaddle().getX(), model.getPongLeftPaddle().getY(), model.getPongLeftPaddle().getWidth(), model.getPongLeftPaddle().getHeight());
        */
    }

    public void startGame(){
        view.getScene().setOnKeyPressed(this::keyPressed);
        view.getScene().setOnKeyReleased(this::keyReleased);
        timer.start();
    }

    public Scene getScene(){
        return view.getScene();
    }
    private void keyPressed(KeyEvent event) {
        KeyCode kc = event.getCode();
        switch (kc) {
            case W:
                model.movePongLeftPaddleUp();
                break;
            case S:
                model.movePongLeftPaddleDown();
                break;
            case UP:
                if (model.isAIEnabled() == false) {
                    model.movePongRightPaddleUp();
                }
                break;
            case DOWN:
                if (model.isAIEnabled() == false) {
                    model.movePongRightPaddleDown();
                }
                break;
            case P:
                model.setAIEnabled(!model.isAIEnabled());
                model.stopPongRightPaddle();
                break;
            case ESCAPE:
                model.setPaused(!model.isPaused());
                break;
            default:  // Nothing
        }
    }

    private void keyReleased(KeyEvent event) {
        KeyCode kc = event.getCode();
        switch (kc) {
            case W:
                model.stopPongLeftPaddle();
                break;
            case S:
                model.stopPongLeftPaddle();
                break;
            case UP:
                if (model.isAIEnabled()) {
                    model.stopPongRightPaddle();
                }
                break;
            case DOWN:
                if (model.isAIEnabled()) {
                    model.stopPongRightPaddle();
                }
                break;
            default: // Nothing
        }
    }
}
