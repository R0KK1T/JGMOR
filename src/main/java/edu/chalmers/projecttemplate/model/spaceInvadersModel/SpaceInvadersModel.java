package edu.chalmers.projecttemplate.model.spaceInvadersModel;

import java.util.ArrayList;
import java.util.List;

public class SpaceInvadersModel {

    //window variables
    private final int windowSizeX = 1000;
    private final int windowSizeY = 900;
    private final int boundOffset = 50;

    //all gameObjects
    private Spaceship player;
    private List<Alien> aliens;
    private List<Projectile> projectiles;
    private List<Barrier> barriers;
    private List<GameObject> gameObjects;

    //player lives and score
    private int score;
    private int lives;

    //variables for handling the creation and moving of aliens
    private final int alienSize = 40;
    private final int alienOffset = 20;
    private final int numberOfAlienRows = 5;
    private final int fieldBottom = 600;
    private int alienTopRowPos = 100;

    //number of barriers
    private final int numberOfBarriers = 4;

    //logic surrounding timer for player shooting
    private int timeBetweenPlayerShots = 150;
    private int timeSinceLastPlayerShot = 8000;

    //logic for handling alien's move timer and what row to move
    private int timeBetweenMove = 40;
    private int currentTime = 0;
    private int alienRowToMove = -1;

    //variables for when aliens hits the wall or the floor of their field
    private boolean hitBound = false;
    private boolean atBottom = false;


    public SpaceInvadersModel() {
        //create player, aliens and projectile
        player = new Spaceship(100, 800, 40, 20, 2);
        aliens = new ArrayList<>();
        projectiles = new ArrayList<>();
        barriers = new ArrayList<>();
        score = 0;
        lives = 3;

        //populate list of aliens
        int numberOfAlienCols = 10;
        for (int i = 0; i < numberOfAlienCols; i++) {
            for (int j = 0; j < numberOfAlienRows; j++) {
                aliens.add(new Alien(boundOffset + (alienSize+alienSize)*i, alienTopRowPos + (alienSize+alienOffset)*j, alienSize, 20));
            }
        }

        //create barriers
        for (int i = 0; i < numberOfBarriers; i++) {
            barriers.add(new Barrier(boundOffset + 200 * i, fieldBottom + 50, 50, 50));
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

        //check all collisions
        checkCollisions();

        //increase time since last player shoot'
        timeSinceLastPlayerShot++;
    }

    private void moveEntities(){
        //player moving left with check for left bound
        if (player.getDirection() == -1 && player.getXpos() >= boundOffset){
            player.move();
        }
        //player moving right with check for right bound
        else if (player.getDirection() == 1 && player.getXpos()+player.getWidth() <= windowSizeX-boundOffset){
            player.move();
        }

        //check if any alien is at the bottom of the field
        atBottom = false;
        for (int i = 0; i < aliens.size(); i++) {
            if (aliens.get(i).getYpos() + aliens.get(i).getHeight() >= fieldBottom){
                atBottom = true;
                break;
            }
        }

        //move aliens downwards and reset which row to move
        if (alienRowToMove < 0){
            alienRowToMove = numberOfAlienRows - 1;

            //when aliens hit bound then move down as long as they are not on bottom
            if(hitBound){
                for (int i = 0; i < aliens.size(); i++) {
                    if (!atBottom){
                        aliens.get(i).moveDown();
                    }
                    aliens.get(i).setDirection(aliens.get(i).getDirection() * -1);
                }
                if (!atBottom){
                    alienTopRowPos += alienSize + alienOffset;
                }
                hitBound = false;
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
        //get closer to moving aliens
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
                hitBound = true;
                break;
            }
            else if(aliens.get(i).getDirection() == -1 && aliens.get(i).getXpos() <= boundOffset){
                hitBound = true;
                break;
            }
        }
    }

    private void aliensShoot(){
        //all aliens have an internal shoot timer if its time for an alien to shoot it will do so
        // else the timer increase and get closer to shooting
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
    
    private void checkCollisions(){
        //loop through projectile list and check if projectile either collides with anything or is out of bounds,
        // if true then remove it and do action depending on what it hit
        for (int i = 0; i < projectiles.size(); i++) {
            //bool for knowing if projectile has hit something
            boolean hit = false;
            //check enemy projectiles
            if (projectiles.get(i).getDirection() == 1){
                //checks collisions with the player, player looses lives or dies when hit
                if (projectiles.get(i).getHitbox().intersect(player.getHitbox())){
                    damagePlayer();
                    projectiles.remove(projectiles.get(i));
                    i--;
                    hit = true;
                    continue;
                }
                //checks collisions with barriers, barriers looses lives or gets destroyed when hit
                for (Barrier barrier: barriers) {
                    if (projectiles.get(i).getHitbox().intersect(barrier.getHitbox())){
                        damageBarrier(barrier);
                        projectiles.remove(projectiles.get(i));
                        i--;
                        hit = true;
                        break;
                    }
                }
            }
            //check player projectiles
            else if (projectiles.get(i).getDirection() == -1){
                //checks collisions with barriers, barrier do not get effected by player projectiles and will therefore
                //not loose lives/get destroyed when hit by one but projectile is destroyed
                for (Barrier barrier: barriers) {
                    if (projectiles.get(i).getHitbox().intersect(barrier.getHitbox())){
                        projectiles.remove(projectiles.get(i));
                        i--;
                        hit = true;
                        break;
                    }
                }
                //if a projectile has hit something it has been removed and we can
                // therefore not check more collisions so we continue to next projectile in list
                if (hit){
                    continue;
                }
                //check collisions with aliens if hit they will be destroyed and player will receive score
                for (Alien alien: aliens) {
                    if (projectiles.get(i).getHitbox().intersect(alien.getHitbox())){
                        projectiles.remove(projectiles.get(i));
                        i--;
                        aliens.remove(alien);
                        score += 100;
                        System.out.println("Score is now: " + score);
                        hit = true;
                        break;
                    }
                }
            }
            //if a projectile hasn't hit anything and is out of bounds it is destroyed
            if (!hit && (projectiles.get(i).getYpos() < 0 || projectiles.get(i).getYpos() > windowSizeY)){
                projectiles.remove(projectiles.get(i));
                i--;
            }
        }
    }

    private void damageBarrier(Barrier barrier){
        //barrier is destroyed if health is at 0 else barrier looses 1 health
        if (barrier.getHealth() <= 0){
            barriers.remove(barrier);
        }
        else {
            barrier.decreaseHealth();
        }
    }

    private void damagePlayer(){
        // player looses 1 health if it has above 0 lives else looses the game
        if (lives > 0){
            lives--;
        }
        else{
            //TODO Game Over
        }
    }

    public void playerShoot(){
        //if the timer for player shooting is above time between shots the player will shoot a projectile
        if (timeSinceLastPlayerShot >= timeBetweenPlayerShots){
            projectiles.add(new Projectile(player.getXpos(), player.getYpos(), -1));
            timeSinceLastPlayerShot = 0;
        }
    }

    public void setPlayerDirection(int direction){
        //set direction of the player (-1, 0, 1)(left, stay, right)
        player.setDirection(direction);
    }

    public int getWindowSizeX() {
        //return size x of playing field
        return windowSizeX;
    }

    public int getWindowSizeY() {
        //return size y of playing field
        return windowSizeY;
    }

    public List<GameObject> getGameObjects(){
        gameObjects = new ArrayList<>();

        //add player
        gameObjects.add(player);
        gameObjects.get(0);

        //add aliens
        for (int i = 0; i < aliens.size(); i++) {
            gameObjects.add(aliens.get(i));
        }

        //add projectiles
        for (int i = 0; i < projectiles.size(); i++) {
            gameObjects.add(projectiles.get(i));
        }

        //add barriers
        for (int i = 0; i < barriers.size(); i++) {
            gameObjects.add(barriers.get(i));
        }

        return gameObjects;
    }
}
