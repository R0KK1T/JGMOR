package edu.chalmers.projecttemplate.view;

import edu.chalmers.projecttemplate.ProjectTemplate;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameMenu extends Parent {

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
