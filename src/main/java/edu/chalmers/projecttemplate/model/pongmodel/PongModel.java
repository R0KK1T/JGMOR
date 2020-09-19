package edu.chalmers.projecttemplate.model.pongmodel;

import edu.chalmers.projecttemplate.controller.pongcontroller.PongController;

public class PongModel {
    private PongController pongController;
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
