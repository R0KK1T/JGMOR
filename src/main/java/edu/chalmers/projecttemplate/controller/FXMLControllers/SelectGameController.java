package edu.chalmers.projecttemplate.controller.FXMLControllers;

import edu.chalmers.projecttemplate.controller.viewNavigator.ViewNavigator;
import edu.chalmers.projecttemplate.view.snake22view.Snake22View;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SelectGameController {
    @FXML
    private Button snakeBtn;

    public void startFrogger() throws Exception {
        ViewNavigator.getInstance().loadGame("frogger");
    }

    public void startPong() throws Exception {
        ViewNavigator.getInstance().loadGame("pong");
    }

    public void startSpaceInvaders() throws Exception {
        ViewNavigator.getInstance().loadGame("spaceInvaders");
    }
    public void startBreakout() throws Exception {
        ViewNavigator.getInstance().loadGame("breakout");
    }

    public void backToStartMenu() throws Exception {
        ViewNavigator.getInstance().loadMenuView("startMenu");
    }

    public void startSnake() throws Exception {
        Snake22View snakeG = new Snake22View();
        Stage stage = (Stage) snakeBtn.getScene().getWindow();
        snakeG.start(stage);
        //ViewNavigator.getInstance().loadMenuView("snake");
    }
}
