package edu.chalmers.projecttemplate.view.snakeview;

import edu.chalmers.projecttemplate.model.snakemodel.MyLogger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;


    public void start(Stage primaryStage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass()
                    .getClassLoader()
                    .getResource("snakeresources/views/WelcomeView.fxml")));

        } catch (Exception ex) {
            MyLogger.WARN("snakeresources/views/WelcomeView.fxml file not found.");
            System.exit(0);
        }
        primaryStage.setTitle("Snake");
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();

        setDefCont();

        primaryStage.show();
    }

    // reset the controls to default
    // every time the application started
    private void setDefCont() {

    }


    public static void main(String[] args) {
        launch(args);
    }

}
