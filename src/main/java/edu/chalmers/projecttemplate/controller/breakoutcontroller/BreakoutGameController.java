package edu.chalmers.projecttemplate.controller.breakoutcontroller;

import edu.chalmers.projecttemplate.controller.controllerInterface.IController;
import edu.chalmers.projecttemplate.model.breakoutmodel.*;
import edu.chalmers.projecttemplate.view.breakoutview.BreakoutGameView;
import edu.chalmers.projecttemplate.view.breakoutview.BreakoutGameViewManager;
import edu.chalmers.projecttemplate.view.breakoutview.BreakoutMenuView;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
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
    @FXML public AnchorPane gamePane;
    @FXML public Label playerName;
    private GameModel gameModel;
    private BreakoutGameViewManager breakoutGameViewManager;
    private Canvas canvas;
    private GraphicsContext gc;
    private AnimationTimer timer;
    private boolean pause;
    private boolean inGame;
    private List<Button> buttonList;
    //constructor
    public BreakoutGameController()  {}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gameModel = new GameModel();
        canvas = new Canvas(gameArea.getPrefWidth(), gameArea.getPrefHeight());
        gc = canvas.getGraphicsContext2D();
        gameArea.getChildren().add(canvas);
        try {
            breakoutGameViewManager = new BreakoutGameViewManager(gc, gameArea.getPrefWidth(), gameArea.getPrefHeight());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
    public void btnExitPauseControl(MouseEvent actionEvent) {
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
    private void callForRedraw() throws FileNotFoundException {
        for (int i=0; i < gameModel.getRepresents().get(0).size(); i++) {
            breakoutGameViewManager.drawPaddle(gameModel.getRepresents().get(0).get(i));
        }
    }
    /*
     * Drawing paddle
     */
    private void drawPaddle() throws FileNotFoundException {
       // breakoutGameViewManager.drawPaddle(gameModel.getPaddle());
    }
    /*
     * Drawing ball
     */
    private void drawBall() throws FileNotFoundException {
        //breakoutGameViewManager.drawBall(gameModel.getBall());
    }
    /*
     * Drawing Bricks
     */
    private void drawBrick() throws FileNotFoundException {
      /* for (int i=0; i<gameModel.getBrickList().size(); i++) {
           //Drawing brick
           if (gameModel.getBrickList().get(i).getBrickStatus())
                breakoutGameViewManager.drawBrick(gameModel.getBrickList().get(i));
       }*/
    }
    /*
     * Show the player's firstname and last name
     */
    private void showPlayer() {
        //Get user's info from menu controller
        String firstName = BreakoutMenuController.userInfo.get(0);
        String lastName = BreakoutMenuController.userInfo.get(1);
        //set user's info to player
      /*  gameModel.getPlayer().setFirstName(firstName);
        gameModel.getPlayer().setLastName(lastName);*/
        //Concatenating the first-and last name in one sentence
        //String name = gameModel.getPlayer().getFirstName() + ", "+gameModel.getPlayer().getLastName();
       // playerName.setText(name);
    }
    /*
     * Show the current score while playing the game
     */
    private void showTheScore() {
        //scoreLabel.setText(String.valueOf(gameModel.getPlayer().getMyScore()));
    }
    /*
     * Initialize listeners
     */
    private void initializeListeners() {
        gamePane.setOnKeyPressed(keyEvent -> {
            KeyCode key = keyEvent.getCode();
            //if (key.equals(KeyCode.Q))
                //gameModel.getPaddle().decX(10);
            //if (key.equals(KeyCode.W))
                //gameModel.getPaddle().decX(-10);
        });
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
     * Creating and processing the game
     */
    //Game start
    public void start() {
        run();
    }
    //Game initialize
    public void init() { breakoutGameViewManager.drawGameArea(); }
    //Game moving stuff
    public void tick() {
        initializeListeners();
        gameModel.tick();
        //gameModel.checkCollisionBallPaddle();
        //gameModel.checkCollisionBallBrick();
        showTheScore();
        checkIsGameOver();
    }
    //Game drawing stuff
    public void render() throws FileNotFoundException {
        drawPaddle();
        drawBall();
        drawBrick();
        callForRedraw();
    }
    //Game run
    private void run() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    init();
                    render();
                    tick();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.start();
    }
    //Game over
    private void checkIsGameOver() {
       /* if (gameModel.gameIsOver()) {
            timer.stop();
            inGame = false;
        }*/
    }
}
