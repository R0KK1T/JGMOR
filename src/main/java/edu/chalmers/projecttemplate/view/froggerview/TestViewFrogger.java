package edu.chalmers.projecttemplate.view.froggerview;

import edu.chalmers.projecttemplate.controller.froggercontroller.FroggerController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TestViewFrogger extends Application {

    private static Stage stage;
    Scene scene1;
    FroggerController froggerController;

    @Override
    public void start(Stage primaryStage) throws Exception {
        froggerController = new FroggerController();
        stage = primaryStage;


        Label label1 = new Label("Welcome to the first scene");
        Button button1 = new Button("Go to game (other scene)");
        button1.setOnAction(e -> changeScene());

        //layout 1 - children are laid out in a vertical column
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, button1);
        scene1 = new Scene(layout1, 500, 500);

        stage.setResizable(false);
        stage.setScene(scene1);
        stage.setTitle("title here");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void changeScene(){
        stage.setX(500);
        stage.setY(50);
        stage.setScene(froggerController.getScene());
        froggerController.startGame();
    }

}
