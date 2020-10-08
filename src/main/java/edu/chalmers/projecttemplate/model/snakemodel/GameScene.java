package edu.chalmers.projecttemplate.model.snakemodel;

import edu.chalmers.projecttemplate.controller.snakecontroller.SettingsViewController;
import javafx.geometry.Point2D;
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


    private long time;

    private Snake snake;
    private final Food food;

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

        gc = canvas.getGraphicsContext2D();

        food = new Food(PIXELSIZE, PIXELSIZE);

        initScreen();

    }
    public Snake getSnake() { return snake;}

    private void initScreen() {
        renderBackground();
        initSnake();
        food.setRandomPosition(WIDTH, HEIGHT);
        renderGameElements();

    }

    private void renderBackground() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        renderGrid(gc);
    }

    private void initSnake(){
        snake = new Snake(new Point2D(WIDTH / 2f, HEIGHT / 2f),
                new Point2D(WIDTH / 2f - PIXELSIZE, HEIGHT / 2f), PIXELSIZE);
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

    private void renderGameElements(){
        snake.render(gc);
        food.render(gc);
        snake.render(gc);
    }

}
