package edu.chalmers.projecttemplate.model.snakemodel;

import javafx.scene.canvas.GraphicsContext;

public class Food extends GameObject{

    public Food(double width, double height) {
        super(width, height);
    }
    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(Assets.apple.getImage(), position.getX(), position.getY());
    }
}