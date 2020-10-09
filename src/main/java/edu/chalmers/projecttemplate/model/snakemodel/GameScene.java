package edu.chalmers.projecttemplate.model.snakemodel;

import com.sun.javafx.scene.traversal.Direction;
import edu.chalmers.projecttemplate.controller.snakecontroller.SettingsViewController;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    private myTimer timer;

    private Snake snake;
    private final Food food;

    private boolean inGame = false;

    private Preferences prefs;

    private final String UP = "UP";
    private final String DOWN = "DOWN";
    private final String RIGHT = "RIGHT";
    private final String LEFT = "LEFT";

    private MyHandlerForArrows myHandlerForArrows = new MyHandlerForArrows();
    private MyHandlerForEsc myHandlerForEsc = new MyHandlerForEsc();

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
        timer = new myTimer();

        // check user inputs on the first screen
        addEventHandler(KeyEvent.KEY_PRESSED, myHandlerForArrows);
        addEventHandler(KeyEvent.KEY_PRESSED, myHandlerForEsc);

        initScreen();

    }

    public void setTime(long time) {
        this.time = time;
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

    private class myTimer extends AnimationTimer {
        private long lastUpdate = 0;

        @Override
        public void start() {
            super.start();
            inGame = true;
        }


        @Override
        public void handle(long now) {
            // if the game isn't paused it will refresh the screen in every 100 milliseconds
            if (now - lastUpdate >= time) {
                addEventHandler(KeyEvent.KEY_PRESSED, myHandlerForArrows);
                lastUpdate = now;
                snake.move();
                renderGameElements();
            }
        }
    }

    private class MyHandlerForEsc implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent event) {
            KeyCode kc = event.getCode();
        }
    }

    private class MyHandlerForArrows implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent event) {
            KeyCode kc = event.getCode();

            String key = kc.toString();
            if ((key.equals(prefs.get(RIGHT, ""))
                    || key.equals(prefs.get(LEFT, ""))
                    || key.equals(prefs.get(UP, ""))
                    || key.equals(prefs.get(DOWN, "")))
                    ) {
                timer.start();
            }
            if (key.equals(prefs.get(RIGHT, "")) && snake.getDirection() != Direction.LEFT) {
                snake.setDirection(Direction.RIGHT);
            } else {
                if (key.equals(prefs.get(LEFT, "")) && snake.getDirection() != Direction.RIGHT) {
                    snake.setDirection(Direction.LEFT);
                } else {
                    if (key.equals(prefs.get(DOWN, "")) && snake.getDirection() != Direction.UP) {
                        snake.setDirection(Direction.DOWN);
                    } else {
                        if (key.equals(prefs.get(UP, "")) && snake.getDirection() != Direction.DOWN) {
                            snake.setDirection(Direction.UP);
                        }
                    }
                }
            }

            removeEventHandler(KeyEvent.KEY_PRESSED, this);
        }
    }

}
