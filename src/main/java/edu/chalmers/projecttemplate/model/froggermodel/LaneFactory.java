package edu.chalmers.projecttemplate.model.froggermodel;

import java.util.ArrayList;
import java.util.Random;

public class LaneFactory {
    private Random rand = new Random();
    private int standardWidth;
    private int minSpeed;
    private int maxSpeed;
    private int columns;
    private int x;
    private int length;

    public LaneFactory(int standardWidth, int columns, int minSpeed, int maxSpeed, int x, int length){
        this.standardWidth = standardWidth;
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
        this.columns = columns;
        this.x = x;
        this.length = length;
    }

    private int randomSpeed(){
        int velocity = minSpeed + rand.nextInt(maxSpeed-minSpeed + 1);
        if(rand.nextInt(2) == 1){
            velocity *= -1;
        }
        return velocity;
    }
    private int spacing(int amountOfObstacles){
        return standardWidth * (rand.nextInt(columns/amountOfObstacles) + 1);
    }

    public Lane createRoadLane(int amountOfObstacles, int y){
        ArrayList<Obstacle> obs = new ArrayList<>();
        int velocity = randomSpeed();

        obs.add(new Obstacle(spacing(amountOfObstacles), y, standardWidth, standardWidth, velocity, ObstacleType.CAR));
        for (int i = 1; i < amountOfObstacles; i++) {
            int offset = standardWidth * (rand.nextInt(3)+1);
            obs.add(new Obstacle(obs.get(i-1).getX() + obs.get(i-1).getWidth() + spacing(amountOfObstacles), y, standardWidth, standardWidth, velocity, ObstacleType.CAR));
        }

        Lane returnLane = new Lane(obs, x, y, length, false);
        
        return returnLane;
    }
    public Lane createRiverLane(int amountOfObstacles, int y){
        ArrayList<Obstacle> obs = new ArrayList<>();
        int velocity = randomSpeed();
        obs.add(new Obstacle(spacing(amountOfObstacles), y, 3* standardWidth, standardWidth, velocity, ObstacleType.LOG));
        for (int i = 1; i < amountOfObstacles; i++) {
            obs.add(new Obstacle(obs.get(i-1).getX() + obs.get(i-1).getWidth() + spacing(amountOfObstacles), y, 3* standardWidth, standardWidth, velocity, ObstacleType.LOG));
        }
        Lane returnLane = new Lane(obs, x, y, length, true);

        return returnLane;
    }
    public Lane createEmptyLane(int y){
        ArrayList<Obstacle> obs = new ArrayList<>();
        Lane returnLane = new Lane(obs, x, y, length, false);

        return returnLane;
    }
    public Lane createFinishLane(int amountOfObstacles, int y){
        ArrayList<Obstacle> obs = new ArrayList<>();
        int velocity = 0;
        for (int i = 0; i < amountOfObstacles; i++) {
            obs.add(new Obstacle(standardWidth *i*3, y, standardWidth, standardWidth, velocity, ObstacleType.GRASS));
        }
        Lane returnLane = new Lane(obs, x, y, length, false);

        return returnLane;
    }
}
