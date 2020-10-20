package edu.chalmers.projecttemplate.model;

import edu.chalmers.projecttemplate.model.pongmodel.PongPaddle;
import org.junit.Assert;
import org.junit.Test;

import java.security.PublicKey;

public class PongTest {
    @Test
    public void PaddleUpdateTest()
    {
        PongPaddle paddle1 = new PongPaddle(5,99,10,10,100,0);
        paddle1.updatePosition();
        Assert.assertTrue(paddle1.getY() == (paddle1.getMaxY()-paddle1.getHeight()));
        PongPaddle paddle2 = new PongPaddle(5,-1,10,10,100,0);
        paddle2.updatePosition();
        Assert.assertTrue(paddle2.getY() == paddle2.getMinY());

    }
    @Test
    public void PaddleMoveTest(){
        PongPaddle paddle = new PongPaddle(5,10,10,10, 100,0);
        paddle.moveDown(1);
        paddle.updatePosition();
        Assert.assertTrue(paddle.getY() > 10);
        paddle.moveUp(4);
        paddle.updatePosition();
        Assert.assertTrue(paddle.getY() < 10);
        paddle.moveDown(3);
    }
}
