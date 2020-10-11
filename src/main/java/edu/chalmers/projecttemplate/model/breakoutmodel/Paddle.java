package edu.chalmers.projecttemplate.model.breakoutmodel;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;


public class Paddle extends Rectangle {
    private int dx;
    private double x;
    private double windowSizeX;
    public Paddle(int x, int y, int width, int height, double gameWidth) {
        super(x, y, width, height);
        this.dx = 0;
        this.x = x;
        windowSizeX = gameWidth;
    }
    /*
     * The paddle moves only in the horizontal direction, so we only update the x
     */
    //Move paddle by update x via dx
    public void move() {
       x += dx;
       this.setX(x);
       if (x <=0)
           x = 0;
       if (x >= windowSizeX - this.getWidth())
           x = windowSizeX - this.getWidth();
    }
    public void keyPressed(KeyEvent e) {
        KeyCode key = e.getCode();
        if (key.equals(KeyCode.LEFT)) {
            dx = -1;
            //System.out.println("Left pressed");
        }
        if (key.equals(KeyCode.RIGHT)) {
            dx = 1;
            //System.out.println("Right pressed");
        }
    }
    public void keyReleased(KeyEvent e) {
        KeyCode key = e.getCode();
        if (key.equals(KeyCode.LEFT)) {
            dx = 0;
        }
        if (key.equals(KeyCode.RIGHT)) {
            dx = 0;
        }
    }

}
