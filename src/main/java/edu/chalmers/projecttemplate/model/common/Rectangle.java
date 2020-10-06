package edu.chalmers.projecttemplate.model.common;

public class Rectangle {
    private int x;
    private int y;
    private int width;
    private int height;

    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public boolean intersect(Rectangle rect){
        return !((this.x >= rect.getX()+rect.getWidth()) || (this.x + this.width <= rect.getWidth())
                || (this.y >= rect.getY() + rect.getHeight()) || (this.y + this.height <= rect.getY()));
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void incX(int i) {
        x += i;
    }

    public void incY(int i) {
        y += i;
    }

    public void decX(int i) {
        x -= i;
    }

    public void decY(int i) {
        y -= i;
    }

}
