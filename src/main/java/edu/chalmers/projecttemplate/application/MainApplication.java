package edu.chalmers.projecttemplate.application;

import edu.chalmers.projecttemplate.controller.viewNavigator.ViewNavigator;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApplication extends Application {

	private static Stage stage;

	@Override
	public void start(Stage primaryStage) throws Exception {

		stage = primaryStage;
		ViewNavigator.getInstance().setStage(stage);
		ViewNavigator.getInstance().loadMenuView("startMenu");

		stage.setResizable(false);
		stage.setX(0);
		stage.setY(0);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
