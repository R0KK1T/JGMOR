package edu.chalmers.projecttemplate.controller.snakecontroller;

import edu.chalmers.projecttemplate.view.snakeview.SnakeView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainMenuController {

    @FXML
    Button newGameButton;

    @FXML
    Button settingsButton;

    @FXML
    Button quitButton;

    @FXML
    public void startNewGame(ActionEvent event) {

    }

    @FXML
    public void settings(ActionEvent event){
        SnakeView.mainStage.setScene(SnakeView.settingsMenuScene);
    }

    @FXML
    public void quit(ActionEvent event){
        SnakeView.mainStage.close();
    }
}