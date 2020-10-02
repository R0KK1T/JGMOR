package edu.chalmers.projecttemplate.model.spaceInvadersModel;

import java.util.ArrayList;
import java.util.List;

public class SpaceInvadersModel {

    private final int windowSizeX = 1000;
    private final int windowSizeY = 900;
    private final int boundOffset = 50;

    private Spaceship player;
    private List<Alien> aliens;
    private List<Projectile> projectiles;

    private int alienSize = 40;
    private int alienOffset = 20;
    private int numberOfAlienCols = 10;
    private int numberOfAlienRows = 5;
    private int alienTopRowPos = 100;

    private int timeBetweenMove = 30;
    private int currentTime = 0;
    private int alienRowToMove = -1;

    private boolean moveDown = false;

    public SpaceInvadersModel() {
        //create player, aliens and projectile
        player = new Spaceship(100, 800, 40, 20, 10);
        aliens = new ArrayList<>();
        projectiles = new ArrayList<>();

        //populate list of aliens
        for (int i = 0; i < numberOfAlienCols; i++) {
            for (int j = 0; j < numberOfAlienRows; j++) {
                aliens.add(new Alien(boundOffset + (alienSize+alienSize)*i, alienTopRowPos + (alienSize+alienOffset)*j, alienSize, 20));
            }
        }
    }

    public void update(){
        //check collision with walls if all rows have moved to the same xposition
        if (alienRowToMove == numberOfAlienRows - 1){
            checkAlienBounds();
        }
        //move all movable entities
        moveEntities();
    }

    private void moveEntities(){
        //move player
        player.move();

        //move aliens downwards and reset which row to move
        if (alienRowToMove < 0){
            alienRowToMove = numberOfAlienRows - 1;
            if(moveDown){
                for (int i = 0; i < aliens.size(); i++) {
                    aliens.get(i).moveDown();
                }
                alienTopRowPos += alienSize + alienOffset;
                moveDown = false;
            }
        }

        //move alien row sideways if timer is right
        if (currentTime >= timeBetweenMove){
            for (int i = 0; i < aliens.size(); i++) {
                if (aliens.get(i).getYpos() == alienTopRowPos + ((alienSize + alienOffset) * alienRowToMove)){
                    aliens.get(i).move();
                }
            }
            alienRowToMove--;
            currentTime = 0;
        }
        else{
            currentTime++;
        }

        //move all projectiles
        for (int i = 0; i < projectiles.size(); i++) {
            projectiles.get(i).move();
        }
    }

    private void checkAlienBounds(){
        //check if aliens has hit the right or the left wall, if yes aliens should move do
        for (int i = 0; i < aliens.size(); i++) {
            if (aliens.get(i).getDirection() == 1 && (aliens.get(i).getXpos() + aliens.get(i).getWidth()) >= windowSizeX - boundOffset){
                moveDown = true;
                break;
            }
            else if(aliens.get(i).getDirection() == -1 && aliens.get(i).getXpos() <= boundOffset){
                moveDown = true;
                break;
            }
        }
    }

    public void setPlayerDirection(int direction){
        //set direction of the player (-1, 0, 1)(left, stay, right)
        player.setDirection(direction);
    }

    private void aliensShoot(){

    }

    public void playerShoot(){

    }

    public MovableObject getPlayer(){
        //return the player
        return player;
    }

    public int getSizeX() {
        //return size x of playing field
        return windowSizeX;
    }

    public int getSizeY() {
        //return size y of playing field
        return windowSizeY;
    }

    public List<MovableObject> getAliens(){
        //return a list of MovableObjects containing all aliens
        List<MovableObject> alienRenderList = new ArrayList<>();
        for (int i = 0; i < aliens.size(); i++) {
            alienRenderList.add(aliens.get(i));
        }
        return alienRenderList;
    }
}
