package edu.chalmers.projecttemplate.model.pongmodel;

import edu.chalmers.projecttemplate.model.ProjectModel;

public class PongModel {
    private ProjectModel projectModel;
    PongBall pongBall;
    PongPaddle pongLeftPaddle,pongRightPaddle;
    private final int windowSizeX = 1920;
    private final int windowSizeY = 1080;

    public PongModel (){
        pongBall = new PongBall(windowSizeX/2, windowSizeY/2, 40, 40,5);
        pongRightPaddle = new PongPaddle(windowSizeX - 50,windowSizeY/2, 200,40, windowSizeY,0);
        pongLeftPaddle = new PongPaddle(10, windowSizeY/2, 200,40, windowSizeY, 0);
        pongBall.resetBall();
    }

    public void update (){
        //calls the update functions in the game objects
        ballHorizontalBounce();
        ballVerticalBounce();
        pongBall.updatePosition();
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
        if((pongBall.getX() + pongBall.getWidth() < 0) || (pongBall.getX() > windowSizeX)){
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


}
