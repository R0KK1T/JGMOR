package edu.chalmers.projecttemplate.controller.spaceInvadersController;

import edu.chalmers.projecttemplate.controller.controllerInterface.IController;
import edu.chalmers.projecttemplate.model.spaceInvadersModel.SpaceInvadersModel;
import edu.chalmers.projecttemplate.view.spaceInvadersView.SpaceInvadersView;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Represents the controller used for controlling the SpaceInvadersModel
 *
 */
public class SpaceInvadersController implements IController {
    SpaceInvadersModel model;
    SpaceInvadersView view;
    AnimationTimer timer;

    // direction int for knowing if the keys A and D are released properly
    int currentPlayerDir = 0;

    //bool for pause
    boolean paused = false;

    /**
     * Constructs the controller and initializes model, view and timer
     *
     */
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
                }
                model.update();
                callForRedraw();
            }
        };
    }

    /**
     * Used for redrawing the entire field in the view based on the state of model
     *
     */
    private void callForRedraw(){
        //clear
        view.clearDrawingArea();

        //draw all positionables in the game
        for (int i = 0; i < model.getRepresents().size(); i++) {
            view.draw(model.getRepresents().get(i).getX(), model.getRepresents().get(i).getY(),
                    model.getRepresents().get(i).getWidth(), model.getRepresents().get(i).getHeight(),
                    model.getRepresents().get(i).getType());
        }
        
        //draw player lives
        for (int i = 0; i < model.getLives(); i++) {
            view.draw(20 + 60*i, model.getWindowSizeY() - 40,40,20,"Spaceship" );
        }

        //display score
        view.displayScore(Integer.toString(model.getScore()));
    }

    /**
     * Starts the game by starting the timer and also initializes and connects keypresses to the scene of view
     *
     */
    public void startGame(){
        //start updating the game
        timer.start();
        view.getScene().setOnKeyPressed(this::keyPressed);
        view.getScene().setOnKeyReleased(this::keyReleased);
    }

    /**
     * Returns the scene that view is responsible for
     *
     * @return scene from view
     */
    public Scene getScene(){
        //returns the scene from SpaceInvadersView
        return view.getScene();
    }

    /**
     * Pauses the game by stopping the timer updating it and also tells the view to draw a pause screen
     *
     */
    private void pauseGame(){
        //TODO call view and tell it to draw pause method
        paused = true;
        timer.stop();
    }

    /**
     * Unpauses the game by starting the timer
     *
     */
    private void unpauseGame(){
        paused = false;
        timer.start();
    }

    /**
     * Checks for certain keys being pressed down and performing appropriate action if true
     *
     * @param event KeyEvent for key being pressed
     */
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

    /**
     * Checks for certain keys being released and performing appropriate action if true
     *
     * @param event KeyEvent for key being released
     */
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
