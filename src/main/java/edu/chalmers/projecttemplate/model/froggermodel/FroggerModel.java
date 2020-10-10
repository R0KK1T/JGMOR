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
        initLanes();
    }
    public void update(){
        checkForCollision();
    }
    public void initLanes(){
        //Starting lane
        lanes.add(factory.createEmptyLane(windowSizeY - squareDimension));
        //All consecutive roads
        for (int i = 2; i < 7; i++) {
            lanes.add(factory.createRoadLane(5, windowSizeY - squareDimension * i));
        }
        //Middle safe lane
        lanes.add(factory.createEmptyLane(windowSizeY - squareDimension * 7));
        //All consecutive rivers
        for (int i = 8; i < 13; i++) {
            lanes.add(factory.createRiverLane(3, windowSizeY - squareDimension * i));
        }
        //Finish line
        lanes.add(factory.createFinishLane(columns/2,0));
    }

    private void checkForCollision() {

    }
    public Lane getCurrentPlayerLane(){
        for (int i = 0; i < getLanes().size(); i++) {
            if(player.getY() == getLanes().get(i).getY()){
                return getLanes().get(i);
            }
        }
        return null;
    }

    public ArrayList<Lane> getLanes() {
        return lanes;
    }
    public Frog getPlayer(){
        return player;
    }

    public int getSquareDimension() {
        return squareDimension;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows(){
        return rows;
    }

    public int getWindowSizeY() {
        return windowSizeY;
    }
}
