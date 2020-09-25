package edu.chalmers.projecttemplate.model.pongmodel;

import edu.chalmers.projecttemplate.model.ProjectModel;

public class PongModel {
    private ProjectModel projectModel;
    PongBall pongBall;
    PongPaddle pongLeftPaddle;
    PongPaddle pongRightPaddle;

    public PongModel (){
        pongBall = new PongBall(400, 300, 40, 40,0);
        pongRightPaddle = new PongPaddle(750,300, 100,40);
        pongLeftPaddle = new PongPaddle(50, 300, 100,40);
    }

    void updateGame (){
        pongBall.updatePosition();
        pongRightPaddle.updatePosition();
        pongLeftPaddle.updatePosition();
    }

    public PongBall getPongBall() {
        return pongBall;
    }

    public PongPaddle getPongRightPaddle() {
        return pongRightPaddle;
    }
    public PongPaddle getPongLeftPaddle() {
        return pongLeftPaddle;
    }
}
