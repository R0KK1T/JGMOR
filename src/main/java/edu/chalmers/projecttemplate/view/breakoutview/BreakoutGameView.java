package edu.chalmers.projecttemplate.view.breakoutview;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class BreakoutGameView {
    private AnchorPane gamePane;
    private Scene gameScene;
    private static final int GAME_WIDTH = 1000;
    private static final int GAME_HEIGHT = 700;
    private final String GAMEVIEW = "breakoutresources/fxml/breakoutGameView.fxml";
    //constructor
    public BreakoutGameView() throws IOException {
        initializeStage();
    }
    private void initializeStage() throws IOException {
        gamePane = FXMLLoader.load(getClass().getClassLoader().getResource(GAMEVIEW));
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);

    }
    public Scene getGameScene() {return gameScene;}


}
