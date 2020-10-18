package edu.chalmers.projecttemplate.model.pongmodel;

import java.util.Random;

public class PongAI {
    private PongBall pongBall;
    private PongPaddle pongPaddle;
    public PongAI (PongPaddle pongPaddle, PongBall pongBall){
        this.pongBall = pongBall;
        this.pongPaddle = pongPaddle;
    }
    public void movePaddle(int paddleVelocity ){
        //Moves the paddle upwards if the ball is above
        if (checkBallAbove()){
            pongPaddle.moveUp(paddleVelocity);
        }
        //Moves the paddle downwards if the ball is below
        if (checkBallBelow()){
            pongPaddle.moveDown(paddleVelocity);
        }
    }

    private boolean checkBallAbove(){
        //Checks if the ball is above the paddle
        Random random = new Random(50);
        if ((pongPaddle.getY() + random.nextDouble()) > pongBall.getY()){
            return true;
        }
        return false;
    }

    private boolean checkBallBelow(){
        //Checks if the ball is below the paddle
        Random random = new Random(50);
        if ((pongPaddle.getY() + pongPaddle.getHeight() + random.nextDouble()) < pongBall.getY()){
            return true;
        }
        return false;
    }
}
