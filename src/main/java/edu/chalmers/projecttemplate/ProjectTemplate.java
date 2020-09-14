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

	/*
	 * Games menu
	 */
	private class GameMenu extends Parent {
		public GameMenu() {
			//a box which contains the menu
			VBox menu0 = new VBox(15);
			//submenu
			VBox menu1 = new VBox(15);
			//Positions 100 in x-axe and 200 in y-axe
			menu0.setTranslateX(100);
			menu0.setTranslateY(200);

			menu1.setTranslateX(100);
			menu1.setTranslateY(200);

			final int offset = 400;

			//create buttons
			//Button Play
			MenuButton btnPlay = new MenuButton("Play");
			btnPlay.setOnMouseClicked(event -> {

			});
			//Button Options
			MenuButton btnOptions = new MenuButton("Options");
			btnOptions.setOnMouseClicked(event -> {

			});
			//Button Exit
			MenuButton btnExit = new MenuButton("Exit");
			btnExit.setOnMouseClicked(event -> {
				System.exit(0);
			});

			//add button to the node
			menu0.getChildren().addAll(btnPlay, btnOptions, btnExit);
			//a background to the game menu
			Rectangle bg = new Rectangle(800, 600);
			bg.setFill(Color.GREY);
			bg.setOpacity(0.4);
			getChildren().addAll(bg, menu0);
		}
	}
	/*
	 * Buttons and effects
	 */
	private static class MenuButton extends StackPane {
		private Text text;

		public MenuButton(String name){
			//create button and add effects
			text = new Text(name);
			text.setFont(text.getFont().font(20));
			text.setFill(Color.WHITE);
			//Buttons parameters (bg= background)
			Rectangle bg = new Rectangle(250, 30);
			bg.setOpacity(0.6);
			bg.setFill(Color.BLACK);
			bg.setEffect(new GaussianBlur(3.5));
			setAlignment(Pos.CENTER_LEFT);
			setRotate(-0.5);
			getChildren().addAll(bg, text);
			//Mouse over a button
			setOnMouseEntered(event -> {
				bg.setTranslateX(10);
				text.setTranslateX(10);
				bg.setFill(Color.WHITE);
				text.setFill(Color.BLACK);
			});
			//Close effects when you exite area (Buttons area)
			setOnMouseExited(event -> {
				bg.setTranslateX(0);
				text.setTranslateX(0);
				bg.setFill(Color.BLACK);
				text.setFill(Color.WHITE);
			});
			//On click
			DropShadow drop = new DropShadow(50, Color.WHITE);
			drop.setInput(new Glow());
			setOnMousePressed(event -> setEffect(drop));
			setOnMouseReleased(event -> setEffect(null));
		}
	}


	public static void main(String[] args) {
		launch(args);
	}
}
