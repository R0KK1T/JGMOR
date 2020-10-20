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

/**
 * Represents the view of the model representing the game space invaders
 *
 */
public class SpaceInvadersView{

    Image backgroundImg;
    Image playerImg;
    Image alienImg;
    Image barrierImg;
    Image projectileImg;

    private int windowSizeX;
    private int windowSizeY;

    GraphicsContext backgroundLayer;
    GraphicsContext gameLayer;
    Text scoreText;

    private Scene scene;

    /**
     * Constructs the view and initializes the scene and all necessary images
     *
     */
    public SpaceInvadersView(int windowSizeX, int windowSizeY) throws Exception{
        this.windowSizeX = windowSizeX;
        this.windowSizeY = windowSizeY;

        //get all images
        initImages();

        //setup scene
        initScene();
    }

    /**
     * Initializes all components used by the scene and adds them to the scene
     *
     */
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

    /**
     * Initializes all images used in the game as sprites which are located in the recourses folder
     *
     */
    private void initImages() throws FileNotFoundException {
        //get background image from path
        backgroundImg = new Image(new FileInputStream("src/main/resources/pongresources/Background.png"));

        //get player image from path
        playerImg = new Image(new FileInputStream("src/main/resources/spaceinvadesresources/SpaceInvadersPlayer.png"));

        //get alien image from path
        alienImg = new Image(new FileInputStream("src/main/resources/spaceinvadesresources/SpaceInvadersAlien.png"));

        //get barrier image from path
        barrierImg = new Image(new FileInputStream("src/main/resources/pongresources/Ball.png"));

        //get projectile image from path
        projectileImg = new Image(new FileInputStream("src/main/resources/pongresources/Ball.png"));
    }

    /**
     * Returns the scene that view is responsible for
     *
     * @return scene used for drawing the game
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Clears gameLayer so that the canvas becomes empty meaning that images can be redrawn
     *
     */
    public void clearDrawingArea(){
        //clear gameLayer
        gameLayer.clearRect(0, 0, windowSizeX, windowSizeY);
    }

    /**
     * Draws an object at specified coordinates, with specified and using image depending on what type of object sent
     *
     * @param posX x position of the object being drawn
     * @param posY y position of the object being drawn
     * @param width width of the object being drawn
     * @param height height of the object being drawn
     * @param type type of the object being drawn used to determine which image to draw
     */
    public void draw(int posX, int posY, int width, int height, String type){
        //draw object
        switch (type){
            case "Spaceship":
                gameLayer.drawImage(playerImg, posX, posY, width, height);
                break;
            case "Alien":
                gameLayer.drawImage(alienImg, posX, posY, width, height);
                break;
            case "Projectile":
                gameLayer.drawImage(projectileImg, posX, posY, width, height);
                break;
            case "Barrier":
                gameLayer.drawImage(barrierImg, posX, posY, width, height);
                break;
            default:
        }
    }

    /**
     * Changes the Text object used for displaying score depending on input parameter
     *
     * @param score string for current score
     */
    public void displayScore(String score){
        scoreText.setText(score);
    }

    /**
     * Draws a "pause screen" used for displaying that the game is paused
     *
     */
    public void drawPauseScreen(){
        gameLayer.drawImage(backgroundImg, 0, 0, windowSizeX, windowSizeY);
    }
}
