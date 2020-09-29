package edu.chalmers.projecttemplate.view.spaceInvadersView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class savestate extends Application {
    private static final int gameHeight = 900;
    private static final int gameWidth = 1000;
    ImageView player;
    ImageView background;
    private Stage stage;
    private Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;

        //import image as background
        Pane root = new Pane();
        root.setPrefSize(gameWidth, gameHeight);

        initImages();

        //add background and player to the root
        root.getChildren().addAll(background, player);

        //Create scene
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void initImages() throws FileNotFoundException {
        //get background image from path
        Image backgroundImg = new Image(new FileInputStream("src/main/resources/arcade1.jpg"));

        //set ImageView to image and set size of background
        background = new ImageView(backgroundImg);
        background.setFitWidth(gameWidth);
        background.setFitHeight(gameHeight);

        //get player image from path
        Image playerImg = new Image(new FileInputStream("src/main/resources/arcade1.jpg"));
        //set ImageView to image and set size of background
        player = new ImageView(playerImg);
        player.setX(300);
        player.setY(300);
        player.setFitWidth(50);
        player.setFitHeight(50);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Scene getScene() {
        return scene;
    }

}
