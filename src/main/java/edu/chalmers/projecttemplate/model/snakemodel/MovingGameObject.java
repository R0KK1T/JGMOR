package edu.chalmers.projecttemplate.model.snakemodel;

import com.sun.javafx.scene.traversal.Direction;
import javafx.geometry.Point2D;

public class MovingGameObject extends GameObject{
    private Direction direction;

    public MovingGameObject(Point2D position, double bodySize) {
        super(position, bodySize);
    }

    public MovingGameObject(Point2D position, double bodySize, Direction direction) {
        super(position, bodySize);
        this.direction = direction;
    }

}
