package edu.chalmers.projecttemplate.model.snake22model;

public class CollisionDetectionModule {

    private Snake snake;
    private Food food;
    private GameConfiguration gc;

    public CollisionDetectionModule(Snake snake, Food food, GameConfiguration gameConfiguration) {
        this.snake = snake;
        this.food = food;
        this.gc = gameConfiguration;
    }

    public boolean detectWallCollision(){
        if (snake.getSnakeHead().getSnakePartPositionX() < 0 || snake.getSnakeHead().getSnakePartPositionX() >= gc.getBoardWidth() ||
                snake.getSnakeHead().getSnakePartPositionY() < 0 || snake.getSnakeHead().getSnakePartPositionY() >= gc.getBoardHeight()){
            System.out.println("Wall collision!!!");
            return true;
        }
        return false;
    }

    // snake biting itself
    public boolean detectOwnCollision(){
        for (int i = 1; i < snake.getSnakeParts().size(); i++) {
            if (snake.getSnakeParts().get(i).getSnakePartPositionX() == snake.getSnakeHead().getSnakePartPositionX() && snake.getSnakeParts().get(i).getSnakePartPositionY() == snake.getSnakeHead().getSnakePartPositionY()){
                System.out.println("DAMN!! I've bit my tail!");
                return true;
            }
        }
        return false;
    }

    // om nom nom
    public boolean detectFoodCollision(){
        if (snake.getSnakeHead().getSnakePartPositionX() == food.getFoodPositionX() && snake.getSnakeHead().getSnakePartPositionY() == food.getFoodPositionY()){
            System.out.println("Om nom nom!");
            return true;
        }
        return false;
    }

    //goFromOtherSideOnWallCollision
    public  void goFromOtherSideOnWallCollision(){
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
