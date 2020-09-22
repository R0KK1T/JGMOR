package edu.chalmers.projecttemplate.model.pongmodel;

import edu.chalmers.projecttemplate.model.ProjectModel;

public class PongModel {
    private ProjectModel projectModel;
    PongBall pongBall;
    PongPaddle pongPaddle;

    void updateGame (){
        pongBall.updatePosition();
        pongPaddle.updatePosition();
    }

    public PongBall getPongBall() {
        return pongBall;
    }

    public PongPaddle getPongPaddle() {
        return pongPaddle;
    }
}
