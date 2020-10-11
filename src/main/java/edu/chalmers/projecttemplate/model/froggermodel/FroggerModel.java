package edu.chalmers.projecttemplate.model.froggermodel;

import java.util.ArrayList;

public class FroggerModel {
    private int squareDimension = 25;
    private int columns = 13;
    private int rows = 13;
    private int windowSizeX;
    private int windowSizeY;
    private int lifeCount = 7;

    private Frog player;
    private LaneFactory factory;
    private ArrayList<Lane> lanes = new ArrayList<>();

    public FroggerModel() {
        windowSizeX = squareDimension * columns;
        windowSizeY = squareDimension * rows;
        newFrog();
        factory = new LaneFactory(squareDimension, squareDimension / 10, squareDimension / 5);

        //Create lanes in accordance to Frogger Map
        initLanes();
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
        lanes.add(factory.createFinishLane(columns/2 + 1,0));
    }
    private void newFrog(){
        player = new Frog(squareDimension * (columns/2), windowSizeY - squareDimension,
                squareDimension, squareDimension, squareDimension);
    }

    public void update(){
        checkForPlayerInteraction();
    }
    public Obstacle collisionDetected(Lane current){
        for (Obstacle obs: current.getObstacles()) {
            if(player.getHitbox().intersect(obs.getHitbox())){
                return obs;
            }
        }
        return null;
    }
    private void checkForPlayerInteraction() {
        Lane current = getCurrentPlayerLane();
        //Skip all if player isn't found on any lane.
        if(current == null){
            return;
        }

        Obstacle collidingObstacle = collisionDetected(current);
        //If current lane is river
        if(current.isRiver()){
            //If player is colliding with obstacle, attach.
            //Else lose life
            if(collidingObstacle != null){
                player.attach(collidingObstacle);
            }
            else{
                loseLife();
            }
        }
        else{
            //Current !isRiver()
            //If player is colliding with obstacle, lose life
            if(collidingObstacle != null){
                loseLife();
            }
        }
    }
    public Lane getCurrentPlayerLane(){
        for (int i = 0; i < getLanes().size(); i++) {
            if(player.getY() == getLanes().get(i).getY()){
                return getLanes().get(i);
            }
        }
        return null;
    }
    public void loseLife(){
        if(lifeCount > 0){
            newFrog();
            lifeCount--;
        }
        else{
            gameOver();
        }
    }
    public void gameOver(){

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
    public int getRows(){
        return rows;
    }
    public int getWindowSizeY() {
        return windowSizeY;
    }

    public int getLifeCount() {
        return lifeCount;
    }
}
