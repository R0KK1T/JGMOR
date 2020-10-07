package edu.chalmers.projecttemplate.model.snakemodel;

import edu.chalmers.projecttemplate.controller.snakecontroller.SettingsViewController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

import java.util.prefs.Preferences;


public class GameScene extends Scene {


    private Canvas canvas;
    private final int WIDTH = 1000;
    private final int HEIGHT = 700;


    private long time;

    private Preferences prefs;



    public GameScene(Parent root, long time) {
        this(root);
        this.time = time;
    }

    public GameScene(Parent root) {
        super(root);
        prefs = Preferences.userRoot().node(SettingsViewController.class.getName());

        canvas = new Canvas(WIDTH, HEIGHT);
        ((Pane) root).getChildren().add(canvas);

    }
}
