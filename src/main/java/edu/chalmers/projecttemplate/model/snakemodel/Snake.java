package edu.chalmers.projecttemplate.model.snakemodel;


import com.sun.javafx.scene.traversal.Direction;
import javafx.geometry.Point2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.LinkedList;

public class Snake implements Renderable {
    private LinkedList<MovingGameObject> body = new LinkedList<>();
    private int bodySize;
    private MovingGameObject tail;

    private Direction direction = Direction.RIGHT;

    public Snake(Point2D head, Point2D tail, int bodySize) {
        this.bodySize = bodySize;
        body.add(new MovingGameObject(head, bodySize));
        body.add(new MovingGameObject(tail, bodySize));
    }

    public MovingGameObject getHead() {
        return body.getFirst();
    }
    public MovingGameObject getNeck() {
        return body.get(1);
    }

    public Direction getDirection() {
        return direction;
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
    }


    public void move() {
        tail = body.pollLast();

        switch (direction) {
            case UP: {
                Point2D newPos = getHead().getPosition().subtract(0, bodySize);
                body.addFirst(new MovingGameObject(newPos, bodySize, direction));
                break;
            }
            case DOWN: {
                Point2D newPos = getHead().getPosition().add(0, bodySize);
                body.addFirst(new MovingGameObject(newPos, bodySize, direction));
                break;
            }
            case LEFT: {
                Point2D newPos = getHead().getPosition().subtract(bodySize, 0);
                body.addFirst(new MovingGameObject(newPos, bodySize, direction));
                break;
            }
            case RIGHT: {
                Point2D newPos = getHead().getPosition().add(bodySize, 0);
                body.addFirst(new MovingGameObject(newPos, bodySize, direction));
                break;
            }
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        switch (direction) {
            case RIGHT: {
                Assets.snake_head.setRotate(-90);
                break;
            }
            case DOWN: {
                Assets.snake_head.setRotate(0);
                break;
            }
            case UP: {
                Assets.snake_head.setRotate(180);
                break;
            }
            case LEFT: {
                Assets.snake_head.setRotate(90);
                break;
            }
        }

        gc.drawImage(Assets.snake_head.snapshot(new SnapshotParameters(), null),
                getHead().getPosition().getX() + 1,
                getHead().getPosition().getY() + 1,
                23,
                23);
        gc.drawImage(Assets.snake_body.getImage(),
                getNeck().getPosition().getX() + 1,
                getNeck().getPosition().getY() + 1,
                23,
                23);

        if (tail != null) {
            gc.setFill(Color.BLACK);
            tail.render(gc);
        }

    }
}
