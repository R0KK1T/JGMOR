package edu.chalmers.projecttemplate.model.breakoutmodel;

import java.util.ArrayList;
import java.util.List;
/*
 * Model for breakout game - The class randomly creates and returns a game model.
 */
public class GameModel {
    private Paddle paddle;
    private Ball ball;
    private List<Brick> brickList;
    private int x, y;
    private int height, width;
    private Player player;
    public GameModel() {
        brickList = new ArrayList<>();
        x = 60;
        y = 50;
        height = 40;
        width= 60;
        init();
        gameModelOne();
    }
    /*
     * Initialize game object
     */
    private void init() {
        paddle = new Paddle(420, 537, 100, 12);
        ball = new Ball(442, 510, 25, 25);
        player = new Player("Player1", "Player1");
    }
    /*
     * Movable object
     */
    public void tick() {
        paddle.move();
        ball.move();
    }
    /*
     * Checks if the ball is colliding with the paddle.
     */
    public void checkCollisionBallPaddle() {
        if (ball.intersect(paddle)) {
            if (ball.getY() < paddle.getY()) {
                ball.reverseVerticalMomentum();
                /*
                 * Zone One is the leftmost part of the paddle, zone two
                 * is the middle left part of the paddle, zone three middle right
                 * and lastly the rightmost part of the paddle
                 */
                int zoneWidth = paddle.getWidth() / 4;
                int zoneOne = paddle.getX() + zoneWidth;
                int zoneTwo = zoneOne + zoneWidth;
                int zoneThree = zoneTwo + zoneWidth;
                if (ball.getX() < zoneOne)
                    ball.setDx(-1);
                else if (ball.getX() < zoneTwo)
                    ball.setDx(-1);
                else if (ball.getX() < zoneThree)
                    ball.setDx(1);
                else
                    ball.setDx(1);
            } else {
                ball.reverseHorizontalMomentum();
            }
        }

    }
    /*
     * Loops through the array of bricks and checks if any of the bricks
     * collides with the ball.
     */
    public void checkCollisionBallBrick() {
        for (int i=0; i<brickList.size(); i++) {
            checkCollisionBallBrick(brickList.get(i));
        }
    }
    /*
     * Method for checking collision between a Brick and the ball.
     */
    private void checkCollisionBallBrick(Brick brick) {
        if (!brick.getBrickStatus()) {
            return;
        }
        if (ball.intersect(brick)) {
            //Hit was from below the brick
            if (ball.getY() <= brick.getY() - ((brick.getHeight())/2)) {
                ball.reverseVerticalMomentum();
            }
            //Hit was from above the brick
            if (ball.getY() >= brick.getY() - ((brick.getHeight())/2)) {
                ball.reverseVerticalMomentum();
            }
            //Hit was on left
            if (ball.getX() < brick.getX()) {
                ball.reverseHorizontalMomentum();
            }
            //Hit was on right
            if (ball.getX() > brick.getX()) {
                ball.reverseHorizontalMomentum();
            }
            brick.setBrickHit();
            brick.setBrickStatus();
            player.setMyScore(1);
        }
    }
    /*
     * Check for game over
     */
    public boolean gameIsOver() {
        return ball.getY()>(ball.getWindowSizeY()-26);
    }

    /*
     * modeling game field
     */

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
    //get paddle
    public Paddle getPaddle() {
        return paddle;
    }
    //get ball
    public Ball getBall() {
        return ball;
    }
    //get player
    public Player getPlayer() {
        return player;
    }
    //get brick list
    public List<Brick> getBrickList() {
        return brickList;
    }
}
