package edu.chalmers.projecttemplate.model.pongmodel;

import java.util.ArrayList;

/**
 * The class responsible for the game logic for the game Pong
 */
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

    /**
     * Constructs an instance of PongModel
     */
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

    /**
     * Calls all the update methods of owned GameObjects and following
     * own methods:
     * ballHorizontalBounce
     * ballVerticalBounce
     * ballRespawn
     */
    public void update (){
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

    /**
     * Checks if the ball intersects with any of the paddles
     * @return returns true if the ball intersects
     */
    private boolean ballIntersectCheck(){
        //Checks if the ball touches either of the paddles
        if (pongBall.intersects(pongLeftPaddle) || pongBall.intersects(pongRightPaddle)){
            return true;
        }
        return false;
    }

    /**
     * Switches the horizontal direction of the ball if it touches any of the paddles
     */
    private void ballHorizontalBounce(){
        if (ballIntersectCheck()){
            pongBall.xDirection = -pongBall.xDirection;
        }
    }

    /**
     * Switches the vertical direction of the ball if it touches either the top or the bottom of the game screen
     */
    private void ballVerticalBounce(){

        if ((pongBall.getY()) < 0 || ((pongBall.getY() + pongBall.getHeight()) > windowSizeY)){
            pongBall.yDirection = -pongBall.yDirection;
        }
    }

    /**
     * Triggers the resetBall method which respawns the ball if it passes either of the paddles
     * and increases the score for the opposite player
     */
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

    /**
     * Calls the moveUp method of the right paddle using the paddleVelocity variable
     */
    public void movePongRightPaddleUp(){
        pongRightPaddle.moveUp(paddleVelocity);
    }

    /**
     * Calls the moveDown method of the right paddle using the paddleVelocity variable
     */
    public void movePongRightPaddleDown(){
        pongRightPaddle.moveDown(paddleVelocity);
    }

    /**
     * Calls the stopMoving method of the right paddle
     */
    public void stopPongRightPaddle(){
        pongRightPaddle.stopMoving();
    }

    /**
     * Calls the moveUp method of the left paddle using the paddleVelocity variable
     */
    public void movePongLeftPaddleUp(){
        pongLeftPaddle.moveUp(paddleVelocity);
    }

    /**
     * Calls the moveDown method of the left paddle using the paddleVelocity variable
     */
    public void movePongLeftPaddleDown(){
        pongLeftPaddle.moveDown(paddleVelocity);
    }

    /**
     * Calls the stopMoving method of the  paddle
     */
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
