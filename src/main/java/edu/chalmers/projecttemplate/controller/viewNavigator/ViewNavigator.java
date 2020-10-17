package edu.chalmers.projecttemplate.controller.viewNavigator;

import edu.chalmers.projecttemplate.application.MainApplication;
import edu.chalmers.projecttemplate.controller.breakoutcontroller.BreakoutGameController;
import edu.chalmers.projecttemplate.controller.breakoutcontroller.BreakoutMenuController;
import edu.chalmers.projecttemplate.controller.controllerInterface.IController;
import edu.chalmers.projecttemplate.controller.froggercontroller.FroggerController;
import edu.chalmers.projecttemplate.controller.pongcontroller.PongController;
import edu.chalmers.projecttemplate.controller.spaceInvadersController.SpaceInvadersController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.HashMap;

public class ViewNavigator {
    private static ViewNavigator viewNavigator;
    private HashMap<String, Scene> menuViews = new HashMap<>();
    private HashMap<String, IController> controllers = new HashMap<>();

    public static ViewNavigator getInstance() throws Exception {
        if (viewNavigator == null){
            viewNavigator = new ViewNavigator();
            viewNavigator.initMenusAndGames();
        }
        return viewNavigator;
    }

    public void loadMenuView(String viewName){
        if (menuViews.containsKey(viewName)){
            Scene scene =  menuViews.get(viewName);
            MainApplication.setScene(scene);
        }
    }

    public void addMenuView(String viewName, Scene scene){
        menuViews.put(viewName, scene);
    }

    public void loadGameView(String gameName){
        if (controllers.containsKey(gameName)){
            Scene scene = controllers.get(gameName).getScene();
            MainApplication.setScene(scene);
            controllers.get(gameName).startGame();
        }
    }

    public void addGameView(String gameName, IController controller){
        controllers.put(gameName, controller);
    }

    private void initMenusAndGames() throws Exception {
        //init startMenuView
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxmlresources/startMenu.fxml"));
        Scene scene = new Scene(root, 800, 600);
        addMenuView("startMenu", scene);
        //init selectGameView
        root = FXMLLoader.load(getClass().getClassLoader().getResource("fxmlresources/selectGame.fxml"));
        scene = new Scene(root);
        addMenuView("selectGame", scene);
        //init spaceInvadersGame
        IController controller = new SpaceInvadersController();
        addGameView("spaceInvaders", controller);
        //init froggerGame
        controller = new FroggerController();
        addGameView("frogger", controller);
        //init pongGame
        controller = new PongController();
        addGameView("pong", controller);
        //init breakout
        controller = new BreakoutMenuController();
        addGameView("breakout", controller);
    }
}
