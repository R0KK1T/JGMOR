package edu.chalmers.projecttemplate.view.froggerview;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class FroggerView {

    HashMap<String, Image> imageHashMap = new HashMap<>();

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
        backgroundLayer.drawImage(imageHashMap.get("Background"), 0, 0, windowSizeX, windowSizeY);

        scene = new Scene(pane);
    }

    private void initImages() throws FileNotFoundException {
        imageHashMap.put("Background", new Image(new FileInputStream("src/main/resources/froggerresources/froggerBackground.png")));
        imageHashMap.put("Frog", new Image(new FileInputStream("src/main/resources/froggerresources/frog.png")));
        imageHashMap.put("Car", new Image(new FileInputStream("src/main/resources/froggerresources/car.png")));
        imageHashMap.put("Log", new Image(new FileInputStream("src/main/resources/froggerresources/log.png")));
        imageHashMap.put("Grass", new Image(new FileInputStream("src/main/resources/froggerresources/grass.png")));
        imageHashMap.put("FinishedFrog", new Image(new FileInputStream("src/main/resources/froggerresources/frogOnFinishLine.png")));
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
                gameLayer.drawImage(imageHashMap.get("Frog"), posX, posY, width, height);
                break;
            case "CAR":
                //draw object
                gameLayer.drawImage(imageHashMap.get("Car"), posX, posY, width, height);
                break;
            case "LOG":
                //draw object
                gameLayer.drawImage(imageHashMap.get("Log"), posX, posY, width, height);
                break;
            case "GRASS":
                //draw object
                gameLayer.drawImage(imageHashMap.get("Grass"), posX, posY, width, height);
                break;
            case "FINISHLINEFROG":
                //draw object
                gameLayer.drawImage(imageHashMap.get("FinishedFrog"), posX, posY, width, height);
                break;
            default:
                break;
        }
    }

}
