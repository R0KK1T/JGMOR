package edu.chalmers.projecttemplate.view.snake22view;

import edu.chalmers.projecttemplate.controller.snake22controller.InputListener;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameInit {

    public GameLoop gameLoop;

    public void gameInit(){
        Canvas canvas = new Canvas(Snake22View.gameConfiguration.getBoardWidth(), Snake22View.gameConfiguration.getBoardHeight());
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        Snake22View.gameScene = new Scene(new Pane(canvas));

        Snake22View.mainStage.setOnCloseRequest(e -> {
            gameLoop.setPaused(false);
            gameLoop.setRunning(false);
        });
        Snake22View.mainStage.setScene(Snake22View.gameScene);


        // *************************************************************************************************

        DrawModule drawModule = new DrawModule(graphicsContext, Snake22View.gameConfiguration);        // creating drawing module
        InputListener inputListener = new InputListener(Snake22View.gameScene);         // creating InputListener module
        inputListener.getUserInput();

        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(1);
        gameLoop = new GameLoop(drawModule, inputListener);   // game loop starts


        threadPool.schedule(gameLoop, 0, TimeUnit.SECONDS);
        threadPool.shutdown();
    }
}
