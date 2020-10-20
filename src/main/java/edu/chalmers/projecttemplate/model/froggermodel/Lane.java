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
     * Constructs an instance of object Lane with specified obstacles, coordinates, length and type
     * @param obstacles the obstacles Lane is to hold
     * @param x the x-coordinate of the lane
     * @param y the y-coordinate of the Lane
     * @param length the length obstacles are allowed to travel from x
     * @param river whether or not the Lane is a river
     */
    public Lane(ArrayList<Obstacle> obstacles, int x, int y, int length,  boolean river){
        this.obstacles = obstacles;
        this.x = x;
        this.y = y;
        this.length = length;
        this.river = river;
    }

    /**
     * Updates all obstacles' positions
     */
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

    /**
     * Getter for ArrayList obstacles
     * @return ArrayList of Obstacle, copy of obstacles
     */
    public ArrayList<Obstacle> getObstacles(){
        return new ArrayList<>(this.obstacles);
    }

    /**
     * Getter for y-coordinate
     * @return int, y
     */
    public int getY() {
        return y;
    }

    /**
     * Returns whether or not Lane is a river
     * @return boolean, river
     */
    public boolean isRiver() {
        return river;
    }
}
