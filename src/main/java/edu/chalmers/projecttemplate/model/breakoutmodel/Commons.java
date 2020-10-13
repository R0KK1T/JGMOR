package edu.chalmers.projecttemplate.model.breakoutmodel;

import javafx.scene.shape.Rectangle;
/*
 * The class extends Rectangle from Javafx library. (Maybe later Rectangle from commons package)
 * The class has some common constants. The windowSizeX and windowSizeY constants store the dimensions of the board.
 * The x and y store the position of the rectangle (brick, paddle, ball).
 * The width and height store the dimension of the rectangle.
 */
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
