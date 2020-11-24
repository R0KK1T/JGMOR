package edu.chalmers.projecttemplate.view.froggerview;

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
import java.util.HashMap;

/**
 * Represents the view of the model representing the game Frogger
 */
public class FroggerView {

    private HashMap<String, Image> imageHashMap = new HashMap<>();

    private int windowSizeX;
    private int windowSizeY;

    private GraphicsContext backgroundLayer;
    private GraphicsContext gameLayer;

    private Text scoreAndLevelText;

    private Scene scene;

    /**
     * Constructs the view and initializes the scene and all necessary images
     */
    public FroggerView(int windowSizeX, int windowSizeY) throws Exception{
        this.windowSizeX = windowSizeX;
        this.windowSizeY = windowSizeY;

        //get all images
        initImages();

        //setup scene
        initScene();
    }

    /**
     * Initializes all components used by the scene and adds them to the scene
     */
    private void initScene() {
        //create instance of model

        //setup the 2 different layered canvases
        Canvas backgroundCanvas = new Canvas(windowSizeX, windowSizeY);
        backgroundLayer = backgroundCanvas.getGraphicsContext2D();
        Canvas gameCanvas = new Canvas(windowSizeX, windowSizeY);
        gameLayer = gameCanvas.getGraphicsContext2D();

        //init scoreText
        scoreAndLevelText = new Text();
        scoreAndLevelText.setX(75);
        scoreAndLevelText.setY(11);
        scoreAndLevelText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
        scoreAndLevelText.setFill(Color.WHITESMOKE);

        //add canvases to the pane
        Pane pane = new Pane(backgroundCanvas, gameCanvas, scoreAndLevelText);

        //draw the background
        backgroundLayer.drawImage(imageHashMap.get("Background"), 0, 0, windowSizeX, windowSizeY);

        scene = new Scene(pane);
    }

    /**
     * Initializes all images used in the game as sprites which are located in the recourse folder
     * and adds them to the hashmap imageHashMap
     */
    private void initImages() throws FileNotFoundException {
        imageHashMap.put("Background", new Image(new FileInputStream("src/main/resources/froggerresources/froggerBackground.png")));
        imageHashMap.put("FROG", new Image(new FileInputStream("src/main/resources/froggerresources/frog.png")));
        imageHashMap.put("CAR", new Image(new FileInputStream("src/main/resources/froggerresources/car.png")));
        imageHashMap.put("LOG", new Image(new FileInputStream("src/main/resources/froggerresources/log.png")));
        imageHashMap.put("GRASS", new Image(new FileInputStream("src/main/resources/froggerresources/grass.png")));
        imageHashMap.put("FINISHLINEFROG", new Image(new FileInputStream("src/main/resources/froggerresources/frogOnFinishLine.png")));
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
     */
    public void clearDrawingArea(){
        //clear gameLayer
        gameLayer.clearRect(0, 0, windowSizeX, windowSizeY);
    }

    /**
     * Draws an object at specified coordinates, with specified  dimensions using image depending on type
     *
     * @param posX x position of the object being drawn
     * @param posY y position of the object being drawn
     * @param width width of the object being drawn
     * @param height height of the object being drawn
     * @param type type of the object being drawn used to determine which image to draw
     */
    public void draw(int posX, int posY, int width, int height, String type){
        gameLayer.drawImage(imageHashMap.get(type), posX, posY, width, height);
    }

    /**
     * Changes the Text object used for displaying score depending on input parameters score and level
     *
     * @param score int for current score.
     * @param level int for current level
     */
    public void displayScore(int score, int level){
        scoreAndLevelText.setText("Level: " + level + " Score: " + score);
    }

    /**
     * Draws miniaturized versions of the Frog texture depending on parameter currentLifeCount
     * @param currentLifeCount the amount of pictures to draw
     */
    public void drawLives(int currentLifeCount) {
        int width = 20;
        for (int i = 0; i < currentLifeCount; i++) {
            draw(i*width, windowSizeY - width, width, width, "FROG");
        }
    }
}
