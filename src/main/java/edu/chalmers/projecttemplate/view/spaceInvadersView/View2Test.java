package edu.chalmers.projecttemplate.view.spaceInvadersView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class View2Test extends Application {

    private static Stage stage;
    Scene scene1;
    SpaceInvadersView SIView;

    @Override
    public void start(Stage primaryStage) throws Exception {
        SIView = new SpaceInvadersView();
        stage = primaryStage;


        Label label1 = new Label("Welcome to the first scene");
        Button button1 = new Button("Go to scene 2");
        button1.setOnAction(e -> changeScene());

        //layout 1 - children are laid out in a vertical column
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, button1);
        scene1 = new Scene(layout1, 500, 500);

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
        stage.setScene(SIView.getScene());
        SIView.startGame();
    }
}

