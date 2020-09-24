package edu.chalmers.projecttemplate.view.pongview;

import edu.chalmers.projecttemplate.view.ProjectView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PongView extends Application {
    private ProjectView projectView;
    public void start (Stage primaryStage) throws Exception{
        //import image as background
        Pane root = new Pane();
        root.setPrefSize(800, 600);
        ImageView background = createImageView("src/main/java/edu/chalmers/projecttemplate/view/resources/pongresources/Background.png",800,600);
        ImageView ball = createImageView("src/main/java/edu/chalmers/projecttemplate/view/resources/pongresources/Ball.png",projectView.projectModel.pongModel.getPongBall().getWidth(),projectView.projectModel.pongModel.getPongBall().getHeight());
        ball.setX(projectView.projectModel.pongModel.getPongBall().getX());
        ball.setY(projectView.projectModel.pongModel.getPongBall().getY());
        //Create scene
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private ImageView createImageView(String imagePath, double setWidth, double setHeight) throws Exception {
        InputStream inStream = Files.newInputStream(Paths.get(imagePath));
        ImageView tempImageView = new ImageView(new Image (inStream));
        inStream.close();
        tempImageView.setFitWidth(setWidth);
        tempImageView.setFitHeight(setHeight);
        return tempImageView;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
