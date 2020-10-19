package edu.chalmers.projecttemplate.controller.FXMLControllers;

import edu.chalmers.projecttemplate.controller.viewNavigator.ViewNavigator;

public class SelectGameController {

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
        ViewNavigator.getInstance().loadMenuView("snake");
    }
}
