package edu.chalmers.projecttemplate.view.spaceInvadersView;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SpaceInvadersView{

    Image backgroundImg;
    Image playerImg;

    private int windowSizeX;
    private int windowSizeY;

    GraphicsContext backgroundLayer;
    GraphicsContext gameLayer;
    Text scoreText;

    private Scene scene;

    public SpaceInvadersView(int windowSizeX, int windowSizeY) throws Exception{
        this.windowSizeX = windowSizeX;
        this.windowSizeY = windowSizeY;

        //get all images
        initImages();

        //setup scene
        initScene();
    }

    public void initScene() {
        //setup the 2 different layered canvases
        Canvas backgroundCanvas = new Canvas(windowSizeX, windowSizeY);
        backgroundLayer = backgroundCanvas.getGraphicsContext2D();
        Canvas gameCanvas = new Canvas(windowSizeX, windowSizeY);
        gameLayer = gameCanvas.getGraphicsContext2D();

        //init score text
        scoreText = new Text();
        scoreText.setX(30);
        scoreText.setY(30);
        scoreText.setScaleX(3);
        scoreText.setScaleY(3);
        scoreText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
        scoreText.setFill(Color.WHITESMOKE);

        //add canvases to the pane
        Pane pane = new Pane(backgroundCanvas, gameCanvas, scoreText);

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
        //draw object
        switch (type){
            case "Spaceship":
                gameLayer.drawImage(playerImg, posX, posY, width, height);
                break;
            case "Alien":
                gameLayer.drawImage(playerImg, posX, posY, width, height);
                break;
            case "Projectile":
                gameLayer.drawImage(playerImg, posX, posY, width, height);
                break;
            case "Barrier":
                gameLayer.drawImage(playerImg, posX, posY, width, height);
                break;
            default:
        }
    }

    public void displayScore(String score){
        scoreText.setText(score);
    }
}
