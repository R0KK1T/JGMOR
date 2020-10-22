package edu.chalmers.projecttemplate.application;

import edu.chalmers.projecttemplate.controller.viewNavigator.ViewNavigator;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApplication extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		ViewNavigator.getInstance().setStage(primaryStage);
		ViewNavigator.getInstance().loadMenuView("startMenu");

		primaryStage.setResizable(false);
		primaryStage.setX(0);
		primaryStage.setY(0);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
