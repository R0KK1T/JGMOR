package edu.chalmers.projecttemplate.view.snake22view;

import edu.chalmers.projecttemplate.model.snake22model.GameConfiguration;
import edu.chalmers.projecttemplate.model.snake22model.Score;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawModule {
    static final Color BACKGROUND_COLOR = new Color(0, 0, 0,1);
    static final Color SNAKE_COLOR = new Color(0,1,0,1);
    static final Color FOOD_COLOR = new Color(1.0,0.0,0.0,1.0);
    static final Color SCORE_COLOR = new Color(1, 1, 1,1);

    private GraphicsContext graphicsContext;
    private GameConfiguration gc;

    public DrawModule(GraphicsContext graphicsContext, GameConfiguration gameConfiguration) {
        this.graphicsContext = graphicsContext;
        this.gc = gameConfiguration;
    }

    public void drawBackGround(){
        graphicsContext.setFill(BACKGROUND_COLOR);
        graphicsContext.fillRect(0,0,gc.getBoardWidth(),gc.getBoardHeight());
    }

    public void drawSnakePart(int headPosX, int headPosY){
        graphicsContext.setFill(SNAKE_COLOR);
        graphicsContext.fillRect(headPosX, headPosY, gc.getSnakePartSize(), gc.getSnakePartSize());
    }

    public void drawFood(int foodPosX, int foodPosY){
        graphicsContext.setFill(FOOD_COLOR);
        graphicsContext.fillRect(foodPosX, foodPosY, gc.getSnakePartSize(), gc.getSnakePartSize());
    }

    public void drawScore(Score gameScore){
        graphicsContext.setFill(SCORE_COLOR);
        graphicsContext.fillText("score: " + gameScore.getGameScore(), 20,20);
    }
}
