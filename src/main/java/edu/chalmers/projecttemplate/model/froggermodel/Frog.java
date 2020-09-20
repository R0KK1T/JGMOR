package edu.chalmers.projecttemplate.model.froggermodel;

public class Frog {
    private Rectangle hitbox;
    public Frog(int x, int y, int width, int height){
        hitbox = new Rectangle(x, y, width, height);
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
}
