package edu.chalmers.projecttemplate.model.snakemodel;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class GameObject implements Renderable{
    protected Point2D position;
    protected double width;
    protected double height;
    protected boolean alive;

    public GameObject() {
        position = new Point2D(0, 0);
        alive = true;
    }

    public GameObject(double width, double height) {
        position = new Point2D(0, 0);
        this.width = width;
        this.height = height;
        alive = true;
    }

    public GameObject(Point2D position) {
        this.position = position;
        alive = true;
    }

    public GameObject(Point2D position, double width, double height) {
        this.position = position;
        this.width = width;
        this.height = height;
        alive = true;
    }

    public GameObject(Point2D position, double bodySize) {
        this.position = position;
        this.width = bodySize;
        this.height = bodySize;
        alive = true;
    }


}
