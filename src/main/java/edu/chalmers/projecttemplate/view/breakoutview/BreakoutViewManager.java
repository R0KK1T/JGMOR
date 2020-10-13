package edu.chalmers.projecttemplate.view.breakoutview;

import edu.chalmers.projecttemplate.model.breakoutmodel.Ball;
import edu.chalmers.projecttemplate.model.breakoutmodel.Brick;
import edu.chalmers.projecttemplate.model.breakoutmodel.Paddle;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
/*
 * BreakoutViewManager is responsible for drawing game images and other things that have to do with View
 * The Singleton's purpose is to control object creation, limiting the number of objects to only one.
 */

public class BreakoutViewManager  {
    private static Image paddleImg;
    private static Image ballImg;
    private static Image brickImg;
    private static BreakoutViewManager breakoutViewManager = new BreakoutViewManager();

    /* A private Constructor prevents any other
     * class from instantiating.
     */
    private BreakoutViewManager() {}
    /* Static 'instance' method */
    public static BreakoutViewManager getInstance() {
        return breakoutViewManager;
    }
    /*
     * Drawing paddle
     */
    public static void drawPaddle(Paddle paddle) throws IOException {
        InputStream inStream = Files.newInputStream(Paths.get("src/main/resources/breakoutresources/images/paddle.png"));
        paddleImg = new Image(inStream, paddle.getWidth(), paddle.getHeight(), false, true);
        paddle.setFill(new ImagePattern(paddleImg));
    }
    /*
     * Drawing Ball
     */
    public static void drawBall(Ball ball) throws IOException {
        InputStream inStream = Files.newInputStream(Paths.get("src/main/resources/breakoutresources/images/ball.png"));
        ballImg = new Image(inStream, ball.getWidth(), ball.getHeight(), false, true);
        ball.setFill(new ImagePattern(ballImg));
    }
    /*
     * Drawing Brick
     */
    public static void drawBrick(Brick brick) throws IOException {
        String name = null;
        if (brick.getBrickPoint()==4)
            name = "redbrick.png";
        else if (brick.getBrickPoint()==3)
            name = "orangebrick.png";
        else if (brick.getBrickPoint()==2)
            name = "greenbrick.png";
        else
            name = "yellowbrick.png";
        InputStream inStream = Files.newInputStream(Paths.get("src/main/resources/breakoutresources/images/"+name+""));
        brickImg = new Image(inStream, brick.getWidth(), brick.getHeight(), false, true);
        brick.setFill(new ImagePattern(brickImg));
    }

}
