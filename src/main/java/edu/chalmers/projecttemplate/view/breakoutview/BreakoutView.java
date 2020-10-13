package edu.chalmers.projecttemplate.view.breakoutview;

import javafx.application.Application;
import javafx.stage.Stage;

public class BreakoutView extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BreakoutMenuView breakoutViewManager = new BreakoutMenuView();
        primaryStage = breakoutViewManager.getMainStage();
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
