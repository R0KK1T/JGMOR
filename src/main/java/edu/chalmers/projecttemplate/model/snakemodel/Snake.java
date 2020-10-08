package edu.chalmers.projecttemplate.model.snakemodel;

import com.sun.javafx.scene.traversal.Direction;
import javafx.geometry.Point2D;

import java.util.LinkedList;


public class Snake {
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

}
