package edu.chalmers.projecttemplate.model.froggermodel;

import java.util.ArrayList;

public class FroggerModel {
    private int squareDimension = 25;
    private int columns = 11;
    private int rows = 13;
    private int windowSizeX;
    private int windowSizeY;

    private Frog player;
    private LaneFactory factory;
    private ArrayList<Lane> lanes = new ArrayList<>();

    public FroggerModel() {
        windowSizeX = squareDimension * columns;
        windowSizeY = squareDimension * rows;
        player = new Frog(squareDimension * (columns/2), windowSizeY - squareDimension,
                squareDimension, squareDimension, squareDimension);
        factory = new LaneFactory(squareDimension, squareDimension / 10, squareDimension / 5);

        //Create lanes in accordance to Frogger Map
        //Starting lane
        lanes.add(factory.createEmptyLane(windowSizeY - squareDimension));
        for (int i = 0; i < 5; i++) {
            lanes.add(factory.createRoadLane(5, windowSizeY - squareDimension * (i + 2)));
        }
        lanes.add(factory.createEmptyLane(windowSizeY - 8 * squareDimension));
        for (int i = 0; i < 5; i++) {
            lanes.add(factory.createRiverLane(3, windowSizeY - squareDimension * (i + 9)));
        }
        lanes.add(factory.createFinishLane(columns/2,0));
    }

    public ArrayList<Lane> getLanes() {
        return lanes;
    }
}
