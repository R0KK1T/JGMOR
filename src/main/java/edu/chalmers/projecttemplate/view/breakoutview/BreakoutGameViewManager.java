package edu.chalmers.projecttemplate.view.breakoutview;

import edu.chalmers.projecttemplate.model.breakoutmodel.Brick;
import edu.chalmers.projecttemplate.model.common.IPositionableInt;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/*
 * BreakoutGameViewManager is responsible for drawing game images and other things that have to do with View.
 */
public class BreakoutGameViewManager {
    private Canvas canvas;
    private GraphicsContext gc;
    private double width;
    private double height;
    private Image bc;
    private Image paddleImg;
    private Image ballImg;
    private Image gameOver;
    private Image redBrick;
    private Image greeBrick;
    private Image orangeBrick;
    private Image yellowBrick;
    public BreakoutGameViewManager() throws FileNotFoundException {
        this.width = 900;
        this.height = 550;
        canvas = new Canvas(width, height);
        gc = canvas.getGraphicsContext2D();
        initImages();
    }
    /*
     * Drawing the game area
     */
    public void drawGameArea() {
        gc.clearRect(0, 0, width, height);
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width, height);
        gc.drawImage(bc, 0, 0, width, height);
    }
    /*
     * Initialize images
     */
    private void initImages() throws FileNotFoundException {
        bc = new Image(new FileInputStream("src/main/resources/breakoutresources/images/bc3.gif"));
        paddleImg = new Image(new FileInputStream("src/main/resources/breakoutresources/images/paddle.png"));
        ballImg = new Image(new FileInputStream("src/main/resources/breakoutresources/images/ball.png"));
        gameOver = new Image(new FileInputStream("src/main/resources/breakoutresources/images/gameOver.png"));
        redBrick = new Image(new FileInputStream("src/main/resources/breakoutresources/images/redbrick.png"));
        orangeBrick = new Image(new FileInputStream("src/main/resources/breakoutresources/images/orangebrick.png"));
        greeBrick = new Image(new FileInputStream("src/main/resources/breakoutresources/images/greenbrick.png"));
        yellowBrick = new Image(new FileInputStream("src/main/resources/breakoutresources/images/yellowbrick.png"));
    }
    /*
     * Drawing the paddle
     */
    public void drawPaddle(IPositionableInt paddle) {
        gc.setFill(Color.WHITE);
        gc.drawImage(paddleImg, paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight());
    }
    /*
     * Drawing the ball
     */
    public void drawBall(IPositionableInt ball) {
        gc.setFill(Color.WHITE);
        gc.fillOval(ball.getX(), ball.getY() , ball.getHeight(), ball.getHeight());
        gc.drawImage(ballImg, ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
    }
    /*
     * Drawing Brick
     */
    public void drawBrick(Brick brick) {
        if (brick.getBrickHit()==4) {
            gc.setFill(Color.RED);
            gc.fillRect(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
            gc.drawImage(redBrick, brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
        }
        else if (brick.getBrickHit()==3) {
            gc.setFill(Color.ORANGE);
            gc.fillRect(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
            gc.drawImage(orangeBrick, brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
        }
        else if (brick.getBrickHit()==2) {
            gc.setFill(Color.GREEN);
            gc.fillRect(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
            gc.drawImage(greeBrick, brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
        }
        else {
            gc.setFill(Color.YELLOW);
            gc.fillRect(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
            gc.drawImage(yellowBrick, brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
        }
    }
    /*
     * Drawing game over
     */
    public void drawGameOver() {
        gc.setFill(Color.BLACK);
        gc.drawImage(gameOver, 200, 200, 500, 200);
    }
    //get canvas
    public Canvas getCanvas() {
        return canvas;
    }
}
