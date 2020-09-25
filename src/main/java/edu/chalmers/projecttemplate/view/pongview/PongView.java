package edu.chalmers.projecttemplate.view.pongview;

import edu.chalmers.projecttemplate.view.ProjectView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.net.URL;
import java.util.ResourceBundle;

public class PongView extends Application implements Initializable {
    private ProjectView projectView;
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("pongresources/pong.fxml"));

        Scene scene = new Scene(root, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    /*public void start (Stage primaryStage) throws Exception{
        //import image as background
        Pane root = new Pane();
        root.setPrefSize(800, 600);
        ImageView background = createImageView("src/main/resources/pongresources/Background.png",800,600);
        ImageView ball = createImageView("src/main/resources/pongresources/Ball.png",projectView.projectModel.pongModel.getPongBall().getWidth(),projectView.projectModel.pongModel.getPongBall().getHeight());
        ball.setX(projectView.projectModel.pongModel.getPongBall().getX());
        ball.setY(projectView.projectModel.pongModel.getPongBall().getY());
        //Create scene
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }*/
    /*private ImageView createImageView(String imagePath, double setWidth, double setHeight) throws Exception {
        InputStream inStream = Files.newInputStream(Paths.get(imagePath));
        ImageView tempImageView = new ImageView(new Image (inStream));
        inStream.close();
        tempImageView.setFitWidth(setWidth);
        tempImageView.setFitHeight(setHeight);
        return tempImageView;
    }*/
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
