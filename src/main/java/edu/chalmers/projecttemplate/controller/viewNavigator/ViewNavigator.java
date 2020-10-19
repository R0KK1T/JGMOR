package edu.chalmers.projecttemplate.controller.viewNavigator;

import edu.chalmers.projecttemplate.controller.breakoutcontroller.BreakoutMenuController;
import edu.chalmers.projecttemplate.controller.controllerInterface.IController;
import edu.chalmers.projecttemplate.controller.froggercontroller.FroggerController;
import edu.chalmers.projecttemplate.controller.pongcontroller.PongController;
import edu.chalmers.projecttemplate.controller.spaceInvadersController.SpaceInvadersController;
import edu.chalmers.projecttemplate.view.mainview.MainView;
import javafx.stage.Stage;

import java.util.HashMap;

public class ViewNavigator {
    private static ViewNavigator viewNavigator;
    private MainView view;
    private HashMap<String, IController> controllers = new HashMap<>();


    public static ViewNavigator getInstance() throws Exception {
        if (viewNavigator == null){
            viewNavigator = new ViewNavigator();
            viewNavigator.view = new MainView();
            viewNavigator.initGames();
        }
        return viewNavigator;
    }

    public void loadMenuView(String viewName){
        view.loadMenu(viewName);
    }

    public void loadGame(String gameName){
        if (controllers.containsKey(gameName)){
            view.setScene(controllers.get(gameName).getScene());
            controllers.get(gameName).startGame();
        }
    }

    public void addGameController(String gameName, IController controller){
        controllers.put(gameName, controller);
    }


    public void initGames() throws Exception {
        //init spaceInvadersGame
        IController controller = new SpaceInvadersController();
        addGameController("spaceInvaders", controller);
        //init froggerGame
        controller = new FroggerController();
        addGameController("frogger", controller);
        //init pongGame
        controller = new PongController();
        addGameController("pong", controller);
        //init breakout
        controller = new BreakoutMenuController();
        addGameController("breakout", controller);
    }

    public void setStage(Stage stage){
        view.setStage(stage);
    }
}
