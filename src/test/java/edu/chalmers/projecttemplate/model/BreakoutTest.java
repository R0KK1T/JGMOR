package edu.chalmers.projecttemplate.model;

import edu.chalmers.projecttemplate.model.breakoutmodel.Ball;
import edu.chalmers.projecttemplate.model.breakoutmodel.Brick;
import edu.chalmers.projecttemplate.model.breakoutmodel.Paddle;
import edu.chalmers.projecttemplate.model.breakoutmodel.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BreakoutTest {
    private static Brick brick;
    private static Paddle paddle;
    private static Ball ball;
    private static Player player;

    @Before
    public void before() {

        brick = new Brick(10, 20, 30, 30, 4);
        paddle = new Paddle(20, 20, 40, 20);
        ball = new Ball(50, 50, 25, 25);
        player = new Player("Georges", "Kayembe");
    }
    @After
    public void after() {
        brick = new Brick(10, 20, 30, 30, 4);
        paddle = new Paddle(20, 20, 40, 20);
        ball = new Ball(50, 50, 25, 25);
        player = new Player("Georges", "Kayembe");
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
        //paddle initial x-position = 20. The paddle moves with a velocity = 3

        //User pressing key 'W' one time
         paddle.moveRight();
         //paddle.move();
        assertTrue("The paddle should have moved three steps to the right: ",paddle.getX() == 20);

        //User pressing key 'Q' twice
         paddle.moveLeft();
         paddle.moveLeft();
         //paddle.move()
        assertTrue("The paddle should have moved six steps to the left: ",paddle.getX() == 20);

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
        assertEquals("Check if the ball collides with the paddle: ", ball.getHitbox().intersect(paddle.getHitbox()), false);
        assertEquals("Check if the ball collides with the brick: ", ball.getHitbox().intersect(brick.getHitbox()), false);
        paddle.setX(50);
        paddle.setY(50);
        assertEquals("The ball should collide with the paddle: ", ball.getHitbox().intersect(paddle.getHitbox()), true);
    }
    /*
     * Test class Player
     */
    @Test
    public void playerTest() {
        assertTrue("The player's frist name is 'Georges' : ",player.getFirstName().equals("Georges"));
        player.setFirstName("David");
        assertTrue("The player's first name is now 'David' : ",player.getFirstName().equals("David"));
        assertTrue("The play should initial have 0 point : ",player.getMyScore() == 0);
        player.setMyScore(50);
        assertTrue("The play should have 50 point : ",player.getMyScore() == 50);
    }
    /*
     * Test class score
     */


}
