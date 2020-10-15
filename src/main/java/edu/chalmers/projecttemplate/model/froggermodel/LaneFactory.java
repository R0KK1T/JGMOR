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

    private int randomSpeed(){
        int velocity = minSpeed + rand.nextInt(maxSpeed-minSpeed + 1);
        if(rand.nextInt(2) == 1){
            velocity *= -1;
        }
        return velocity;
    }

    public Lane createRoadLane(int amountOfObstacles, int y){
        ArrayList<Obstacle> obs = new ArrayList<>();
        int velocity = randomSpeed();

        obs.add(new Obstacle(obsWidth * (rand.nextInt(3)), y, obsWidth, obsWidth, velocity));
        for (int i = 1; i < amountOfObstacles; i++) {
            int offset = obsWidth * (rand.nextInt(3)+1);
            obs.add(new Obstacle(obs.get(i-1).getX() + obs.get(i-1).getWidth() + offset, y, obsWidth, obsWidth, velocity));
        }

        Lane returnLane = new Lane(obs, velocity, y, false);
        
        return returnLane;
    }
    public Lane createRiverLane(int amountOfObstacles, int y){
        ArrayList<Obstacle> obs = new ArrayList<>();
        int velocity = randomSpeed();
        obs.add(new Obstacle(obsWidth * (rand.nextInt(3)+1), y, 3*obsWidth, obsWidth, velocity));
        for (int i = 1; i < amountOfObstacles; i++) {
            obs.add(new Obstacle(obs.get(i-1).getX() + obs.get(i-1).getWidth() + obsWidth * (rand.nextInt(5)+1), y, 3*obsWidth, obsWidth, velocity));
        }
        Lane returnLane = new Lane(obs, velocity, y, true);

        return returnLane;
    }
    public Lane createEmptyLane(int y){
        ArrayList<Obstacle> obs = new ArrayList<>();
        Lane returnLane = new Lane(obs, 0, y, false);

        return returnLane;
    }
    public Lane createFinishLane(int amountOfObstacles, int y){
        ArrayList<Obstacle> obs = new ArrayList<>();
        int velocity = 0;
        for (int i = 0; i < amountOfObstacles; i++) {
            obs.add(new Obstacle(obsWidth*i*3, y, obsWidth, obsWidth, velocity));
        }
        Lane returnLane = new Lane(obs, velocity, y, false);

        return returnLane;
    }
}
