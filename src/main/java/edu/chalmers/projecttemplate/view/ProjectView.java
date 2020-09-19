package edu.chalmers.projecttemplate.view;


import edu.chalmers.projecttemplate.view.pongview.PongView;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;


public class ProjectView  {
    PongView pongView;
    GameMenu gameMenu;
    public void init (Stage primaryStage) throws Exception{
        //import image as background
        Pane root = new Pane();
        root.setPrefSize(800, 600);
        InputStream inStream = Files.newInputStream(Paths.get("src/main/java/edu/chalmers/projecttemplate/view/resources/arcade1.jpg"));
        Image img = new Image(inStream);
        inStream.close();
        //Create and resize imageView
        ImageView imgView = new ImageView(img);
        imgView.setFitWidth(800);
        imgView.setFitHeight(600);
        //add menu
        gameMenu = new GameMenu();
        //add imgView, gameMenu to the root
        root.getChildren().addAll(imgView, gameMenu);

        //Create scene
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
