package edu.chalmers.projecttemplate.model.breakoutmodel;

import java.util.ArrayList;
import java.util.List;
/*
 * Model for Breakout brick obstacle.
 * This class is responsible for keep different models of breakout brick obstacle
 * The class randomly creates and returns a brick obstacle.
 */
public class BrickObstacle {
    private List<Brick> brickList;
    private int x, y;
    private int height, width;
    public BrickObstacle() {
        x = 60;
        y = 50;
        height = 40;
        width = 60;
        brickList = new ArrayList<>();
        gameModelOne();
    }
    //1. Game model1: 12 bricks x 4 = 48bricks
    /* 4 4 4 4 4 4 4 4 4 4 4 4
       3 3 3 3 3 3 3 3 3 3 3 3
       2 2 2 2 2 2 2 2 2 2 2 2
       1 1 1 1 1 1 1 1 1 1 1 1
     */
    private void gameModelOne() {
        for (int i = 0; i<48; i++) {
            Brick brick = null;
            if (i<=11) {
                brick = new Brick(x, y, width, height, 4);
                if (i==11) {
                    y = y + 45;
                    x = -5;
                }
            } else if (i>=12 && i<=23) {
                brick = new Brick(x, y, width, height, 3);
                if (i==23) {
                    y = y + 45;
                    x = -5;
                }
            } else if (i>=24 && i<=35) {
                brick = new Brick(x, y, width, height, 2);
                if (i==35) {
                    y = y + 45;
                    x = -5;
                }
            } else {
                brick = new Brick(x, y, width, height, 1);
            }
            brickList.add(brick);
            x = x + 65;
        }
    }
    //return brick obstacle
    public List<Brick> getBrickList() {
        return brickList;
    }
}
