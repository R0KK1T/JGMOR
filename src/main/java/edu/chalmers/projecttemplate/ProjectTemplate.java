package edu.chalmers.projecttemplate;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.SwingUtilities;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import edu.chalmers.projecttemplate.view.GameMenu;

public class ProjectTemplate extends Application {
	private GameMenu gameMenu;

	@Override
	public void start(Stage primaryStage) throws Exception {
		//import image as background
		Pane root = new Pane();
		root.setPrefSize(800, 600);
		InputStream is = Files.newInputStream(Paths.get("src/images/arcade1.jpg"));
		Image img = new Image(is);
		is.close();
		//Create and resize imageView
		ImageView imgView = new ImageView(img);
		imgView.setFitWidth(800);
		imgView.setFitHeight(600);
		//add menu
		gameMenu = new GameMenu();
		//add imgView, gameMenu to the root
		root.getChildren().addAll(imgView, gameMenu);

		//Create scene
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
