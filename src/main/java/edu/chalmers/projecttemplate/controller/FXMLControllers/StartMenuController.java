package edu.chalmers.projecttemplate.controller.FXMLControllers;

import edu.chalmers.projecttemplate.controller.viewNavigator.ViewNavigator;

public class StartMenuController  {

    public void quitGame(){
        System.exit(0);
    }
    public void selectGame() throws Exception {
        ViewNavigator.getInstance().loadMenuView("selectGame");
    }

}
