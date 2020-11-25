package edu.chalmers.projecttemplate.model.pongmodel;

import java.util.Random;

/**
 * Handles the movement of the right paddle if it's AI controlled
 */
public class PongAI {
    private PongBall pongBall;
    private PongPaddle pongPaddle;

    /**
     * Constructs an instance of PongAI with a specified pongBall and pongPaddle
     * @param pongPaddle The paddle the AI is going to control
     * @param pongBall The ball that the AI needs to observe
     */
    public PongAI (PongPaddle pongPaddle, PongBall pongBall){
        this.pongBall = pongBall;
        this.pongPaddle = pongPaddle;
    }

    /**
     * An update-like method, telling its paddle to move up or down depending on the ball's position
     * @param paddleVelocity the value that the paddle's move methods need
     */
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

    /**
     * Checks if the ball is above the paddle
     * @return returns true if ball is above
     */
    private boolean checkBallAbove(){
        //Checks if the ball is above the paddle
        Random random = new Random(50);
        if ((pongPaddle.getY() + random.nextDouble()) > pongBall.getY()){
            return true;
        }
        return false;
    }

    /**
     * Checks if the ball is below the paddle and returns a boolean
     * @return returns true if ball is below
     */
    private boolean checkBallBelow(){
        //Checks if the ball is below the paddle
        Random random = new Random(50);
        if ((pongPaddle.getY() + pongPaddle.getHeight() + random.nextDouble()) < pongBall.getY()){
            return true;
        }
        return false;
    }
}
