package edu.chalmers.projecttemplate.model.froggermodel;

import java.util.ArrayList;

public class FroggerModel {
    private int squareDimension = 75;
    private int columns = 13;
    private int rows = 13;
    private int windowSizeX;
    private int windowSizeY;
    private int lifeCount = 7;
    private int currentLifeCount;

    private Frog player;
    private LaneFactory factory;
    private ArrayList<Lane> lanes;
    private ArrayList<IPositionable> positionables;
    private boolean changesToPositionables = false;
    private int updateUnits = 0;
    private int delayAmount = 3;

    public FroggerModel() {
        windowSizeX = squareDimension * columns;
        windowSizeY = squareDimension * rows;
        factory = new LaneFactory(squareDimension, columns, 1, 5);
        resetGame();
    }
    private void resetGame(){
        //Create frog
        newFrog();
        //Set life count
        currentLifeCount = lifeCount;
        //Create lanes in accordance to Frogger Map
        initLanes();
    }
    private void initLanes(){
        changesToPositionables = true;
        lanes = new ArrayList<>();
        //Starting lane
        lanes.add(factory.createEmptyLane(windowSizeY - squareDimension));
        //All consecutive roads
        for (int i = 2; i < 7; i++) {
            lanes.add(factory.createRoadLane(3, windowSizeY - squareDimension * i));
        }
        //Middle safe lane
        lanes.add(factory.createEmptyLane(windowSizeY - squareDimension * 7));
        //All consecutive rivers
        for (int i = 8; i < 13; i++) {
            lanes.add(factory.createRiverLane(2, windowSizeY - squareDimension * i));
        }
        //Finish line
        lanes.add(factory.createFinishLane(columns/3 + 1,0));
    }
    private void newFrog(){
        changesToPositionables = true;
        player = new Frog(squareDimension * (columns/2), windowSizeY - squareDimension,
                squareDimension, squareDimension, squareDimension);
    }

    public void update(){
        if(updateUnits >= delayAmount){
            moveObstacles();
            player.update();
            updateUnits = 0;
        }
        else{
            updateUnits++;
        }
        checkForPlayerInteraction();
    }
    private void moveObstacles(){
        for (Obstacle obs:getAllObstacles()) {
            obs.move();
            if(obs.getX() > windowSizeX + squareDimension && obs.getVelocity() > 0){
                obs.moveTo(-squareDimension - obs.getWidth());
            }
            else if(obs.getX() + obs.getWidth() < -squareDimension && obs.getVelocity() < 0){
                obs.moveTo(windowSizeX + squareDimension);
            }
        }
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
    private void loseLife(){
        currentLifeCount--;
        if(currentLifeCount > 0){
            newFrog();
        }
        else{
            resetGame();
        }
    }

    //Getter to check which lane player is currently on
    public Lane getCurrentPlayerLane(){
        for (int i = 0; i < getLanes().size(); i++) {
            if(player.getY() == getLanes().get(i).getY()){
                return getLanes().get(i);
            }
        }
        return null;
    }
    //Getter for obstacle colliding with player
    public Obstacle collisionDetected(Lane current){
        for (Obstacle obs: current.getObstacles()) {
            if(player.getHitbox().intersect(obs.getHitbox())){
                return obs;
            }
        }
        return null;
    }


    public void movePlayer(int direction){
        switch (direction) {
            case 1:
                player.moveUp();
                break;
            case 2:
                player.moveDown();
                break;
            case 3:
                player.moveRight();
                break;
            case 4:
                player.moveLeft();
                break;
            default:
        }
    }



    public ArrayList<IPositionable> getPositionables(){
        if (changesToPositionables){
            positionables = new ArrayList<>();
            for (Obstacle obs: getAllObstacles()) {
                positionables.add(obs);
            }
            positionables.add(player);
            changesToPositionables = false;
        }
        return positionables;
    }

    private ArrayList<Obstacle> getAllObstacles(){
        ArrayList<Obstacle> returnList = new ArrayList<>();
        for (Lane lane: lanes) {
            for (Obstacle obs: lane.getObstacles()) {
                returnList.add(obs);
            }
        }
        return returnList;
    }
    //Getters, mostly for testing purposes
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

    public int getColumns() {
        return columns;
    }

    public int getWindowSizeX() {
        return windowSizeX;
    }

    public int getWindowSizeY() {
        return windowSizeY;
    }
    public int getLifeCount() {
        return lifeCount;
    }
    public int getCurrentLifeCount(){ return currentLifeCount; }
}
