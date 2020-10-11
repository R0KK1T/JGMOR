package edu.chalmers.projecttemplate.view.breakoutview;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BreakoutGameView {
    private AnchorPane gamePane;
    private Scene gameScene;
    private static final int GAME_WIDTH = 1000;
    private static final int GAME_HEIGHT = 700;
    private final String GAMEVIEW = "breakoutresources/fxml/breakoutGameView.fxml";
    Image paddleImage;
    //constructor
    public BreakoutGameView() throws IOException {
        initializeStage();
        initImages();
    }
    private void initializeStage() throws IOException {
        gamePane = FXMLLoader.load(getClass().getClassLoader().getResource(GAMEVIEW));
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);

    }
    private void initImages() throws FileNotFoundException {
        paddleImage = new Image(new FileInputStream("src/main/resources/breakoutresources/images/paddle.png"));
    }
    public Scene getGameScene() {return gameScene;}

    /*
     * Draw game paddle, ball, and brick
     */
    public void drawPaddle(GraphicsContext gc, int posX, int posY, int width, int height) {
        gc.drawImage(paddleImage, posX, posY, width, height);
    }

}
