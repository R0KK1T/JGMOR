package edu.chalmers.projecttemplate.model;

import edu.chalmers.projecttemplate.model.snake22model.GameConfiguration;
import edu.chalmers.projecttemplate.model.snake22model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Snake22Test {
    static int snakePartSize;
    static int boardWidth;
    static int boardHeight;
    static int gameSpeed;
    static int startPointX;
    static int startPointY;
    static Direction startDirection;
    static  int startingSnakeLength;
    static boolean gameOverOnWallCollision;


    static GameConfiguration gameConfiguration;
    List<SnakePart> snakeParts;

    int gameScore;

    @BeforeClass
    public static void setup(){
        snakePartSize = 10;
        boardWidth = 640;
        boardHeight = 640;
        gameSpeed = 80;
        startPointX = boardWidth/2;
        startPointY = boardHeight/2;
        startDirection = Direction.RIGHT;
        startingSnakeLength = 5;
        gameOverOnWallCollision = false;
        gameConfiguration= new GameConfiguration();
    }

    @Before
    public void init() {
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
    public void creationOfSnakeWithGameConfig_InitiallyCreateSnakeParts(){
        Snake snake =  new Snake(gameConfiguration);
        List<SnakePart>  snakeParts = snake.getSnakeParts();
        Assert.assertEquals(snakeParts.size(), 6);
    }

    @Test
    public void creationOfSnakeWithGameConfig_InitiallyCreateSnakeHead(){
        Snake  snake =  new Snake(gameConfiguration);
        SnakePart  snakeHead = snake.getSnakeHead();
        Assert.assertEquals(snakeHead.getSnakePartPositionX(), 320);
        Assert.assertEquals(snakeHead.getSnakePartPositionY(), 320);
        Assert.assertEquals(snakeHead.getCurrentDirection(), Direction.RIGHT);
    }

    @Test
    public void addingSnakePartToTail_ShouldIncreasePartSizeByOne(){
        Snake  snake =  new Snake(gameConfiguration);
        int initialSnakePartsCount = snake.getSnakeParts().size();
        Assert.assertEquals(initialSnakePartsCount, 6); //Checking the initial part count first
        snake.addSnakePartToTail();
        List<SnakePart>  newSnakeParts = snake.getSnakeParts();
        Assert.assertEquals(newSnakeParts.size(), 7);
        SnakePart lastAddedSnakePart = newSnakeParts.get(6);
        Assert.assertEquals(lastAddedSnakePart.getSnakePartPositionX(), 320);
        Assert.assertEquals(lastAddedSnakePart.getSnakePartPositionY(), 320);
        Assert.assertEquals(lastAddedSnakePart.getCurrentDirection(), Direction.RIGHT);
    }

    @Test(expected = NullPointerException.class)
    public void creationOfSnakeWithNullGameConfig_ResultsNullPointerException(){
        GameConfiguration gc =null;
        Snake  snake =  new Snake(gc);
        List<SnakePart>  snakeParts = snake.getSnakeParts();
        Assert.assertEquals(snakeParts.size(), 6);
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
    public void forInputValues_DetectWallCollisionResultFalse(){
        Snake  snake =  new Snake(gameConfiguration);
        Food food = new Food(gameConfiguration, snakeParts);
        CollisionDetectionModule collisionDetectionModule = new CollisionDetectionModule(snake,
                food, gameConfiguration);
        Assert.assertFalse(collisionDetectionModule.detectWallCollision());
    }

    @Test
    public void forGivenInputValues_goFromOtherSideOnWallCollisionShouldNotChangeHeadPositions(){
        Snake  snake =  new Snake(gameConfiguration);
        Food food = new Food(gameConfiguration, snakeParts);
        CollisionDetectionModule collisionDetectionModule = new CollisionDetectionModule(snake,
                food, gameConfiguration);
        collisionDetectionModule.goFromOtherSideOnWallCollision();
        Assert.assertEquals(320, snake.getSnakeHead().getSnakePartPositionX());
        Assert.assertEquals(320, snake.getSnakeHead().getSnakePartPositionY());
    }
}
