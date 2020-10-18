package edu.chalmers.projecttemplate.model.pongmodel;

public class PongModel {
    PongBall pongBall;
    PongPaddle pongLeftPaddle,pongRightPaddle;
    PongAI pongAI;
    private final int windowSizeX = 1600;
    private final int windowSizeY = 980;
    private final int paddleVelocity = 3;
    private final int ballVelocity = 5;
    private int leftPlayerScore = 0;
    private int rightPlayerScore = 0;
    private boolean isPaused;

    public PongModel (){
        pongBall = new PongBall(windowSizeX/2, windowSizeY/2, 40, 40,ballVelocity);
        pongRightPaddle = new PongPaddle(windowSizeX - 50,windowSizeY/2, 200,40, windowSizeY,0);
        pongLeftPaddle = new PongPaddle(10, windowSizeY/2, 200,40, windowSizeY, 0);
        pongAI = new PongAI(pongRightPaddle,pongBall);
        pongBall.resetBall();
        isPaused = false;
    }

    public void update (){
        //calls the ball behaviour functions and the update functions in the game objects
        if (isPaused == false){
            ballHorizontalBounce();
            ballVerticalBounce();
            pongBall.updatePosition();
            pongAI.movePaddle(paddleVelocity);
            pongRightPaddle.updatePosition();
            pongLeftPaddle.updatePosition();
            ballRespawn();
        }
    }


    private boolean ballIntersectCheck(){
        //Checks if the ball touches either of the paddles
        if (pongBall.intersects(pongLeftPaddle) || pongBall.intersects(pongRightPaddle)){
            return true;
        }
        return false;
    }

    private void ballHorizontalBounce(){
        //Switches the horizontal direction of the ball if it touches any of the paddles
        if (ballIntersectCheck()){
            pongBall.xDirection = -pongBall.xDirection;
        }
    }

    private void ballVerticalBounce(){
        //Switches the vertical direction of the ball if it touches either the top or the bottom of the game screen
        if ((pongBall.getY()) < 0 || ((pongBall.getY() + pongBall.getHeight()) > windowSizeY)){
            pongBall.yDirection = -pongBall.yDirection;
        }
    }

    private void ballRespawn(){
        //Triggers the resetBall method which respawns the ball if it passes either of the paddles
        //and increases the score for the opposite player
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

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public boolean isPaused() {
        return isPaused;
    }
}
