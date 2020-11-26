package edu.chalmers.projecttemplate.model;

import edu.chalmers.projecttemplate.model.snake22model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Snake22Test {

    static GameConfiguration gameConfiguration;
    List<SnakePart> snakeParts;
    static int snakePartSize;
    static int boardWidth;
    static int boardHeight;
    static int gameSpeed;
    static int startPointX;
    static int startPointY;
    static Direction startDirection;
    static  int startingSnakeLength;
    static boolean gameOverOnWallCollision;

    private CollisionDetectionModule collisionDetectionModule;

    int gameScore;

    @BeforeClass
    public static void setup(){
        gameConfiguration= new GameConfiguration();
        snakePartSize = 10;
        boardWidth = 640;
        boardHeight = 640;
        gameSpeed = 80;
        startPointX = boardWidth/2;
        startPointY = boardHeight/2;
        startDirection = Direction.RIGHT;
        startingSnakeLength = 5;
        gameOverOnWallCollision = false;
    }

    @Before
    public void setUp() {
        gameScore= 5;
        snakeParts = new ArrayList<>();
        SnakePart snakePart1 =  new SnakePart(0, 10,
                Direction.RIGHT);
        SnakePart snakePart2 =  new SnakePart(5, 7,
                Direction.LEFT);
        SnakePart snakePart3 =  new SnakePart(6, 8,
                Direction.RIGHT);
        snakeParts.add(snakePart1);
        snakeParts.add(snakePart2);
        snakeParts.add(snakePart3);
        collisionDetectionModule = new CollisionDetectionModule(gameConfiguration);
    }

    @Test
    public void detectWallCollision_ResultFalse(){
        Snake snake = new Snake(gameConfiguration);

        assertFalse(collisionDetectionModule.detectWallCollision(snake));
    }

    @Test
    public void detectWallCollision_ResultTrue(){
        Snake snake = new Snake(gameConfiguration);
        snake.getSnakeHead().setSnakePartPositionX(-1);

        assertTrue(collisionDetectionModule.detectWallCollision(snake));
    }

    @Test
    public void detectOwnCollision_ResultFalse() {
        Snake snake = new Snake(gameConfiguration);
        SnakePart head = snake.getSnakeHead();
        snake.getSnakeParts().clear();
        snake.getSnakeParts().add(head);

        assertFalse(collisionDetectionModule.detectOwnCollision(snake));
    }

    @Test
    public void detectOwnCollision_ResultTrue() {
        Snake snake = new Snake(gameConfiguration);
        snake.getSnakeParts().get(2).setSnakePartPositionX(snake.getSnakeHead().getSnakePartPositionX());
        snake.getSnakeParts().get(2).setSnakePartPositionY(snake.getSnakeHead().getSnakePartPositionY());

        assertTrue(collisionDetectionModule.detectOwnCollision(snake));
    }

    @Test
    public void forGivenInputValues_goFromOtherSideOnWallCollisionShouldNotChangeHeadPositions(){
        Snake snake = new Snake(gameConfiguration);

        collisionDetectionModule.goFromOtherSideOnWallCollision(snake);

        assertEquals(320, snake.getSnakeHead().getSnakePartPositionX());
        assertEquals(320, snake.getSnakeHead().getSnakePartPositionY());
    }

    @Test
    public void createFood_ShouldRandomizePositions(){
        Food food1 = new Food(gameConfiguration, snakeParts);
        int randomX1 = food1.getFoodPositionX();
        int randomY1 = food1.getFoodPositionY();

        Food  food2 = new Food(gameConfiguration, snakeParts);
        int randomX2 = food2.getFoodPositionX();
        int randomY2 = food2.getFoodPositionY();

        Food  food3 = new Food(gameConfiguration, snakeParts);
        int randomX3 = food3.getFoodPositionX();
        int randomY3 = food3.getFoodPositionY();

        Assert.assertFalse(randomX1 == randomX2 &&
                randomX2 == randomX3);
        Assert.assertFalse(randomY1 == randomY3 &&
                randomY2 == randomY3);
    }

    @Test
    public void loadConfiguration_successfully(){
        Assert.assertEquals(snakePartSize,new GameConfiguration().getSnakePartSize());
        Assert.assertEquals(boardWidth,new GameConfiguration().getBoardWidth());
        Assert.assertEquals(boardHeight,new GameConfiguration().getBoardHeight());
        Assert.assertEquals(gameSpeed,new GameConfiguration().getGameSpeed());
        Assert.assertEquals(startPointX,new GameConfiguration().getStartPointX());
        Assert.assertEquals(startPointY,new GameConfiguration().getStartPointY());
        Assert.assertEquals(startDirection,new GameConfiguration().getStartDirection());
        Assert.assertEquals(startingSnakeLength,new GameConfiguration().getStartingSnakeLength());
        Assert.assertFalse(gameOverOnWallCollision);
    }

    @Test
    public void withGameScoreAndGameConfig_InitiallyCreateScoreObject(){
        Score score = new Score(gameScore, gameConfiguration);
        Assert.assertEquals(5, score.getGameScore());
    }

    @Test
    public void increaseGameScore_ShouldIncreaseGameScoreBy10(){
        int expectedScore = gameScore + 10 ;
        Score  score = new Score(gameScore, gameConfiguration);
        score.increaseScore();
        Assert.assertEquals(expectedScore, score.getGameScore());
    }

    @Test
    public void increaseGameScoreWithDefaultConstructor_ShouldIncreaseGameScoreBy10(){
        int expectedScore =  10 ;
        Score  score = new Score();
        score.increaseScore();
        Assert.assertEquals(expectedScore, score.getGameScore());
    }

    @Test
    public void addingSnakePartToTail_ShouldIncreasePartSizeByOne(){
        Snake snake = new Snake(gameConfiguration);
        int initialSnakePartsCount = snake.getSnakeParts().size();
        snake.addSnakePartToTail();
        List<SnakePart> newSnakeParts = snake.getSnakeParts();
        SnakePart lastAddedSnakePart = newSnakeParts.get(6);

        assertEquals(initialSnakePartsCount, 6); //Checking the initial part count first
        assertEquals(newSnakeParts.size(), 7);
        assertEquals(lastAddedSnakePart.getSnakePartPositionX(), 320);
        assertEquals(lastAddedSnakePart.getSnakePartPositionY(), 320);
        assertEquals(lastAddedSnakePart.getCurrentDirection(), Direction.RIGHT);
    }

    @Test
    public void creationOfSnakeWithGameConfig_InitiallyCreateSnakeParts(){
        Snake snake =  new Snake(gameConfiguration);
        List<SnakePart>  snakeParts = snake.getSnakeParts();
        assertEquals(snakeParts.size(), 6);
    }

    @Test
    public void creationOfSnakeWithGameConfig_InitiallyCreateSnakeHead(){
        Snake  snake =  new Snake(gameConfiguration);
        SnakePart  snakeHead = snake.getSnakeHead();
        assertEquals(snakeHead.getSnakePartPositionX(), 320);
        assertEquals(snakeHead.getSnakePartPositionY(), 320);
        assertEquals(snakeHead.getCurrentDirection(), Direction.RIGHT);
    }

    @Test(expected = NullPointerException.class)
    public void creationOfSnakeWithNullGameConfig_ResultsNullPointerException(){
        GameConfiguration gc =null;
        Snake snake = new Snake(gc);
        List<SnakePart> snakeParts = snake.getSnakeParts();
        assertEquals(snakeParts.size(), 6);
    }

    @Test
    public void addSnakePartToTail() {
        Snake snake = new Snake(gameConfiguration);
        int length = snake.getSnakeParts().size();

        snake.addSnakePartToTail();

        assertEquals(length + 1, snake.getSnakeParts().size());
    }
}
