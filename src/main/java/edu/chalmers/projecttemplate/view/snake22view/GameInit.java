package edu.chalmers.projecttemplate.view.snake22view;

import edu.chalmers.projecttemplate.controller.snake22controller.GameStateUpdater;
import edu.chalmers.projecttemplate.controller.snake22controller.InputListener;
import edu.chalmers.projecttemplate.model.snake22model.*;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameInit {
    private GraphicsContext graphicsContext;
    public GameLoop gameLoop;

    public void gameInit(){
        Canvas canvas = new Canvas(Snake22View.gameConfiguration.getBoardWidth(), Snake22View.gameConfiguration.getBoardHeight());
        this.graphicsContext = canvas.getGraphicsContext2D();

        Snake22View.gameScene = new Scene(new Pane(canvas));

        Snake22View.mainStage.setOnCloseRequest(e -> {
            gameLoop.setPaused(false);
            gameLoop.setRunning(false);
        });
        Snake22View.mainStage.setScene(Snake22View.gameScene);


        // *************************************************************************************************
        Score gameScore = new Score();
        DrawModule drawModule = new DrawModule(graphicsContext, Snake22View.gameConfiguration, gameScore);        // creating drawing module
        InputListener inputListener = new InputListener(Snake22View.gameScene);         // creating InputListener module
        inputListener.getUserInput();                                   // inputListener starts to "listen" for key presses
        Snake snake = new Snake(Snake22View.gameConfiguration);                     // creating snake
        Food food = new Food(Snake22View.gameConfiguration, snake.getSnakeParts());                        // creating food
        GameStateUpdater gameStateUpdater = new GameStateUpdater(inputListener, snake, Snake22View.gameConfiguration);     // creating gamestateUpdater module
        CollisionDetectionModule collisionDetectionModule = new CollisionDetectionModule(snake, food, Snake22View.gameConfiguration);              // creating collisionDetectionModule module


        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(1);
        gameLoop = new GameLoop(drawModule, inputListener, gameStateUpdater, snake, food, Snake22View.gameConfiguration, collisionDetectionModule, gameScore);   // game loop starts


        threadPool.schedule(gameLoop, 0, TimeUnit.SECONDS);
        threadPool.shutdown();
    }
}
