package edu.chalmers.projecttemplate.view.pongview;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PongView {
    Image backgroundImg;
    Image playerImg;
    Text leftScore;
    Text rightScore;

    private int windowSizeX;
    private int windowSizeY;

    GraphicsContext backgroundLayer;
    GraphicsContext gameLayer;

    private Scene scene;

    public PongView(int windowSizeX, int windowSizeY) throws Exception{
        this.windowSizeX = windowSizeX;
        this.windowSizeY = windowSizeY;

        //get all images
        initImages();

        //place text
        initText();

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

    private void initText(){
        leftScore = new Text();
        rightScore = new Text();
        leftScore.setFont(new Font(20));
        rightScore.setFont(new Font(20));
        leftScore.setText("0");
        rightScore.setText("0");
        rightScore.setX(windowSizeX+(windowSizeX/10));
        leftScore.setX(windowSizeX-(windowSizeX/10));
        rightScore.setY(windowSizeY/25);
        leftScore.setY(windowSizeY/25);

    }

    public Scene getScene() {
        return scene;
    }

    public void clearDrawingArea(){
        //clear gameLayer
        gameLayer.clearRect(0, 0, windowSizeX, windowSizeY);
    }

    public void draw(double posX, double posY, double width, double height){
        //draw object
        gameLayer.drawImage(playerImg, posX, posY, width, height);

    }

    public void setLeftScoreText (String string){
        leftScore.setText(string);
    }

    public void setRightScoreText (String string){
        rightScore.setText(string);
    }


}


