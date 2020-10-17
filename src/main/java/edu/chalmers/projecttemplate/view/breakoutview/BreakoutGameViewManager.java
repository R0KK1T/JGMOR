package edu.chalmers.projecttemplate.view.breakoutview;

import edu.chalmers.projecttemplate.model.breakoutmodel.Ball;
import edu.chalmers.projecttemplate.model.breakoutmodel.Brick;
import edu.chalmers.projecttemplate.model.breakoutmodel.Paddle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
/*
 * BreakoutGameViewManager is responsible for drawing game images and other things that have to do with View.
 */
public class BreakoutGameViewManager {
    private GraphicsContext gc;
    private double width;
    private double height;
    public BreakoutGameViewManager(GraphicsContext gc, double width, double height) {
        this.gc = gc;
        this.width = width;
        this.height = height;
    }
    /*
     * Drawing the game area
     */
    public void drawGameArea() {
        gc.clearRect(0, 0, width, height);
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width, height);
    }
    /*
     * Drawing the paddle
     */
    public void drawPaddle(Paddle paddle) {
        gc.setFill(Color.CYAN);
        gc.fillRoundRect(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight(), 25, 25);
    }
    /*
     * Drawing the ball
     */
    public void drawBall(Ball ball) {
        gc.setFill(Color.WHITE);
        gc.fillOval(ball.getX(), ball.getY() , ball.getHeight(), ball.getHeight());
    }
    /*
     * Drawing Brick
     */
    public void drawBrick(Brick brick) {
        if (brick.getBrickHit()==4) {
            gc.setFill(Color.RED);
            gc.fillRect(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
        }
        else if (brick.getBrickHit()==3) {
            gc.setFill(Color.ORANGE);
            gc.fillRect(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
        }
        else if (brick.getBrickHit()==2) {
            gc.setFill(Color.GREEN);
            gc.fillRect(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
        }
        else {
            gc.setFill(Color.YELLOW);
            gc.fillRect(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
        }
    }


}
