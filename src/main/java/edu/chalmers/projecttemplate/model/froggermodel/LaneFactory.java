package edu.chalmers.projecttemplate.model.froggermodel;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class which soul responsibility is to create certain types of lanes for class FroggerModel
 */
public class LaneFactory {
    private Random rand = new Random();
    private int standardWidth;
    private int minSpeed;
    private int maxSpeed;
    private int columns;
    private int x;
    private int length;

    /**
     * Constructs an instance of LaneFactory based on the following parameters
     * @param standardWidth the standard width for the obstacles to be created for the lanes
     * @param columns how many potential units of space a lane should have
     * @param minSpeed minimum speed obstacles should have
     * @param maxSpeed maximum speed obstacles should have
     * @param x the x-coordinate lanes are to take
     * @param length the length lanes are to take
     */
    public LaneFactory(int standardWidth, int columns, int minSpeed, int maxSpeed, int x, int length){
        this.standardWidth = standardWidth;
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
        this.columns = columns;
        this.x = x;
        this.length = length;
    }

    /**
     * Gives a random speed based on variables minSpeed and maxSpeed. Also has a 50% chance of multiplying it by -1
     * @return int, velocity
     */
    private int randomSpeed(){
        int velocity = minSpeed + rand.nextInt(maxSpeed-minSpeed + 1);
        if(rand.nextInt(2) == 1){
            velocity *= -1;
        }
        return velocity;
    }

    /**
     * Gives a random distance based on variables columns and amountOfObstacles
     * @param amountOfObstacles the amount of obstacles on a given lane
     * @return int, distance to previous obstacle in lane
     */
    private int spacing(int amountOfObstacles){
        return standardWidth * (rand.nextInt(columns/amountOfObstacles) + 1);
    }

    /**
     * Creates an instance of Lane based on LaneFactory variables and given variables. Adds Obstacles with type CAR and
     * sets river to false
     * @param amountOfObstacles amount of obstacles to be added to the Lane
     * @param y the y-coordinate for the Lane
     * @return Lane, returnLane
     */
    public Lane createRoadLane(int amountOfObstacles, int y){
        ArrayList<Obstacle> obs = new ArrayList<>();
        int velocity = randomSpeed();

        obs.add(new Obstacle(spacing(amountOfObstacles), y, standardWidth, standardWidth, velocity, ObstacleType.CAR));
        for (int i = 1; i < amountOfObstacles; i++) {
            int offset = standardWidth * (rand.nextInt(3)+1);
            obs.add(new Obstacle(obs.get(i-1).getX() + obs.get(i-1).getWidth() + spacing(amountOfObstacles),
                    y, standardWidth, standardWidth, velocity, ObstacleType.CAR));
        }

        Lane returnLane = new Lane(obs, x, y, length, false);
        
        return returnLane;
    }
    /**
     * Creates an instance of Lane based on LaneFactory variables and given variables. Adds Obstacles with type LOG and
     * sets river to true
     * @param amountOfObstacles amount of obstacles to be added to the Lane
     * @param y the y-coordinate for the Lane
     * @return Lane, returnLane
     */
    public Lane createRiverLane(int amountOfObstacles, int y){
        ArrayList<Obstacle> obs = new ArrayList<>();
        int velocity = randomSpeed();

        obs.add(new Obstacle(spacing(amountOfObstacles), y, 3* standardWidth, standardWidth,
                velocity, ObstacleType.LOG));
        for (int i = 1; i < amountOfObstacles; i++) {
            obs.add(new Obstacle(obs.get(i-1).getX() + obs.get(i-1).getWidth() + spacing(amountOfObstacles),
                    y, 3* standardWidth, standardWidth, velocity, ObstacleType.LOG));
        }
        Lane returnLane = new Lane(obs, x, y, length, true);

        return returnLane;
    }

    /**
     * Creates an instance of Lane based on LaneFactory variables and given variables. Adds no Obstacles and
     * sets river to false.
     * @param y the y-coordinate for the Lane
     * @return Lane, returnLane
     */
    public Lane createEmptyLane(int y){
        ArrayList<Obstacle> obs = new ArrayList<>();
        Lane returnLane = new Lane(obs, x, y, length, false);

        return returnLane;
    }

    /**
     * Creates an instance of Lane based on LaneFactory variables and given variables.
     * Adds Obstacles with type GRASS and sets river to false.
     * @param amountOfObstacles amount of obstacles to be added to the Lane
     * @param y the y-coordinate for the Lane
     * @return Lane, returnLane
     */
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
