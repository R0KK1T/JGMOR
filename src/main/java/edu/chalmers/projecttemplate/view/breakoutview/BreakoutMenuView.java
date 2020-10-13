package edu.chalmers.projecttemplate.view.breakoutview;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
/*
 * View for Breakout menu
 */
public class BreakoutMenuView {
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;
    private final String STARTMENU = "breakoutresources/fxml/breakoutStartMenu.fxml";
    private static final int WIDTH = 1000;
    private static int HEIGHT = 700;
    //constuctor
    public BreakoutMenuView() throws IOException {
        initializeStage();
    }
    private void initializeStage() throws IOException {
        mainPane = FXMLLoader.load(getClass().getClassLoader().getResource(STARTMENU));
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setTitle("Breakout Menu");
        mainStage.setScene(mainScene);
    }
    //return the main stage
    public Stage getMainStage() {return mainStage;}
    //retunr the main Scene
    public Scene getMainScene() {return mainScene;}
}
