package edu.chalmers.projecttemplate.model.breakoutmodel;

import edu.chalmers.projecttemplate.model.common.IPositionableInt;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
/*
 * Model for breakout game - The class randomly creates and returns a game model.
 */
public class GameModel {
    private Paddle paddle;
    private Ball ball;
    private Player player;
    private int windowSizeX;
    private int windowSizeY;
    public GameModel() {
        init();
    }
    /*
     * Initialize game object
     */
    private void init() {
        windowSizeX = 900;
        windowSizeY = 550;
        paddle = new Paddle(420, 537, 100, 12);
        ball = new Ball(442, 510, 25, 25);
        player = new Player("Player1", "Player1");
    }
    /*
     * Movable object
     */
    public void tick() {
        paddle.move();
        checkCollisionPaddleWall();

        ball.move();
        checkCollisionBallWall();

        checkCollisionBallPaddle();

    }
    /*
     * Set paddle direction on KeyListeners
     */
    public void setPaddleMoveRight() {
        paddle.moveRight();
    }
    public void setPaddleMoveLeft() {
        paddle.moveLeft();
    }
    public void setPaddleStill() {
        paddle.setVelocity(0);
    }
    /*
     * Check if the paddle is colliding with the wall
     */
    public void checkCollisionPaddleWall() {
        if (paddle.getX() <= 0)
            paddle.initX(0);
        if (paddle.getX() >= windowSizeX - paddle.getWidth())
            paddle.initX(windowSizeX - paddle.getWidth());
    }
    /*
     * Check if the ball is colliding with the wall
     */
    public void checkCollisionBallWall() {
        if (ball.getX() < 0 || (ball.getX()+ball.getWidth() > windowSizeX))
            ball.reverseHorizontalMomentum();
        if (ball.getY() < 0)
            ball.reverseVerticalMomentum();
    }
    /*
     * Checks if the ball is colliding with the paddle.
     */
    public void checkCollisionBallPaddle() {
        if (ball.getHitbox().intersect(paddle.getHitbox())) {
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
    /*public void checkCollisionBallBrick() {
        for (int i=0; i<brickList.size(); i++) {
            checkCollisionBallBrick(brickList.get(i));
        }
    }*/
    /*
     * Method for checking collision between a Brick and the ball.
     */
    /*private void checkCollisionBallBrick(Brick brick) {
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
    }*/
    /*
     * Check for game over
     */
    /*public boolean gameIsOver() {
        return ball.getY()>(ball.getWindowSizeY()-26);
    }*/

    /*
     * modeling game field
     */


    //get paddle
    /*public Paddle getPaddle() {
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
    /*public List<Brick> getBrickList() {
        return brickList;
    }*/
    public List<IPositionableInt> getRepresents() {
        List<IPositionableInt> gameObjects = new ArrayList<>();
        //add paddle
        gameObjects.add(paddle);
        //add ball
        gameObjects.add(ball);
        return gameObjects;
    }
}
