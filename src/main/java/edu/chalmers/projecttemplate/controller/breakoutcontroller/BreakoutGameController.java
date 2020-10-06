package edu.chalmers.projecttemplate.controller.breakoutcontroller;

import edu.chalmers.projecttemplate.model.breakoutmodel.*;
import edu.chalmers.projecttemplate.view.breakoutview.BreakoutViewManager;
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
    private Wall gameModel;
    private Paddle paddle;
    private Ball ball;
    private Brick brick;
    //constructor
    public BreakoutGameController() {}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            paddle = new Paddle();
            ball = new Ball();
            gameModel = new Wall(gameArea, btnGameExit, btnGamePause, scoreLabel, paddle, ball);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //1. Button Exit
    public void btnExitGameControl(ActionEvent mouseEvent) throws IOException {
        BreakoutViewManager newMenu = new BreakoutViewManager();
        Scene gameScene = newMenu.getMainScene();
        Stage gameStage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        gameStage.setScene(gameScene);
        gameStage.show();
    }
    //2. Button Pause
    public void btnExitPauseControl(ActionEvent actionEvent) {
    }


}
