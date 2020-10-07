package edu.chalmers.projecttemplate.model.froggermodel;

import java.util.ArrayList;

public class Lane {
    private ArrayList<Obstacle> obstacles;
    private int y;
    private int velocity;
    private boolean river;
    //Plan is to utilize a factory pattern to generate Lanes
    public Lane(ArrayList<Obstacle> obstacles, int velocity, int y, boolean river){
        this.obstacles = obstacles;
        this.velocity = velocity;
        this.y = y;
        this.river = river;
    }

    public ArrayList<Obstacle> getObstacles(){
        return new ArrayList<>(this.obstacles);
    }

    public int getY() {
        return y;
    }

    public int getVelocity() {
        return velocity;
    }

    public boolean isRiver() {
        return river;
    }
}
