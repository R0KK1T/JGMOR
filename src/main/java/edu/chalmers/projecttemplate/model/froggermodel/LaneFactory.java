package edu.chalmers.projecttemplate.model.froggermodel;

import java.util.ArrayList;

public class LaneFactory {
    public Lane createRoadLane(int amountOfObstacles, int velocity, int y){
        ArrayList<Obstacle> obs = new ArrayList<>();
        for (int i = 0; i < amountOfObstacles; i++) {
            obs.add(new Car(10*i, y, 10, 10, velocity));
        }
        Lane returnLane = new Lane(obs, velocity, y, false);
        
        return returnLane;
    }
}
