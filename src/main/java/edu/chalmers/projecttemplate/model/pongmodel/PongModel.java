package edu.chalmers.projecttemplate.model.pongmodel;

public class PongModel {
    PongBall pongBall;
    PongPaddle pongLeftPaddle,pongRightPaddle;
    PongAI pongAI;
    private final int windowSizeX = 1920;
    private final int windowSizeY = 1080;
    private final int paddleVelocity = 3;
    private final int ballVelocity = 5;
    private int leftPlayerScore = 0;
    private int rightPlayerScore = 0;

    public PongModel (){
        pongBall = new PongBall(windowSizeX/2, windowSizeY/2, 40, 40,ballVelocity);
        pongRightPaddle = new PongPaddle(windowSizeX - 50,windowSizeY/2, 200,40, windowSizeY,0);
        pongLeftPaddle = new PongPaddle(10, windowSizeY/2, 200,40, windowSizeY, 0);
        pongAI = new PongAI(pongRightPaddle,pongBall);
        pongBall.resetBall();
    }

    public void update (){
        //calls the ball behaviour functions and the update functions in the game objects
        ballHorizontalBounce();
        ballVerticalBounce();
        pongBall.updatePosition();
        pongAI.movePaddle(paddleVelocity);
        pongRightPaddle.updatePosition();
        pongLeftPaddle.updatePosition();
        ballRespawn();
    }


    private boolean ballIntersectCheck(){
        if (pongBall.intersects(pongLeftPaddle) || pongBall.intersects(pongRightPaddle)){
            return true;
        }
        return false;
    }

    private void ballHorizontalBounce(){
        if (ballIntersectCheck()){
            pongBall.xDirection = -pongBall.xDirection;
        }
    }

    private void ballVerticalBounce(){
        if ((pongBall.getY()) < 0 || ((pongBall.getY() + pongBall.getHeight()) > windowSizeY)){
            pongBall.yDirection = -pongBall.yDirection;
        }
    }

    private void ballRespawn(){
        if(pongBall.getX() + pongBall.getWidth() < 0){
            leftPlayerScore++;
            pongBall.resetBall();
        }
        if(pongBall.getX() > windowSizeX){
            rightPlayerScore++;
            pongBall.resetBall();
        }
    }

    public PongBall getPongBall() {
        //returns the ball object
        return pongBall;
    }

    public PongPaddle getPongRightPaddle() {
        //return the right paddle object
        return pongRightPaddle;
    }
    public PongPaddle getPongLeftPaddle() {
        //return the left paddle object
        return pongLeftPaddle;
    }
    public int getSizeX() {
        //return size x of playing field
        return windowSizeX;
    }

    public int getSizeY() {
        //return size y of playing field
        return windowSizeY;
    }

    public int getPaddleVelocity() {
        return paddleVelocity;
    }

    public int getLeftPlayerScore() {
        return leftPlayerScore;
    }

    public int getRightPlayerScore() {
        return rightPlayerScore;
    }
}
