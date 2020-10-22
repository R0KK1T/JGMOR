package edu.chalmers.projecttemplate.model;

import edu.chalmers.projecttemplate.model.breakoutmodel.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BreakoutTest {
    private static Brick brick;
    private static Paddle paddle;
    private static Ball ball;
    private static Player player;
    private static BestScore score;

    @Before
    public void before() {

        brick = new Brick(10, 20, 30, 30, 4);
        paddle = new Paddle(20, 20, 40, 20);
        ball = new Ball(50, 50, 25, 25);
        player = new Player("Georges", "Kayembe");
        score = new BestScore();
    }
    @After
    public void after() {
        brick = new Brick(10, 20, 30, 30, 4);
        paddle = new Paddle(20, 20, 40, 20);
        ball = new Ball(50, 50, 25, 25);
        player = new Player("Georges", "Kayembe");
        score = new BestScore();
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
        assertTrue("The ball should now move in the opposite site: ",ball.getDx() == -1);
    }
    @Test
    public void reverseVerticalMomentummTest() {
        //Suppose the ball moves and collides with the bottom of wall
        ball.reverseVerticalMomentum();
        assertTrue("The ball should now move in the opposite site: ",ball.getDx() == 1);
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
        assertTrue("player first name is 'Georges' : ",player.getFirstName().equals("Georges"));
        player.setFirstName("David");
        assertTrue("player first name is now 'David' : ",player.getFirstName().equals("David"));
        assertTrue("player should initial have 0 point : ",player.getMyScore() == 0);
        player.setMyScore(50);
        assertTrue("player should now have 50 point : ",player.getMyScore() == 50);
    }
    /*
     * Test class score
     */
    @Test
    public void savedScoreTest() throws IOException {
        //Suppose that the first player has received 40 points.
        Player player = new Player("Gmiak", "Master");
        player.setMyScore(40);
        assertTrue("player should have 40 point : ",player.getMyScore() == 40);
        //Let's create two more players. Let's give them 70 and 60 points respectively.
        Player player1 = new Player("David", "Helias");
        player1.setMyScore(70);
        assertTrue("player1 should have 70 point : ",player1.getMyScore() == 70);

        Player player2 = new Player("Elias", "Kampbell");
        player2.setMyScore(60);
        assertTrue("player2 should have 60 point : ",player2.getMyScore() == 60);

        //Now let's save the score and check the file bestPlayer.txt
        score.cleanFile();
        score.readAndSaveScore(player);
        score.readAndSaveScore(player1);
        score.readAndSaveScore(player2);

        assertTrue("The player with the highest score should be 'David' : ",score.getBestPlayers().get(0).getFirstName().equals("David"));
        assertTrue("David has : ",score.getBestPlayers().get(0).getMyScore() == 70);

        assertTrue("The player with lowest score should be 'Gmiak' : ",score.getBestPlayers().get(2).getFirstName().equals("Gmiak"));
        assertTrue("Gmiak has : ",score.getBestPlayers().get(2).getMyScore() == 40);

    }


}
