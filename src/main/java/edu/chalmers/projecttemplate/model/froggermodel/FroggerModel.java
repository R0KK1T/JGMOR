package edu.chalmers.projecttemplate.model.froggermodel;

import java.util.ArrayList;

/**
 * Represents the structure and functionality of the old retro game Frogger
 */
public class FroggerModel {
    //Window measures and dimensions
    private int squareDimension = 75;
    private int columns = 13;
    private int rows = 13;
    private int windowSizeX;
    private int windowSizeY;

    //Game variables
    private int initialAmountOfLives = 7;
    private int currentLifeCount;
    private int frogsToSave;
    private int savedFrogs = 0;
    private int points = 0;
    private int highestLane;
    private int level;

    //Fundamental variables to create the structure of the game
    private Frog player;
    private LaneFactory factory;
    private ArrayList<Lane> lanes;

    //Delay/buffer for updating continuous motion
    private int updateUnits = 0;
    private int delayAmount = 3;

    //List of all game objects and the ability to check when to update it
    private ArrayList<IRepresentable> represents;
    private boolean changesToRepresents = false;

    /**
     * Constructs an instance of FroggerModel with no specified parameters
     */
    public FroggerModel() {
        windowSizeX = squareDimension * columns;
        windowSizeY = squareDimension * rows;
        frogsToSave = (columns - (columns/3 + 1)) / 2;
        factory = new LaneFactory(squareDimension, columns, 1, 5, -squareDimension, windowSizeX + 2*squareDimension);
        resetGame();
    }

    /**
     * Sets starting values to appropriate variables and calls function newLevel()
     */
    private void resetGame(){
        newLevel();
        //Set life count
        currentLifeCount = initialAmountOfLives;
        //Set points
        points = 0;
        //Set level
        level = 1;
    }

    /**
     * Calls functions to structure a new level and sets values to appropriate variables accordingly
     */
    private void newLevel(){
        level++;
        savedFrogs = 0;
        //Create frog
        newFrog();
        //Create lanes in accordance to the original game
        initLanes();
        //Set highestLane
        resetHighestLane();
    }

    /**
     * Sets variable highestLane to the y-coordinate of the first lane in ArrayList lanes
     */
    private void resetHighestLane(){
        highestLane = lanes.get(0).getY();
    }

    /**
     * Sets ArrayList lanes and adds lanes based on the structure of the original Frogger
     */
    private void initLanes(){
        changesToRepresents = true;
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

    /**
     * Sets variable player and sets boundaries of player
     */
    private void newFrog(){
        changesToRepresents = true;
        player = new Frog(squareDimension * (columns/2), windowSizeY - squareDimension,
                squareDimension, squareDimension, squareDimension);
        player.setBounds(0, 0, windowSizeX, windowSizeY);
    }

    /**
     * Updates the state of the entire game. Calls appropriate functions to check for
     * everything that would alter the state of the game.
     */
    public void update(){
        if(updateUnits >= delayAmount){
            updateLanes();
            player.update();
            updateUnits = 0;
        }
        else{
            updateUnits++;
        }
        checkForPlayerInteraction();
        checkForProgress();
        checkForPlayerAtFinishLine();
    }

    /**
     * Updates the state of all lanes
     */
    private void updateLanes(){
        for (Lane lane: lanes) {
            lane.update();
        }
    }

    /**
     * Calls for player to move based on parameter direction
     * @param direction int, determines which of variable player's move functions to call
     */
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

    /**
     * Calls appropriate functions to determine if player is colliding with an obstacle in a specific lane
     * and decides what consequences that would bring based on the state of the game
     */
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

    /**
     * Decreases variable currentLifeCount by one and either calls function resetGame() or newFrog() depending
     * on the value of variable currentLifeCount
     */
    private void loseLife(){
        currentLifeCount--;
        if(currentLifeCount > 0){
            newFrog();
        }
        else{
            resetGame();
        }
    }

    /**
     * Checks if player is on the last lane and calls appropriate functions accordingly, adding points and either
     * sets new level or resets player's position.
     */
    private void checkForPlayerAtFinishLine(){
        if(getCurrentPlayerLane() == lanes.get(lanes.size() - 1)){
            lanes.get(lanes.size() - 1).add(new Obstacle(player.getX(), player.getY(), squareDimension, squareDimension, 0, ObstacleType.FINISHLINEFROG));
            reachedTheFinishLine();
            if(savedFrogs >= frogsToSave){
                newLevel();
            }
            else{
                newFrog();
                resetHighestLane();
            }
        }
    }

    /**
     * Checks if player has progressed towards the goal and adds points accordingly
     */
    private void checkForProgress(){
        if(player.getY() < highestLane){
            points += 10;
            highestLane = player.getY();
        }
    }

    /**
     * Increases amount of saved frogs by one and adds 20 points
     */
    private void reachedTheFinishLine(){
        savedFrogs++;
        points += 200;
    }

    /**
     * Checks if player is colliding with an obstacle on a given lane
     * @param current Lane, the lane of which collision is investigated
     * @return Obstacle that player is colliding with. If no collision return null
     */
    public Obstacle collisionDetected(Lane current){
        for (Obstacle obs: current.getObstacles()) {
            if(player.getHitbox().intersect(obs.getHitbox())){
                return obs;
            }
        }
        return null;
    }

    /**
     * Return the lane which player is currently on
     * @return Lane, with the same y-coordinate as player. If none found, return null
     */
    public Lane getCurrentPlayerLane(){
        for (int i = 0; i < getLanes().size(); i++) {
            if(player.getY() == getLanes().get(i).getY()){
                return getLanes().get(i);
            }
        }
        return null;
    }

    /**
     * Getter for ArrayList "represents" containing all representable objects in terms of game functionality
     * @return ArrayList of IRepresentable, represents
     */
    public ArrayList<IRepresentable> getRepresents(){
        if (changesToRepresents){
            represents = new ArrayList<>();
            for (Obstacle obs: getAllObstacles()) {
                represents.add(obs);
            }
            represents.add(player);
            changesToRepresents = false;
        }
        return represents;
    }

    /**
     * Getter for all obstacles
     * @return ArrayList of Obstacle
     */
    private ArrayList<Obstacle> getAllObstacles(){
        ArrayList<Obstacle> returnList = new ArrayList<>();
        for (Lane lane: lanes) {
            for (Obstacle obs: lane.getObstacles()) {
                returnList.add(obs);
            }
        }
        return returnList;
    }

    /**
     * Getter for all lanes
     * @return ArrayList of Lane, lanes
     */
    public ArrayList<Lane> getLanes() {
        return lanes;
    }

    /**
     * Getter for object player
     * @return Frog, player
     */
    public Frog getPlayer(){
        return player;
    }

    /**
     * Getter for variable squareDimension
     * @return int, squareDimension
     */
    public int getSquareDimension() {
        return squareDimension;
    }

    /**
     * Getter for variable rows
     * @return int, rows
     */
    public int getRows(){
        return rows;
    }

    /**
     * Getter for variable columns
     * @return int, columns
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Getter for variable windowSizeX
     * @return int, windowSizeX
     */
    public int getWindowSizeX() {
        return windowSizeX;
    }

    /**
     * Getter for variable windowSizeY
     * @return int, windowSizeY
     */
    public int getWindowSizeY() {
        return windowSizeY;
    }

    /**
     * Getter for variable initialAmountOfLives
     * @return int, initialAmountOfLives
     */
    public int getInitialAmountOfLives() {
        return initialAmountOfLives;
    }

    /**
     * Getter for variable currentLifeCount
     * @return int, currentLifeCount
     */
    public int getCurrentLifeCount(){ return currentLifeCount; }

    /**
     * Getter for variable points
     * @return int, points
     */
    public int getPoints() {
        return points;
    }

    /**
     * Getter for variable level
     * @return int, level
     */
    public int getLevel(){
        return level;
    }
}
