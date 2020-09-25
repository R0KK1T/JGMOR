package edu.chalmers.projecttemplate.view.breakoutview;

import edu.chalmers.projecttemplate.controller.breakoutcontroller.BreakoutMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class BreakoutViewManager {
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;
    //private BreakoutMenuController menu;
    private static final int WIDTH = 1000;
    private static int HEIGHT = 700;
    //constructor
    public BreakoutViewManager() throws IOException {
        mainPane = FXMLLoader.load(getClass().getClassLoader().getResource("breakoutresources/fxml/breakoutStartMenu.fxml"));
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        //menu = new BreakoutMenuController(mainPane);
        mainStage.setScene(mainScene);
    }
    //return the main stage
    public Stage getMainStage() {return mainStage;}
}
