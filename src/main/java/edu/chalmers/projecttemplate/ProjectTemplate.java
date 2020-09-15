package edu.chalmers.projecttemplate;

import edu.chalmers.projecttemplate.view.ProjectView;
import javafx.application.Application;
import javafx.stage.Stage;

public class ProjectTemplate extends Application {
	ProjectView projectView;

	@Override
	public void start(Stage primaryStage) throws Exception {
		projectView = new ProjectView();
		projectView.init(primaryStage);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
