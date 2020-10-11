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
        paddle = new Paddle(420, 536, 60, 12, gameArea.getPrefWidth());
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
     * Render the game
     */
    private void renderTheGame() throws IOException {
        drawPaddle();
        gameArea.getChildren().add(paddle);
        initializeListeners();
    }
    /*
     * Initialize listeners
     */
    private void initializeListeners() {
        gamePane.setOnKeyPressed(keyEvent -> paddle.keyPressed(keyEvent));
        gamePane.setOnKeyReleased(keyEvent -> paddle.keyReleased(keyEvent));
    }

    /*
     * Run the game
     */
    private void run() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                paddle.move();
            }
        };
        timer.start();
    }

}
