package edu.chalmers.projecttemplate.model.snakemodel;

import edu.chalmers.projecttemplate.controller.snakecontroller.SettingsViewController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.prefs.Preferences;


public class GameScene extends Scene {
    public static final int PIXELSIZE = 25;

    private final GraphicsContext gc;

    private final Canvas canvas;
    private final int WIDTH = 1000;
    private final int HEIGHT = 700;

    private int score = 0;


    private long time;

    private Preferences prefs;

    private Label inGameScoreLabel;



    public GameScene(Parent root, long time) {
        this(root);
        this.time = time;
    }

    public GameScene(Parent root) {
        super(root);
        prefs = Preferences.userRoot().node(SettingsViewController.class.getName());

        canvas = new Canvas(WIDTH, HEIGHT);
        ((Pane) root).getChildren().add(canvas);

        gc = canvas.getGraphicsContext2D();

        initScreen();

    }

    private void initScreen() {
        score = 0;
        if (prefs.getBoolean("renderScore", true)) {
            inGameScoreLabel.setText("Score: " + score + "pt.");
        }
        renderBackground();
    }

    private void renderBackground() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        renderGrid(gc);
    }

    private void renderGrid(GraphicsContext gc) {
        gc.setStroke(Color.GRAY);
        for (int i = 0; i < WIDTH; i += PIXELSIZE) {
            gc.strokeLine(i, 0, i, HEIGHT);
        }
        for (int i = 0; i < HEIGHT; i += PIXELSIZE) {
            gc.strokeLine(0, i, WIDTH, i);
        }
    }

}
