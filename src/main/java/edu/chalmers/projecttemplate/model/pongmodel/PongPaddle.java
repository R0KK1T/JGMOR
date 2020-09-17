package edu.chalmers.projecttemplate.model.pongmodel;

public class PongPaddle extends GameObject implements IMovable {
    @Override
    public void move(double changeInX, double changeInY) {
        x = x + changeInX;
        y = y + changeInY;
    }
}
