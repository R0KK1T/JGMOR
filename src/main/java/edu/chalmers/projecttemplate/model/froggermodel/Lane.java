package edu.chalmers.projecttemplate.model.froggermodel;

import java.util.ArrayList;

/**
 * Represents a row of obstacles,
 */
public class Lane {
    private ArrayList<Obstacle> obstacles;
    private int x;
    private int y;
    private int length;
    private boolean river;

    /**
     * Constructs an instance of object Lane with specified obstacles, y-coordinate and type
     * @param obstacles the obstacles Lane is to hold
     * @param y the y-coordinate of the Lane
     * @param river whether or not the Lane is a river
     */
    public Lane(ArrayList<Obstacle> obstacles, int x, int y, int length,  boolean river){
        this.obstacles = obstacles;
        this.x = x;
        this.y = y;
        this.length = length;
        this.river = river;
    }
    public void update(){
        for (Obstacle obs: obstacles) {
            obs.move();
            if(obs.getX() > x + length && obs.getVelocity() > 0){
                obs.moveTo(x - obs.getWidth());
            }
            else if(obs.getX() + obs.getWidth() < x && obs.getVelocity() < 0){
                obs.moveTo(x + length);
            }
        }
    }
    /**
     * Adds an obstacle to ArrayList obstacles
     * @param obs the obstacle to be added
     */
    public void add(Obstacle obs){
        obstacles.add(obs);
    }

    public ArrayList<Obstacle> getObstacles(){
        return new ArrayList<>(this.obstacles);
    }

    public int getY() {
        return y;
    }
    public boolean isRiver() {
        return river;
    }
}
