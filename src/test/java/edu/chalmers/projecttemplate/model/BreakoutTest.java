package edu.chalmers.projecttemplate.model;

import edu.chalmers.projecttemplate.model.breakoutmodel.Ball;
import edu.chalmers.projecttemplate.model.breakoutmodel.Brick;
import edu.chalmers.projecttemplate.model.breakoutmodel.Paddle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BreakoutTest {
    private static Brick brick;
    private static Paddle paddle;
    private static Ball ball;

    @Before
    public void before() {

        brick = new Brick(10, 20, 30, 30, 4);
        paddle = new Paddle(20, 20, 40, 20);
        ball = new Ball(50, 50, 25, 25);
    }
    @After
    public void after() {
        brick = new Brick(10, 20, 30, 30, 4);
        paddle = new Paddle(20, 20, 40, 20);
    }
    /*
     * Test class Brick
     */
    @Test
    public void getBrickPointTest() {
        assertTrue("The brick should hold 4 points: ",brick.getBrickPoint() == 4);
    }
    @Test
    public void getBrickHitTest() {
        assertTrue("The brick should contain 4 hits: ",brick.getBrickHit() == 4);
    }
    @Test
    public void getBrickStatusTest() {
        assertEquals("isActive should be true: ", brick.getBrickStatus(), true);
    }
    @Test
    public void setBrickHitTest() {
        brick.setBrickHit();
        assertTrue("The brick should have 3 hits left: ",brick.getBrickHit() == 3);
        assertEquals("isActive should be true: ", brick.getBrickStatus(), true);
    }
    @Test
    public void setBrickStatusTest() {
        // Normal state
        assertTrue("The brick should contain 4 hits: ",brick.getBrickHit() == 4);
        // First hit
        brick.setBrickHit();
        brick.setBrickStatus();
        assertEquals("isActive should be true: ", brick.getBrickStatus(), true);


        assertTrue("The brick should have 3 hits left: ",brick.getBrickHit() == 3);
        // Second hit
        brick.setBrickHit();
        brick.setBrickStatus();
        assertEquals("isActive should be true: ", brick.getBrickStatus(), true);


        assertTrue("The brick should have 2 hits left: ",brick.getBrickHit() == 2);
        // Third hit
        brick.setBrickHit();
        brick.setBrickStatus();
        assertEquals("isActive should be true: ", brick.getBrickStatus(), true);


        assertTrue("The brick should have 1 hit left: ",brick.getBrickHit() == 1);
        // Fourth hit
        brick.setBrickHit();
        brick.setBrickStatus();
        assertEquals("isActive should now be false: ", brick.getBrickStatus(), false);
    }
    /*
     * Test class Paddle
     */
    @Test
    public void moveTest() {
        //paddle initial x-position = 20

        //User pressing right arrow one time
        paddle.incX(1);
        paddle.move();
        assertTrue("The paddle would have moved one step to the right: ",paddle.getX() == 21);

        //User pressing left arrow twice
        paddle.incX(-1);
        paddle.move();
        paddle.incX(-1);
        paddle.move();
        assertTrue("The paddle would have moved two steps to the left: ",paddle.getX() == 19);

    }
    /*
     * Test class Ball
     */
    @Test
    public void reverseHorizontalMomentumTest() {
        //Suppose the ball moves and collides with the top of wall
        ball.reverseHorizontalMomentum();
        assertTrue("The ball would now move in the opposite site: ",ball.getDx() == -1);
    }
    @Test
    public void reverseVerticalMomentummTest() {
        //Suppose the ball moves and collides with the bottom of wall
        ball.reverseVerticalMomentum();
        assertTrue("The ball would now move in the opposite site: ",ball.getDx() == 1);
    }
    /*
     * Test on collision
     */
    @Test
    public void collisionTest() {
        assertEquals("Check if the ball collides with the paddle: ", ball.intersect(paddle), false);
        assertEquals("Check if the ball collides with the brick: ", ball.intersect(brick), false);
        paddle.setX(50);
        paddle.setY(50);
        assertEquals("The ball should collide with the paddle: ", ball.intersect(paddle), true);
    }
}
