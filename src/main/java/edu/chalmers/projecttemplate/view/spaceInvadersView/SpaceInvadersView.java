package edu.chalmers.projecttemplate.view.spaceInvadersView;

import edu.chalmers.projecttemplate.model.spaceInvadersModel.SpaceInvadersModel;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SpaceInvadersView{

    private static final int gameHeight = 900;
    private static final int gameWidth = 1000;

    private double xpos = 100;

    SpaceInvadersModel model;

    Image backgroundImg;
    Image playerImg;

    GraphicsContext backgroundLayer;
    GraphicsContext gameLayer;

    private Scene scene;

    private AnimationTimer timer;

    public SpaceInvadersView() throws Exception{
        initScene();
    }

    public void initScene() throws Exception {
        //get all images
        initImages();

        //setup the 2 different layered canvases
        Canvas backgroundCanvas = new Canvas(gameWidth, gameHeight);
        backgroundLayer = backgroundCanvas.getGraphicsContext2D();
        Canvas gameCanvas = new Canvas(gameWidth, gameHeight);
        gameLayer = gameCanvas.getGraphicsContext2D();

        Pane rootPane = new Pane(backgroundCanvas, gameCanvas);

        //draw the background
        backgroundLayer.drawImage(backgroundImg, 0, 0, gameWidth, gameHeight);

        //draw the player "just a random thing for now"
        gameLayer.drawImage(backgroundImg, xpos, 200, 100, 100);

        scene = new Scene(rootPane);

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //model.update(now);   // calls update function in model
                // call a method in view that clears the gameLayer of any entities already drawn and then redraw all applicable
                xpos++;
                testdraw();

            }
        };

    }

    private void initImages() throws FileNotFoundException {
        //get background image from path
        backgroundImg = new Image(new FileInputStream("src/main/resources/arcade1.jpg"));

        //get player image from path
        playerImg = new Image(new FileInputStream("src/main/resources/arcade1.jpg"));
    }

    public Scene getScene() {
        startGame();
        return scene;
    }

    public void startGame(){

        //TODO Start a new game by creating an instance of model which we can then use

        timer.start();
    }

    private void testdraw(){
        gameLayer.clearRect(0, 0,gameWidth ,gameHeight);
        gameLayer.drawImage(backgroundImg, xpos, 200, 100, 100);
    }
}
