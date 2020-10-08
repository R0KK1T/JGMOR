package edu.chalmers.projecttemplate.model.froggermodel;

import java.util.ArrayList;
import java.util.Random;

public class LaneFactory {
    private Random rand = new Random();
    private int obsWidth;
    private int minSpeed;
    private int maxSpeed;

    public LaneFactory(int standardWidth, int minSpeed, int maxSpeed){
        obsWidth = standardWidth;
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
    }

    public Lane createRoadLane(int amountOfObstacles, int y){
        ArrayList<Obstacle> obs = new ArrayList<>();
        int velocity = minSpeed + rand.nextInt(maxSpeed);
        for (int i = 0; i < amountOfObstacles; i++) {
            obs.add(new Car(obsWidth*i  + rand.nextInt(obsWidth), y, obsWidth, obsWidth, velocity));
        }
        Lane returnLane = new Lane(obs, velocity, y, false);
        
        return returnLane;
    }
    public Lane createRiverLane(int amountOfObstacles, int y){
        ArrayList<Obstacle> obs = new ArrayList<>();
        int velocity = minSpeed + rand.nextInt(maxSpeed);
        for (int i = 0; i < amountOfObstacles; i++) {
            obs.add(new Log(obsWidth*i + rand.nextInt(obsWidth), y, 3*obsWidth, obsWidth, velocity));
        }
        Lane returnLane = new Lane(obs, velocity, y, true);

        return returnLane;
    }
    public Lane createEmptyLane(int y){
        ArrayList<Obstacle> obs = new ArrayList<>();
        Lane returnLane = new Lane(obs, 0, y, false);

        return returnLane;
    }
}
