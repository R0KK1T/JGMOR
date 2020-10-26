package edu.chalmers.projecttemplate.model.breakoutmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
        randomModel();
        //gameModelThree();
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
    //2. Game model2: 10 bricks x 4 = 40bricks
    /* 4 4 4 4 4   4 4 4 4 4
       3 3 3 3 3   3 3 3 3 3
       2 2 2 2 2   2 2 2 2 2
       1 1 1 1 1   1 1 1 1 1
     */
    private void gameModelTwo() {
        for (int i = 0; i<40; i++) {
            Brick brick = null;
            if (i<=9) {
                brick = new Brick(x, y, width, height, 4);
                if (i==4)
                    x = x + 130;
                if (i==9) {
                    y = y + 45;
                    x = -5;
                }
            } else if (i>=10 && i<=19) {
                brick = new Brick(x, y, width, height, 3);
                if (i==14)
                    x = x + 130;
                if (i==19) {
                    y = y + 45;
                    x = -5;
                }
            } else if (i>=20 && i<=29) {
                brick = new Brick(x, y, width, height, 2);
                if (i==24)
                    x = x + 130;
                if (i==29) {
                    y = y + 45;
                    x = -5;
                }
            } else {
                if (i == 35)
                    x = x + 130;
                brick = new Brick(x, y, width, height, 1);
            }
            brickList.add(brick);
            x = x + 65;
        }
    }
    //3. Game model3: 10 bricks x 4 = 36 bricks
    /* 4 4 4 4 4 4 4 4 4 4 4 4
         3 3 3 3 3 3 3 3 3 3
           2 2 2 2 2 2 2 2
             1 1 1 1 1 1
     */
    private void gameModelThree() {
        for (int i = 0; i<36; i++) {
            Brick brick = null;
            if (i<=11) {
                brick = new Brick(x, y, width, height, 4);
                if (i==11) {
                    y = y + 45;
                    x = 65;
                }
            } else if (i>=12 && i<=21) {
                brick = new Brick(x, y, width, height, 3);
                if (i==21) {
                    y = y + 45;
                    x = 130;
                }
            } else if (i>=22 && i<=29) {
                brick = new Brick(x, y, width, height, 2);
                if (i==29) {
                    y = y + 45;
                    x = 195;
                }
            } else {
                brick = new Brick(x, y, width, height, 1);
            }
            brickList.add(brick);
            x = x + 65;
        }
    }
    //Generate random brick obstacle
    private void randomModel() {
        int min = 1, max = 2;
        Random random = new Random();
        int model = random.nextInt((max - min) + 1) +min;
        switch (model) {
            case 1:
                gameModelOne();
                break;
            case 2:
                gameModelTwo();
                break;
            case 3:
                gameModelThree();
                break;
            default:
                gameModelOne();
        }
    }
    //return brick obstacle
    public List<Brick> getBrickList() {
        return brickList;
    }
}
