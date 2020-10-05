package edu.chalmers.projecttemplate.view.snakeview;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Objects;

public class SnakeView extends Application {

    public static Stage mainStage;
    public static Stage pauseMenuStage;
    public static Stage gameOverMenuStage;
    public static Scene gameScene;
    public static Scene mainMenuScene;
    public static Scene pauseMenuScene;
    public static Scene settingsMenuScene;
    public static Scene gameOverMenuScene;

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

        // loads pause menu window
        Parent pauseMenu = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("snakeresources/pauseMenu.fxml")));
        pauseMenuScene = new Scene(pauseMenu, 320, 320);

        // loads settings menu window
        Parent settingsMenu = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("snakeresources/settingsMenu.fxml")));
        settingsMenuScene = new Scene(settingsMenu);

        // loads game over menu window
        Parent gameOverMenu = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("snakeresources/gameOver.fxml")));
        gameOverMenuScene = new Scene(gameOverMenu);


        stage.setScene(mainMenuScene);
        stage.show();

    }

    public static void pauseMenu(){
        pauseMenuStage = new Stage();
        pauseMenuStage.initModality(Modality.APPLICATION_MODAL);
        pauseMenuStage.setTitle("Game paused");
        pauseMenuStage.setScene(SnakeView.pauseMenuScene);
        pauseMenuStage.show();
    }

    public static void gameOverMenu(){
        gameOverMenuStage = new Stage();
        gameOverMenuStage.initModality(Modality.APPLICATION_MODAL);
        gameOverMenuStage.setTitle("Game over");
        gameOverMenuStage.setScene(SnakeView.gameOverMenuScene);
        gameOverMenuStage.show();
    }


}