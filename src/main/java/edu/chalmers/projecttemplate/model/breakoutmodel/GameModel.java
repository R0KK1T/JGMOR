package edu.chalmers.projecttemplate.model.breakoutmodel;

import edu.chalmers.projecttemplate.model.common.IPositionableInt;
import java.util.ArrayList;
import java.util.List;
/*
 * Model for breakout game.
 */
public class GameModel {
    private Paddle paddle;
    private Ball ball;
    private Player player;
    private BrickObstacle brickObstacle;
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
        brickObstacle = new BrickObstacle();
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

        checkCollisionBallBrick();

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
        paddle.setDx(0);
    }
    /*
     * Check if the paddle is colliding with the wall
     */
    private void checkCollisionPaddleWall() {
        if (paddle.getX() <= 0)
            paddle.initX(0);
        if (paddle.getX() >= windowSizeX - paddle.getWidth())
            paddle.initX(windowSizeX - paddle.getWidth());
    }
    /*
     * Check if the ball is colliding with the wall
     */
    private void checkCollisionBallWall() {
        if (ball.getX() < 0 || (ball.getX()+ball.getWidth() > windowSizeX))
            ball.reverseHorizontalMomentum();
        if (ball.getY() < 0)
            ball.reverseVerticalMomentum();
    }
    /*
     * Checks if the ball is colliding with the paddle.
     */
    private void checkCollisionBallPaddle() {
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
    private void checkCollisionBallBrick() {
        for (int i=0; i<brickObstacle.getBrickList().size(); i++) {
            checkCollisionBallBrick(brickObstacle.getBrickList().get(i));
        }
    }
    /*
     * Method for checking collision between a Brick and the ball.
     */
    private void checkCollisionBallBrick(Brick brick) {
        if (!brick.getBrickStatus()) {
            return;
        }

        int zoneWidth = brick.getWidth() / 2;
        int zoneOne = (brick.getY() + brick.getHeight())  + zoneWidth;
        int zoneTwo = brick.getX() + zoneWidth;

        int zoneHeight = brick.getHeight() / 2;
        int zoneThree = brick.getY() + zoneHeight;
        int zoneFour = (brick.getX() + zoneWidth) + zoneHeight;

        if (ball.getHitbox().intersect(brick.getHitbox())) {
            //Hit was from bottom the brick
            if (ball.getY() >= brick.getY() - ((brick.getHeight())/2)) {
                ball.reverseVerticalMomentum();
                if (ball.getX() < zoneOne)
                    ball.setDx(1);
                else
                    ball.setDx(-1);
            }
            //Hit was from above the brick
            if (ball.getY() <= brick.getY() - ((brick.getHeight())/2)) {
                ball.reverseVerticalMomentum();
                if (ball.getX() < zoneTwo) {
                    ball.setDx(-1);
                }
                else {
                    ball.setDx(1);
                }
            }
            //Hit was on left
            if (ball.getX() < brick.getX()) {
                ball.reverseHorizontalMomentum();
                if (ball.getY() >= zoneThree)
                    ball.setDy(1);
                else
                    ball.setDy(-1);
            }
            //Hit was on right
            if (ball.getX() > brick.getX()) {
                ball.reverseHorizontalMomentum();
                if (ball.getY() >= zoneFour)
                    ball.setDy(-1);
                else
                    ball.setDy(1);
            }

            brick.setBrickHit();
            brick.setBrickStatus();
            player.setMyScore(1);
        }
    }
    /*
     * We divide the length where the ball hits the piece into two parts so that
     * we specifically know in which direction the ball should then be moved to.
     */

    /*
     * Check if the game is over
     */
    public boolean gameIsOver() {
        return ball.getY()>(windowSizeY-26);
    }
    /*
     * Check if the player wins the game
     */
    public boolean youWin() {
        for (int i=0; i<brickObstacle.getBrickList().size(); i++){
            if (brickObstacle.getBrickList().get(i).getBrickStatus())
                return false;
        }
        return true;
    }

    /*
     * Return game movable object
     */
    public List<IPositionableInt> getMovableObject() {
        List<IPositionableInt> gameObjects = new ArrayList<>();
        //add paddle
        gameObjects.add(paddle);
        //add ball
        gameObjects.add(ball);
        return gameObjects;
    }
    /*
     * Return game static object
     */
    public List<Brick> getStaticObject() {
        List<Brick> gameObjects = new ArrayList<>();
        //add bricks
        for (int i=0; i<brickObstacle.getBrickList().size(); i++)
            gameObjects.add(brickObstacle.getBrickList().get(i));
        return gameObjects;
    }
    /*
     * Get player
     */
    public Player getPlayer() {
        return player;
    }
}
