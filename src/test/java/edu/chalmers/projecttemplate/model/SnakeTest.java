package edu.chalmers.projecttemplate.model;

import edu.chalmers.projecttemplate.model.snakemodel.Snake;

import com.sun.javafx.scene.traversal.Direction;
import javafx.geometry.Point2D;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SnakeTest {
    private Snake snake;
    private Point2D head;
    private Point2D tail;

    @Before
    public void init() {
        head = new Point2D(1,0);
        tail = new Point2D(0,0);
        snake = new Snake(head,tail,1);
        snake.setDirection(Direction.DOWN);
    }

    @Test
    public void test_headPosition() {
        Assert.assertEquals(head,snake.getHead().getPosition());
    }

    @Test
    public void test_getBodyPosition() {
        Assert.assertEquals(tail,snake.getBody(1).getPosition());
    }
}