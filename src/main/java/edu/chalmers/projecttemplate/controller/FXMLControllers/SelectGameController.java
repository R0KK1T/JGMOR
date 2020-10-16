package edu.chalmers.projecttemplate.controller.FXMLControllers;

import edu.chalmers.projecttemplate.controller.viewNavigator.ViewNavigator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SelectGameController {
    @FXML
    private Button breakoutBtn;
    @FXML
    private Button froggerBtn;
    @FXML
    private Button pongBtn;
    @FXML
    private Button snakeBtn;
    @FXML
    private Button spaceInvadersBtn;
    @FXML
    private Button backBtn;

    public void startFrogger() throws Exception {
        ViewNavigator.getInstance().loadGameView("frogger");
    }

    public void startPong() throws Exception {
        ViewNavigator.getInstance().loadGameView("pong");
    }

    public void startSpaceInvaders() throws Exception {
        ViewNavigator.getInstance().loadGameView("spaceInvaders");
    }

    public void backToStartMenu() throws Exception {
        ViewNavigator.getInstance().loadMenuView("startMenu");
    }
}
