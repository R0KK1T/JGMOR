package edu.chalmers.projecttemplate.model;

import edu.chalmers.projecttemplate.model.pongmodel.PongAI;
import edu.chalmers.projecttemplate.model.pongmodel.PongBall;
import edu.chalmers.projecttemplate.model.pongmodel.PongModel;
import edu.chalmers.projecttemplate.model.pongmodel.PongPaddle;
import org.junit.Assert;
import org.junit.Test;


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
    }
    @Test
    public void BallMoveTest(){
        PongBall ball = new PongBall(10,10,10,10,1);
        ball.resetBall();
        ball.updatePosition();
        Assert.assertTrue(ball.getX() != 10 || ball.getY() != 10);
    }
    @Test
    public void PongAITest(){
        PongBall ball = new PongBall(25,10,10,10,10);
        PongPaddle paddle = new PongPaddle(5,10,10,10,100,0);
        PongAI AI = new PongAI(paddle,ball);
        ball.resetBall();
        while(paddle.getY() == 10) {
            ball.updatePosition();
            AI.movePaddle(10);
            paddle.updatePosition();
        }
        Assert.assertTrue(paddle.getY() != 10);
    }
    @Test
    public void PongModelTest(){
        PongModel pongModel = new PongModel();
        int x = 0;
        while ((pongModel.getGameObjects().get(0).getX() < pongModel.getSizeX() || pongModel.getGameObjects().get(0).getX() > 0) && x < pongModel.getSizeX()*5){
            pongModel.update();
            x++;
        }
        if (x < pongModel.getSizeX()*5) {
            Assert.assertTrue(pongModel.getGameObjects().get(0).getX() == (pongModel.getSizeX() / 2));
        }
        else {
            Assert.assertTrue(pongModel.getGameObjects().get(0).getX() < pongModel.getSizeX() || pongModel.getGameObjects().get(0).getX() > 0);
        }
        x = 0;
        int y = 0;
        double y1 = pongModel.getGameObjects().get(0).getY();
        pongModel.update();
        double y2 = pongModel.getGameObjects().get(0).getY();
        double deltaY1 = y2 - y1;
        double deltaY2 = 0;
        while ((y < 1) && x < pongModel.getSizeX()*5){
            double y3 = pongModel.getGameObjects().get(0).getY();
            pongModel.update();
            double y4 = pongModel.getGameObjects().get(0).getY();
            deltaY2 = y4 - y3;
            if (deltaY1 != deltaY2){
                if (deltaY2 != -deltaY1){
                    y = 1;
                }
            }
            x++;
        }
        if (x < pongModel.getSizeX()*5) {
            Assert.assertTrue(deltaY2 != deltaY1);
        }
        else {
            Assert.assertTrue(pongModel.getGameObjects().get(0).getX() < pongModel.getSizeX() || pongModel.getGameObjects().get(0).getX() > 0);
        }

    }
   
}
