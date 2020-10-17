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
import javafx.stage.Stage;
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
    public AnchorPane gamePane;
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
        breakoutGameViewManager = new BreakoutGameViewManager(gc, gameArea.getPrefWidth(), gameArea.getPrefHeight());
        buttonList = new ArrayList<>();
        addButtonToList();
        initButtonListeners();
        pause = true;
        inGame = true;
        start();
    }

    /*
     * 1. Button Exit
     */
    public void btnExitGameControl(MouseEvent mouseEvent) throws IOException {
        BreakoutMenuView newMenu = new BreakoutMenuView();
        Scene gameScene = newMenu.getMainScene();
        Stage gameStage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        gameStage.setScene(gameScene);
        gameStage.show();
    }
    /*
     * 2. Button Pause
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
    /*
     * 3. Paddle
     */
    //Draw paddle
    private void drawPaddle() {
        breakoutGameViewManager.drawPaddle(gameModel.getPaddle());
    }
    /*
     * 4. Ball
     */
    //Draw ball
    private void drawBall() {
        breakoutGameViewManager.drawBall(gameModel.getBall());
    }
    /*
     * 5. Bricks
     */
    private void drawBrick() {
       for (int i=0; i<gameModel.getBrickList().size(); i++) {
           //Drawing brick
           if (gameModel.getBrickList().get(i).getBrickStatus())
                breakoutGameViewManager.drawBrick(gameModel.getBrickList().get(i));
       }
    }
    /*
     * Initialize listeners
     */
    private void initializeListeners() {
        gamePane.setOnKeyPressed(keyEvent -> {
            KeyCode key = keyEvent.getCode();
            if (key.equals(KeyCode.Q))
                gameModel.getPaddle().decX(10);
            if (key.equals(KeyCode.W))
                gameModel.getPaddle().decX(-10);
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
        gameModel.checkCollisionBallPaddle();
        gameModel.checkCollisionBallBrick();
        checkIsGameOver();
    }
    //Game drawing stuff
    public void render() {
        drawPaddle();
        drawBall();
        drawBrick();
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
    private void checkIsGameOver() {
        if (gameModel.gameIsOver()) {
            timer.stop();
            inGame = false;
        }
    }
}
