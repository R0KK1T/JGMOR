package edu.chalmers.projecttemplate.view.froggerview;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FroggerView {

    Image backgroundImg;
    Image playerImg;

    private int windowSizeX;
    private int windowSizeY;

    GraphicsContext backgroundLayer;
    GraphicsContext gameLayer;

    private Scene scene;

    public FroggerView(int windowSizeX, int windowSizeY) throws Exception{
        this.windowSizeX = windowSizeX;
        this.windowSizeY = windowSizeY;

        //get all images
        initImages();

        //setup scene
        initScene();
    }

    public void initScene() {
        //create instance of model

        //setup the 2 different layered canvases
        Canvas backgroundCanvas = new Canvas(windowSizeX, windowSizeY);
        backgroundLayer = backgroundCanvas.getGraphicsContext2D();
        Canvas gameCanvas = new Canvas(windowSizeX, windowSizeY);
        gameLayer = gameCanvas.getGraphicsContext2D();

        //add canvases to the pane
        Pane pane = new Pane(backgroundCanvas, gameCanvas);

        //draw the background
        backgroundLayer.drawImage(backgroundImg, 0, 0, windowSizeX, windowSizeY);

        scene = new Scene(pane);
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

    public void clearDrawingArea(){
        //clear gameLayer
        gameLayer.clearRect(0, 0, windowSizeX, windowSizeY);
    }

    public void draw(int posX, int posY, int width, int height, String type){
        switch(type){
            case "FROG":
                //draw object
                gameLayer.drawImage(playerImg, posX, posY, width, height);
                break;
            case "CAR":
                //draw object
                gameLayer.drawImage(playerImg, posX, posY, width, height);
                break;
            case "LOG":
                //draw object
                gameLayer.drawImage(playerImg, posX, posY, width, height);
                break;
            case "GRASS":
                //draw object
                gameLayer.drawImage(playerImg, posX, posY, width, height);
                break;
            case "FINISHLINEFROG":
                //draw object
                gameLayer.drawImage(playerImg, posX, posY, width, height);
                break;
            default:
                //draw object
                gameLayer.drawImage(playerImg, posX, posY, width, height);
                break;
        }

    }

}
