package edu.chalmers.projecttemplate.model.breakoutmodel;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** THE WALL **/
public class Wall {
    AnchorPane gameArea;
    Button btnGameExit;
    Button btnGamePause;
    Label scoreLabel;
    BreakoutConfig breakoutConfig;
    Paddle paddle;
    Ball ball;
    private List<Brick> brickList;

    public Wall(AnchorPane gameArea, Button btnGameExit, Button btnGamePause, Label scoreLabel, Paddle paddle, Ball ball) throws IOException {
        this.gameArea = gameArea;
        this.btnGameExit = btnGameExit;
        this.btnGamePause = btnGamePause;
        this.scoreLabel = scoreLabel;
        this.paddle = paddle;
        this.ball = ball;
        breakoutConfig = new BreakoutConfig();
        brickList = new ArrayList<>();
        addBricksToList();
        setButtonStyle();
        gameInit();
    }
    //Create game's field model
    private void gameInit() {
    }
    //create 68 bricks : 17 x 4 = 68
    private void addBricksToList() throws IOException {

    }
    //Set button's style
    private void setButtonStyle() throws IOException {
        //Button Exit
        breakoutConfig.setButtonNormaleStyle(btnGameExit, "red_boxCross.png", 50, 50);
        //Button Pause
        breakoutConfig.setButtonNormaleStyle(btnGamePause, "yellow_boxTick.png", 50, 50);
    }

}
