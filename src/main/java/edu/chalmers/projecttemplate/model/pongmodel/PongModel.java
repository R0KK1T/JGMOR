package edu.chalmers.projecttemplate.model.pongmodel;

import edu.chalmers.projecttemplate.model.ProjectModel;

public class PongModel {
    private ProjectModel projectModel;
    PongBall pongBall;
    PongPaddle pongLeftPaddle,pongRightPaddle;
    private final int windowSizeX = 1000;
    private final int windowSizeY = 900;

    public PongModel (){
        pongBall = new PongBall(400, 300, 40, 40,0);
        pongRightPaddle = new PongPaddle(750,300, 100,40);
        pongLeftPaddle = new PongPaddle(50, 300, 100,40);
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
