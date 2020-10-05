package edu.chalmers.projecttemplate.controller.snakecontroller;

import edu.chalmers.projecttemplate.view.snakeview.SnakeView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GameOverMenuController {

    @FXML
    Button newGameButton;

    @FXML
    Button mainMenuButton;

    @FXML
    Button quitButton;

    @FXML
    public void newGame(ActionEvent event) {
        SnakeView.gameOverMenuStage.close();

    }

    @FXML
    public void mainMenu(ActionEvent event){
        SnakeView.gameOverMenuStage.close();
        SnakeView.mainStage.setScene(SnakeView.mainMenuScene);
    }

    @FXML
    public void quit(ActionEvent event){
        SnakeView.mainStage.close();
    }

}
