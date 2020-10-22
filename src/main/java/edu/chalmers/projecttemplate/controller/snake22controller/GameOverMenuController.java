package edu.chalmers.projecttemplate.controller.snake22controller;

import edu.chalmers.projecttemplate.controller.viewNavigator.ViewNavigator;
import edu.chalmers.projecttemplate.view.snake22view.Snake22View;
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
        Snake22View.gameInit.gameLoop.setPaused(false);      // This fixes problem with game not closing thread after new game from pause menu
        Snake22View.gameInit.gameLoop.setRunning(false);     // need to finish game loop to close the thread, because another gameInit.gameInit() creates another thread and the previous one stuck inside not finished game loop
        Snake22View.gameOverMenuStage.close();
        Snake22View.gameInit.gameInit();
    }

    @FXML
    public void mainMenu(ActionEvent event){
        Snake22View.gameInit.gameLoop.setPaused(false);
        Snake22View.gameInit.gameLoop.setRunning(false);
        Snake22View.gameOverMenuStage.close();
        Snake22View.mainStage.setScene(Snake22View.mainMenuScene);
    }

    @FXML
    public void quit(ActionEvent event){
        try {
            ViewNavigator.getInstance().loadMenuView("selectGame");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Snake22View.gameOverMenuStage.close();
        Snake22View.gameInit.gameLoop.setPaused(false);
        Snake22View.gameInit.gameLoop.setRunning(false);
        //Snake22View.mainStage.close();
    }
}
