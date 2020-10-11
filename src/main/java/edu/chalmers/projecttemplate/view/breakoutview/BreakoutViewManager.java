package edu.chalmers.projecttemplate.view.breakoutview;

import edu.chalmers.projecttemplate.model.breakoutmodel.Paddle;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BreakoutViewManager  {
    Image paddleImg;
    public BreakoutViewManager() {}
    /*
     * Draw paddle
     */
    public void drawPaddle(Paddle paddle) throws IOException {
        InputStream inStream = Files.newInputStream(Paths.get("src/main/resources/breakoutresources/images/paddle.png"));
        paddleImg = new Image(inStream, paddle.getWidth(), paddle.getHeight(), false, true);
        paddle.setFill(new ImagePattern(paddleImg));
    }
}
