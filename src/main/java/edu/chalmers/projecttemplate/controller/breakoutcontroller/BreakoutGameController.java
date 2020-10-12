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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    int x;
    AnimationTimer timer;
    //constructor
    public BreakoutGameController() {}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        paddle = new Paddle(420, 538, 60, 12);
        ball = new Ball(432, 507, 25, 25);
        breakoutViewManager = new BreakoutViewManager();
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
     * Initialize listeners
     */
    private void initializeListeners() {
        gamePane.setOnKeyPressed(keyEvent -> paddle.keyPressed(keyEvent));
        gamePane.setOnKeyReleased(keyEvent -> paddle.keyReleased(keyEvent));
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
