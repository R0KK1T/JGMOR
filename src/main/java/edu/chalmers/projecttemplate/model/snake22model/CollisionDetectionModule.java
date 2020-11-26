package edu.chalmers.projecttemplate.model.snake22model;

/**
 * Collision detection.
 */
public class CollisionDetectionModule {

    private final GameConfiguration gc;

    public CollisionDetectionModule(GameConfiguration gameConfiguration) {
        this.gc = gameConfiguration;
    }

    /**
     * Detects wall collision
     * @return is collided with a wall
     * @param snake snake
     */
    public boolean detectWallCollision(Snake snake){
        if (snake.getSnakeHead().getSnakePartPositionX() < 0 || snake.getSnakeHead().getSnakePartPositionX() >= gc.getBoardWidth() ||
                snake.getSnakeHead().getSnakePartPositionY() < 0 || snake.getSnakeHead().getSnakePartPositionY() >= gc.getBoardHeight()){
            System.out.println("Wall collision!!!");
            return true;
        }
        return false;
    }

    /**
     * Detects own snake collision
     * @return is snake bit itself
     * @param snake snake
     */
    public boolean detectOwnCollision(Snake snake){
        for (int i = 1; i < snake.getSnakeParts().size(); i++) {
            if (snake.getSnakeParts().get(i).getSnakePartPositionX() == snake.getSnakeHead().getSnakePartPositionX()
                    && snake.getSnakeParts().get(i).getSnakePartPositionY() == snake.getSnakeHead().getSnakePartPositionY()){
                System.out.println("DAMN!! I've bit my tail!");
                return true;
            }
        }
        return false;
    }

    /**
     * Detects food collision. In case of detection adds snake part,
     * generates new food position and increases game score.
     *
     * @param snake snake
     * @param gameScore - current game score
     * @param food - food element
     */
    public void detectFoodCollision(Snake snake, Score gameScore, Food food){
        if (snake.getSnakeHead().getSnakePartPositionX() == food.getFoodPositionX()
                && snake.getSnakeHead().getSnakePartPositionY() == food.getFoodPositionY()){
            System.out.println("Om nom nom!");
            snake.addSnakePartToTail();
            gameScore.increaseScore();
            food.generatePosition();
        }
    }

    /**
     * Go from other side of wall collision
     * @param snake snake
     */
    public  void goFromOtherSideOnWallCollision(Snake snake){
        if (snake.getSnakeHead().getSnakePartPositionX() < 0){
            snake.getSnakeHead().setSnakePartPositionX(gc.getBoardWidth()-10);
        }
        if (snake.getSnakeHead().getSnakePartPositionX() >= gc.getBoardWidth()){
            snake.getSnakeHead().setSnakePartPositionX(0);
        }
        if (snake.getSnakeHead().getSnakePartPositionY() < 0){
            snake.getSnakeHead().setSnakePartPositionY(gc.getBoardHeight()-10);
        }
        if (snake.getSnakeHead().getSnakePartPositionY() >= gc.getBoardHeight()){
            snake.getSnakeHead().setSnakePartPositionY(0);
        }
    }
}
