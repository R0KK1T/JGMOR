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
        if (checkBallAbove()){
            pongPaddle.moveUp(paddleVelocity);
        }
        if (checkBallBelow()){
            pongPaddle.moveDown(paddleVelocity);
        }
    }

    private boolean checkBallAbove(){
        Random random = new Random(15);
        if ((pongPaddle.getY() + random.nextDouble()) > pongBall.getY()){
            return true;
        }
        return false;
    }

    private boolean checkBallBelow(){
        Random random = new Random(15);
        if ((pongPaddle.getY() + pongPaddle.getHeight() + random.nextDouble()) < pongBall.getY()){
            return true;
        }
        return false;
    }
}
