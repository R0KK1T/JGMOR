package edu.chalmers.projecttemplate.controller.snake22controller;

import edu.chalmers.projecttemplate.controller.viewNavigator.ViewNavigator;
import edu.chalmers.projecttemplate.view.snake22view.Snake22View;
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
        Snake22View.gameInit.gameInit();
    }

    @FXML
    public void settings(ActionEvent event){
        Snake22View.mainStage.setScene(Snake22View.settingsMenuScene);
    }

    @FXML
    public void quit(ActionEvent event){
        //Snake22View.mainStage.close();
        try {
            ViewNavigator.getInstance().loadMenuView("selectGame");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
