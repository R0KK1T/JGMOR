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
    private List<Barrier> barriers;
    private List<IPositionable> positionables;

    private int alienSize = 40;
    private int alienOffset = 20;
    private int numberOfAlienCols = 10;
    private int numberOfAlienRows = 5;
    private int alienTopRowPos = 100;

    private int numberOfBarriers = 4;

    private int timeBetweenPlayerShots = 150;
    private int timeSinceLastPlayerShot = 10000;

    private int timeBetweenMove = 40;
    private int currentTime = 0;
    private int alienRowToMove = -1;

    private boolean moveDown = false;


    public SpaceInvadersModel() {
        //create player, aliens and projectile
        player = new Spaceship(100, 800, 40, 20, 2);
        aliens = new ArrayList<>();
        projectiles = new ArrayList<>();
        barriers = new ArrayList<>();

        //populate list of aliens
        for (int i = 0; i < numberOfAlienCols; i++) {
            for (int j = 0; j < numberOfAlienRows; j++) {
                aliens.add(new Alien(boundOffset + (alienSize+alienSize)*i, alienTopRowPos + (alienSize+alienOffset)*j, alienSize, 20));
            }
        }

        //create barriers
        for (int i = 0; i < numberOfBarriers; i++) {
            barriers.add(new Barrier(boundOffset + 200 * i, 650, 50, 50));
        }
    }

    public void update(){
        //check collision with walls if all rows have moved to the same xposition
        if (alienRowToMove == numberOfAlienRows - 1){
            checkAlienBounds();
        }
        //move all movable entities
        moveEntities();

        //All aliens shoot if its time for them to shoot
        aliensShoot();

        //increase time since last player shoot'
        timeSinceLastPlayerShot++;
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

        for (int i = 0; i < aliens.size(); i++) {
            if(aliens.get(i).getTimeSinceLastShot() >= aliens.get(i).getTimeBetweenShots()){
                aliens.get(i).resetTimeScinceLastShot();
                projectiles.add(new Projectile(aliens.get(i).getXpos(), aliens.get(i).getYpos(), 1));
            }
            else{
                aliens.get(i).incTimeScinceLastShot();
            }
        }
    }

    public void playerShoot(){
        if (timeSinceLastPlayerShot >= timeBetweenPlayerShots){
            projectiles.add(new Projectile(player.getXpos(), player.getYpos(), -1));
            timeSinceLastPlayerShot = 0;
        }
    }

    public int getWindowSizeX() {
        //return size x of playing field
        return windowSizeX;
    }

    public int getWindowSizeY() {
        //return size y of playing field
        return windowSizeY;
    }

    //TODO remove get player because it's redundant code
    public MovableObject getPlayer(){
        //return the player
        return player;
    }

    public List<IPositionable> getPositionables(){
        positionables = new ArrayList<>();

        //add player
        positionables.add(player);
        positionables.get(0);

        //add aliens
        for (int i = 0; i < aliens.size(); i++) {
            positionables.add(aliens.get(i));
        }

        //add projectiles
        for (int i = 0; i < projectiles.size(); i++) {
            positionables.add(projectiles.get(i));
        }

        //add barriers
        for (int i = 0; i < barriers.size(); i++) {
            positionables.add(barriers.get(i));
        }

        return positionables;
    }
}
