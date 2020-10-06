package edu.chalmers.projecttemplate.model.pongmodel;

import edu.chalmers.projecttemplate.model.ProjectModel;

public class PongModel {
    private ProjectModel projectModel;
    PongBall pongBall;
    PongPaddle pongLeftPaddle,pongRightPaddle;
    private final int windowSizeX = 1000;
    private final int windowSizeY = 900;

    public PongModel (){
        pongBall = new PongBall(windowSizeX/2, windowSizeY/2, 40, 40,1);
        pongRightPaddle = new PongPaddle(windowSizeX - 50,windowSizeY/2, 200,40);
        pongLeftPaddle = new PongPaddle(10, windowSizeY/2, 200,40);
        pongBall.resetBall();
    }

    public void update (){
        //calls the update functions in the game objects

        pongBall.updatePosition();
        pongRightPaddle.updatePosition();
        pongLeftPaddle.updatePosition();
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
