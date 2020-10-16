package edu.chalmers.projecttemplate.model.breakoutmodel;

import edu.chalmers.projecttemplate.model.common.Rectangle;

/*
 * The class extends Rectangle from Javafx library. (Maybe later Rectangle from commons package)
 * The class has some common constants. The windowSizeX and windowSizeY constants store the dimensions of the board.
 * The x and y store the position of the rectangle (brick, paddle, ball).
 * The width and height store the dimension of the rectangle.
 */
public class Commons extends Rectangle {
    private int windowSizeX;
    private int windowSizeY;
    public Commons(int x, int y, int width, int height) {
        super(x, y, width, height);
        windowSizeX = 900;
        windowSizeY = 550;
    }
    public int getWindowSizeX() {
        return windowSizeX;
    }
    public int getWindowSizeY() {
        return windowSizeY;
    }
}
