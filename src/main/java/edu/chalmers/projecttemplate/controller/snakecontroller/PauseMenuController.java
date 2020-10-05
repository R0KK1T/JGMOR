package edu.chalmers.projecttemplate.controller.snakecontroller;

import edu.chalmers.projecttemplate.view.snakeview.SnakeView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PauseMenuController {

    @FXML
    Button continueButton;

    @FXML
    Button newGameButton;

    @FXML
    Button mainMenuButton;

    @FXML
    Button quitButton;

    @FXML
    public void continueGame(ActionEvent event) {
        SnakeView.pauseMenuStage.close();
    }

    @FXML
    public void newGame(ActionEvent event) {
        SnakeView.pauseMenuStage.close();
    }

    public void mainMenu(ActionEvent event){
        SnakeView.pauseMenuStage.close();
        SnakeView.mainStage.setScene(SnakeView.mainMenuScene);
    }

    @FXML
    public void quit(ActionEvent event){
        SnakeView.pauseMenuStage.close();
        SnakeView.mainStage.close();
    }

}
