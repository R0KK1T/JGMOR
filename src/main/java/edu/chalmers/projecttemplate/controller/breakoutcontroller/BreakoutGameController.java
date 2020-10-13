package edu.chalmers.projecttemplate.controller.breakoutcontroller;

import edu.chalmers.projecttemplate.model.breakoutmodel.*;
import edu.chalmers.projecttemplate.view.breakoutview.BreakoutMenuView;
import edu.chalmers.projecttemplate.view.breakoutview.BreakoutViewManager;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
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
    private Wall gameModel;
    private Paddle paddle;
    private Ball ball;
    private BreakoutViewManager breakoutViewManager;
    private AnimationTimer timer;
    private boolean pause;
    //constructor
    public BreakoutGameController() {}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        paddle = new Paddle(420, 538, 60, 12);
        ball = new Ball(432, 507, 25, 25);
        gameModel = new Wall();
        breakoutViewManager = BreakoutViewManager.getInstance();
        pause = true;
        try {
            renderTheGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
        run();
    }

    /*
     * 1. Button Exit
     */
    public void btnExitGameControl(ActionEvent mouseEvent) throws IOException {
        BreakoutMenuView newMenu = new BreakoutMenuView();
        Scene gameScene = newMenu.getMainScene();
        Stage gameStage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        gameStage.setScene(gameScene);
        gameStage.show();
    }
    /*
     * 2. Button Pause
     */
    public void btnExitPauseControl(ActionEvent actionEvent) {
        btnGamePause.setOnMouseClicked(mouseEvent -> {
            if (pause) {
                timer.stop();
                pause = false;
                btnGamePause.setText("PLAY");
            }
            else {
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
    private void drawPaddle() throws IOException {
        breakoutViewManager.drawPaddle(paddle);
    }
    /*
     * 4. Ball
     */
    //Draw ball
    private void drawBall() throws IOException {
        breakoutViewManager.drawBall(ball);
    }
    /*
     * 5. Bricks
     */
    private void drawBrick() throws IOException {
        for (int i=0; i<gameModel.getBrickList().size(); i++) {
            //Drawing brick
            breakoutViewManager.drawBrick(gameModel.getBrickList().get(i));
            //Adding brick to the game field
            gameArea.getChildren().add(gameModel.getBrickList().get(i));
        }
    }
    /*
     * Initialize listeners
     */
    private void initializeListeners() {
        gamePane.setOnKeyPressed(keyEvent -> {
            KeyCode key = keyEvent.getCode();
            if (key.equals(KeyCode.LEFT))
                paddle.setDx(-1);
            if (key.equals(KeyCode.RIGHT))
                paddle.setDx(1);
        });
        gamePane.setOnKeyReleased(keyEvent -> {
            KeyCode key = keyEvent.getCode();
            if (key.equals(KeyCode.LEFT))
                paddle.setDx(0);
            if (key.equals(KeyCode.RIGHT))
                paddle.setDx(0);
        });
    }
    /*
     * Render the game
     */
    private void renderTheGame() throws IOException {
        //Drawing and adding paddle to the game field
        drawPaddle();
        gameArea.getChildren().add(paddle);
        //Drawing and adding ball to the game field
        drawBall();
        gameArea.getChildren().add(ball);
        //Drawing and adding brick to the game field
        drawBrick();
        //Init game listeners
        initializeListeners();
    }

    /*
     * Run the game
     */
    private void run() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                paddle.move();
                ball.move();
            }
        };
        timer.start();
    }

}
