package edu.chalmers.projecttemplate.view.spaceInvadersView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SpaceInvadersView extends Application {

    private static final int gameHeight = 900;
    private static final int gameWidth = 1000;
    ImageView player;
    ImageView background;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //import image as background
        Pane root = new Pane();
        root.setPrefSize(gameWidth, gameHeight);

        initImages();

        //add background and player to the root
        root.getChildren().addAll(background, player);

        //Create scene
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
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

    //Image image = new Image("src/picture/arcade1.jpg");

    public static void main(String[] args) {
        launch(args);
    }
}
