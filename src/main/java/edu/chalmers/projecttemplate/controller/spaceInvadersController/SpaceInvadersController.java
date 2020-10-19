package edu.chalmers.projecttemplate.controller.spaceInvadersController;

import edu.chalmers.projecttemplate.controller.controllerInterface.IController;
import edu.chalmers.projecttemplate.model.spaceInvadersModel.SpaceInvadersModel;
import edu.chalmers.projecttemplate.view.spaceInvadersView.SpaceInvadersView;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class SpaceInvadersController implements IController {
    SpaceInvadersModel model;
    SpaceInvadersView view;
    AnimationTimer timer;

    // direction int for knowing if the keys A and D are released properly
    int currentPlayerDir = 0;

    //bool for pause
    boolean paused = false;

    public SpaceInvadersController() throws Exception {
        //Create instance of view and model
        model = new SpaceInvadersModel();
        view = new SpaceInvadersView(model.getWindowSizeX(), model.getWindowSizeY());

        //initialize the timer
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //if game over then save score and then pause game
                if (model.getGameOver()){
                    //TODO save score
                    timer.stop();
                }
                model.update();
                callForRedraw();
            }
        };
    }

    private void callForRedraw(){
        //clear
        view.clearDrawingArea();

        //draw all positionables in the game
        for (int i = 0; i < model.getGameObjects().size(); i++) {
            view.draw(model.getGameObjects().get(i).getX(), model.getGameObjects().get(i).getY(),
                    model.getGameObjects().get(i).getWidth(), model.getGameObjects().get(i).getHeight(),
                    model.getGameObjects().get(i).getType());
        }
        
        //draw player lives
        for (int i = 0; i < model.getLives(); i++) {
            view.draw(20 + 60*i, model.getWindowSizeY() - 40,40,20,"Spaceship" );
        }

        //display score
        view.displayScore(Integer.toString(model.getScore()));
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

    private void pauseGame(){
        //TODO call view and tell it to draw pause method
        paused = true;
        timer.stop();
    }

    private void unpauseGame(){
        paused = false;
        timer.start();
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
            case ESCAPE:
                if (paused){
                    unpauseGame();
                }
                else{
                    pauseGame();
                }
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
