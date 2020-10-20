package edu.chalmers.projecttemplate.model.pongmodel;

import java.util.ArrayList;

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
    private boolean AIEnabled;
    private ArrayList<GameObject> gameObjects;

    public PongModel (){
        gameObjects = new ArrayList<>();
        pongBall = new PongBall(windowSizeX/2, windowSizeY/2, 40, 40,ballVelocity);
        pongRightPaddle = new PongPaddle(windowSizeX - 50,windowSizeY/2, 200,40, windowSizeY,0);
        pongLeftPaddle = new PongPaddle(10, windowSizeY/2, 200,40, windowSizeY, 0);
        gameObjects.add(pongBall);
        gameObjects.add(pongRightPaddle);
        gameObjects.add(pongLeftPaddle);
        pongAI = new PongAI(pongRightPaddle,pongBall);
        pongBall.resetBall();
        isPaused = false;
        AIEnabled = false;
    }

    public void update (){
        //calls the ball behaviour functions and the update functions in the game objects
        if (isPaused == false){
            ballHorizontalBounce();
            ballVerticalBounce();
            pongBall.updatePosition();
            if (AIEnabled) {
                pongAI.movePaddle(paddleVelocity);
            }
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

    public void movePongRightPaddleUp(){
        pongRightPaddle.moveUp(paddleVelocity);
    }

    public void movePongRightPaddleDown(){
        pongRightPaddle.moveDown(paddleVelocity);
    }

    public void stopPongRightPaddle(){
        pongRightPaddle.stopMoving();
    }

    public void movePongLeftPaddleUp(){
        pongLeftPaddle.moveUp(paddleVelocity);
    }

    public void movePongLeftPaddleDown(){
        pongLeftPaddle.moveDown(paddleVelocity);
    }

    public void stopPongLeftPaddle(){
        pongLeftPaddle.stopMoving();
    }


    public int getSizeX() {
        //return size x of playing field
        return windowSizeX;
    }

    public int getSizeY() {
        //return size y of playing field
        return windowSizeY;
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

    public void setAIEnabled(boolean AIEnabled) {
        this.AIEnabled = AIEnabled;
    }

    public boolean isAIEnabled() {
        return AIEnabled;
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }
}
