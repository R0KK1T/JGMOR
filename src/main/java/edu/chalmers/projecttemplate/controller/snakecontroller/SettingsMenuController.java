package edu.chalmers.projecttemplate.controller.snakecontroller;

import edu.chalmers.projecttemplate.view.snakeview.SnakeView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;


public class SettingsMenuController {

    @FXML
    ChoiceBox<String> gameSpeed;

    @FXML
    ChoiceBox<String> wallCollisions;

    @FXML
    ChoiceBox<String> gameboardSize;

    @FXML
    Button mainMenuButton;


    @FXML
    public void mainMenu(ActionEvent event) {
        SnakeView.mainStage.setScene(SnakeView.mainMenuScene);
    }

}
