package edu.chalmers.projecttemplate.model.snakemodel;

import java.awt.Rectangle;
import java.util.ArrayList;


public class Snake {

    private int snakeX = 100;
    private int snakeY = 100;
    private boolean snakeUp = false;
    private boolean snakeDown = false;
    private boolean snakeLeft = false;
    private boolean snakeRight = true;
    private  ArrayList<Rectangle> list = new ArrayList<Rectangle>();


    public  ArrayList<Rectangle> getList() {
        return list;
    }
    public  void setList(ArrayList<Rectangle> list) {
        this.list = list;
    }

    public boolean isSnakeLeft() {
        return snakeLeft;
    }
    public void setSnakeLeft(boolean snakeLeft) {
        this.snakeLeft = snakeLeft;
    }

}
