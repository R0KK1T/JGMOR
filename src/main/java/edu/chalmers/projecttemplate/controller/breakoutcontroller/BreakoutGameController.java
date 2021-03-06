package edu.chalmers.projecttemplate.controller.breakoutcontroller;

import edu.chalmers.projecttemplate.model.breakoutmodel.*;
import edu.chalmers.projecttemplate.view.breakoutview.BreakoutGameViewManager;
import edu.chalmers.projecttemplate.view.breakoutview.BreakoutMenuView;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
/*
 * Controller for breakout game area
 */
public class BreakoutGameController implements Initializable {
    @FXML public Button btnGameExit;
    @FXML public Button btnGamePause;
    @FXML public AnchorPane gameArea;
    @FXML public Label scoreLabel;
    @FXML public Label playerName;
    private GameModel gameModel;
    private BreakoutGameViewManager viewManager;
    private AnimationTimer timer;
    private boolean pause;
    private boolean inGame;
    private List<Button> buttonList;
    //constructor
    public BreakoutGameController()  {}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            gameModel = new GameModel();
            viewManager = new BreakoutGameViewManager();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        gameArea.getChildren().add(viewManager.getCanvas());
        buttonList = new ArrayList<>();
        addButtonToList();
        initButtonListeners();
        showPlayer();
        pause = true;
        inGame = true;
        start();
    }
    /*
     * Button Exit
     */
    public void btnExitGameControl(MouseEvent mouseEvent) throws IOException {
        BreakoutMenuView newMenu = new BreakoutMenuView();
        Scene gameScene = newMenu.getMainScene();
        Stage gameStage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        gameStage.setScene(gameScene);
        gameStage.show();
    }
    /*
     * Button Pause
     */
    public void btnExitPauseControl() {
        btnGamePause.setOnMouseClicked(mouseEvent -> {
            if (pause && inGame) {
                timer.stop();
                pause = false;
                btnGamePause.setText("PLAY");
            }
            else if (!pause && inGame){
                timer.start();
                pause = true;
                btnGamePause.setText("PAUSE");
            }
        });
    }
    /*
     * Initialize listeners
     */
    public void onKeyPressed(KeyEvent keyEvent) {
        KeyCode kc = keyEvent.getCode();
        switch (kc) {
            case Q:
                gameModel.setPaddleMoveLeft();
                break;
            case W:
                gameModel.setPaddleMoveRight();
                break;
            case ESCAPE:
                break;
            default:
        }
    }

    public void onKeyReleased(KeyEvent keyEvent) {
        KeyCode kc = keyEvent.getCode();
        switch (kc) {
            case Q:
                gameModel.setPaddleStill();
                break;
            case W:
                gameModel.setPaddleStill();
                break;
            case ESCAPE:
                break;
            default:
        }
    }

    private void addButtonToList() {
        buttonList.add(btnGameExit);
        buttonList.add(btnGamePause);
    }
    private void initButtonListeners() {
        for (int i=0; i<2; i++) {
            int finalI = i;
            buttonList.get(i).setOnMouseEntered(mouseEvent -> buttonList.get(finalI).setEffect(new Glow()));
            buttonList.get(i).setOnMouseExited(mouseEvent -> buttonList.get(finalI).setEffect(null));
            buttonList.get(i).setCursor((Cursor.HAND));

        }
    }
    /*
     * Drawing paddle, ball & bricks
     */
    private void callForRedraw() {
        // Drawing paddle & ball
        for (int i = 0; i < gameModel.getMovableObject().size(); i++) {
            if (i==0)
                viewManager.drawPaddle(gameModel.getMovableObject().get(i));
            if (i==1)
                viewManager.drawBall(gameModel.getMovableObject().get(i));
        }
        // Drawing bricks
        for (int i=0; i < gameModel.getStaticObject().size(); i++) {
            if (gameModel.getStaticObject().get(i).getBrickStatus())
                viewManager.drawBrick(gameModel.getStaticObject().get(i));
        }

    }

    /*
     * Show the player's firstname and last name
     */
    private void showPlayer() {
        //Get user's info from menu controller
        String firstName = BreakoutMenuController.userInfo.get(0);
        String lastName = BreakoutMenuController.userInfo.get(1);
        //set user's info to player
        gameModel.getPlayer().setFirstName(firstName);
        gameModel.getPlayer().setLastName(lastName);
        //Concatenating the first-and last name in one sentence
        String name = gameModel.getPlayer().getFirstName() + ", "+gameModel.getPlayer().getLastName();
        playerName.setText(name);
    }
    /*
     * Show the current score while playing the game
     */
    private void showTheScore() {
        scoreLabel.setText(String.valueOf(gameModel.getPlayer().getMyScore()));
    }
    /*
     * Creating and processing the game
     */
    //Game start
    private void start() { run(); }
    //Game initialize
    private void init() { viewManager.drawGameArea(); }
    //Game moving stuff
    private void tick() {
        gameModel.tick();
        showTheScore();
        isGameOver();
        isGameFinish();
    }
    //Game drawing stuff
    private void render() {
        callForRedraw();
    }
    //Game run
    private void run() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                init();
                render();
                tick();
            }
        };
        timer.start();
    }
    //Game over
    private void isGameOver() {
       if (gameModel.gameIsOver()) {
            timer.stop();
            inGame = false;
            viewManager.drawGameOver();
            savePlayerScore();
        }
    }
    /*
     * Check if the player wins the game
     */
    private void isGameFinish() {
        if (gameModel.youWin()) {
            timer.stop();
            inGame = false;
            viewManager.drawGameFinish();
            savePlayerScore();
        }
    }
    /*
     * Saving the player's score when she/he won or lost the game
     */
    private void savePlayerScore() {
        gameModel.getPlayer().saveMyScore();
    }
}
