package edu.chalmers.projecttemplate.view.spaceInvadersView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class View2Test extends Application {

    private static Stage stage;
    Scene scene1, scene2;
    SpaceInvadersView SIView;

    @Override
    public void start(Stage primaryStage) throws Exception {

        SIView = new SpaceInvadersView();
        stage = primaryStage;

        Label label1 = new Label("Welcome to the first scene");
        Button button1 = new Button("Go to scene 2");
        button1.setOnAction(e -> stage.setScene(SIView.getScene()));

        //layout 1 - children are laid out in a vertical column
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, button1);
        scene1 = new Scene(layout1, 500, 500);


        Button button2 = new Button("Go to scene 1");
        button2.setOnAction(e -> stage.setScene(scene1));

        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);
        scene2 = new Scene(layout2, 300, 300);

        stage.setScene(scene1);
        stage.setTitle("title here");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

