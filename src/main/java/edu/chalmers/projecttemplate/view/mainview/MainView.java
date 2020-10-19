package edu.chalmers.projecttemplate.view.mainview;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;

public class MainView {

    private HashMap<String, Scene> menuViews = new HashMap<>();
    Stage stage;

    public MainView() throws Exception {
        initMenus();
    }

    public void loadMenu(String viewName){
        if (menuViews.containsKey(viewName)){
            setScene(menuViews.get(viewName));
        }
    }

    private void addMenuView(String viewName, Scene scene){
        menuViews.put(viewName, scene);
    }

    private void initMenus() throws Exception {
        //init startMenuView
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxmlresources/startMenu.fxml"));
        Scene scene = new Scene(root, 800, 600);
        addMenuView("startMenu", scene);
        //init selectGameView
        root = FXMLLoader.load(getClass().getClassLoader().getResource("fxmlresources/selectGame.fxml"));
        scene = new Scene(root);
        addMenuView("selectGame", scene);
        //init snake
        root = FXMLLoader.load(getClass().getClassLoader().getResource("snakeresources/views/WelcomeView.fxml"));
        scene = new Scene(root);
        addMenuView("snake", scene);
    }

    public void setScene(Scene scene){
        stage.setScene(scene);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
