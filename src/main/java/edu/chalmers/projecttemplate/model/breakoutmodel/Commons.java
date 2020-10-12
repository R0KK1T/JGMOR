package edu.chalmers.projecttemplate.model.breakoutmodel;

import javafx.scene.shape.Rectangle;

public class Commons extends Rectangle {
    public double windowSizeX;
    public double windowSizeY;
    public double x;
    public double y;
    public double width;
    public double height;
    public Commons(double x, double y, double width, double height) {
        super(x, y, width, height);
        windowSizeX = 900;
        windowSizeY = 550;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
