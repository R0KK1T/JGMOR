package edu.chalmers.projecttemplate.view.snake22view;

import edu.chalmers.projecttemplate.model.snake22model.GameConfiguration;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Snake22View extends Application {
    public static GameConfiguration gameConfiguration;
    public static GameInit gameInit;

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

        Snake22View.mainStage = stage;

        stage.setTitle("SnakeFX");
        stage.setResizable(false);

        gameConfiguration = new GameConfiguration();

        // loads main menu window
        Parent mainMenu = FXMLLoader.load(getClass().getClassLoader().getResource("snake22resources/mainMenu.fxml"));
        mainMenuScene = new Scene(mainMenu, 320, 320);

        // loads pause menu window
        Parent pauseMenu = FXMLLoader.load(getClass().getClassLoader().getResource("snake22resources/pauseMenu.fxml"));
        pauseMenuScene = new Scene(pauseMenu, 320, 320);

        // loads settings menu window
        Parent settingsMenu = FXMLLoader.load(getClass().getClassLoader().getResource("snake22resources/settingsMenu.fxml"));
        settingsMenuScene = new Scene(settingsMenu);

        // loads game over menu window
        Parent gameOverMenu = FXMLLoader.load(getClass().getClassLoader().getResource("snake22resources/gameOver.fxml"));
        gameOverMenuScene = new Scene(gameOverMenu);

        //loads main window of the game (canvas). creates all games objects, key listener and fires up game loop when method is called from that object
        gameInit = new GameInit();

        stage.setScene(mainMenuScene);
        stage.show();

    }

    public static void pauseMenu(){
        pauseMenuStage = new Stage();
        pauseMenuStage.initModality(Modality.APPLICATION_MODAL);
        pauseMenuStage.setTitle("Game paused");
        pauseMenuStage.setScene(Snake22View.pauseMenuScene);
        pauseMenuStage.show();
    }

    public static void gameOverMenu(){
        gameOverMenuStage = new Stage();
        gameOverMenuStage.initModality(Modality.APPLICATION_MODAL);
        gameOverMenuStage.setTitle("Game over");
        gameOverMenuStage.setScene(Snake22View.gameOverMenuScene);
        gameOverMenuStage.show();
    }

}
