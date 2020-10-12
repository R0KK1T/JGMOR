package edu.chalmers.projecttemplate.view.breakoutview;

import edu.chalmers.projecttemplate.model.breakoutmodel.Ball;
import edu.chalmers.projecttemplate.model.breakoutmodel.Paddle;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BreakoutViewManager  {
    Image paddleImg;
    Image ballImg;
    public BreakoutViewManager() {}
    /*
     * Drawing paddle
     */
    public void drawPaddle(Paddle paddle) throws IOException {
        InputStream inStream = Files.newInputStream(Paths.get("src/main/resources/breakoutresources/images/paddle.png"));
        paddleImg = new Image(inStream, paddle.getWidth(), paddle.getHeight(), false, true);
        paddle.setFill(new ImagePattern(paddleImg));
    }
    /*
     * Drawing Ball
     */
    public void drawBall(Ball ball) throws IOException {
        InputStream inStream = Files.newInputStream(Paths.get("src/main/resources/breakoutresources/images/ball.png"));
        ballImg = new Image(inStream, ball.getWidth(), ball.getHeight(), false, true);
        ball.setFill(new ImagePattern(ballImg));
    }
}
