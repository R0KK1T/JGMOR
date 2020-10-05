package edu.chalmers.projecttemplate.view.snakeview;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class SnakeView extends Application {

    public static Stage mainStage;
    public static Scene mainMenuScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // *** Initial set-up of the window ***

        SnakeView.mainStage = stage;

        stage.setTitle("SnakeFX");
        stage.setResizable(false);


        // loads main menu window
        Parent mainMenu = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("snakeresources/mainMenu.fxml")));
        mainMenuScene = new Scene(mainMenu, 320, 320);


        stage.setScene(mainMenuScene);
        stage.show();

    }


}