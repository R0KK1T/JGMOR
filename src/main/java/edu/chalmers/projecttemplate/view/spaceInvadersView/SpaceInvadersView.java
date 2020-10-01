package edu.chalmers.projecttemplate.view.spaceInvadersView;

import edu.chalmers.projecttemplate.model.spaceInvadersModel.MovableObject;
import edu.chalmers.projecttemplate.model.spaceInvadersModel.SpaceInvadersModel;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class SpaceInvadersView{

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
        //create instance of model
        model = new SpaceInvadersModel();

        //get all images
        initImages();

        //setup the 2 different layered canvases
        Canvas backgroundCanvas = new Canvas(model.getSizeX(), model.getSizeY());
        backgroundLayer = backgroundCanvas.getGraphicsContext2D();
        Canvas gameCanvas = new Canvas(model.getSizeX(), model.getSizeY());
        gameLayer = gameCanvas.getGraphicsContext2D();

        Pane rootPane = new Pane(backgroundCanvas, gameCanvas);

        //draw the background
        backgroundLayer.drawImage(backgroundImg, 0, 0, model.getSizeX(), model.getSizeY());

        scene = new Scene(rootPane);

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //model.update(now);   // calls update function in model
                model.update();
                draw();
            }
        };

    }

    private void initImages() throws FileNotFoundException {
        //get background image from path
        backgroundImg = new Image(new FileInputStream("src/main/resources/pongresources/Background.png"));

        //get player image from path
        playerImg = new Image(new FileInputStream("src/main/resources/pongresources/Ball.png"));
    }

    public Scene getScene() {
        return scene;
    }

    public void startGame(){

        //TODO Start a new game by creating an instance of model which we can then use

        timer.start();
    }

    private void draw(){
        gameLayer.clearRect(0, 0, model.getSizeX() ,model.getSizeY());
        MovableObject player = model.getPlayer();
        gameLayer.drawImage(playerImg, player.getXpos(), player.getYpos(), player.getWidth(), player.getHeight());
        List<MovableObject> aliens = new ArrayList<>();
        aliens = model.getAliens();
        for (int i = 0; i < aliens.size(); i++) {
            gameLayer.drawImage(playerImg, aliens.get(i).getXpos(), aliens.get(i).getYpos(), aliens.get(i).getWidth(), aliens.get(i).getHeight());
        }
    }
}
