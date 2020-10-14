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

    private int score;
    private int lives;

    private int alienSize = 40;
    private int alienOffset = 20;
    private int numberOfAlienCols = 10;
    private int numberOfAlienRows = 5;
    private int alienTopRowPos = 100;
    private int fieldBottom = 600;

    private int numberOfBarriers = 4;

    private int timeBetweenPlayerShots = 150;
    private int timeSinceLastPlayerShot = 10000;

    private int timeBetweenMove = 5; //40
    private int currentTime = 0;
    private int alienRowToMove = -1;

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
        for (Projectile projectile: projectiles) {
            //enemy projectiles
            if (projectile.getDirection() == 1){
                //checks collisions with the player
                if (projectile.getHitbox().intersect(player.getHitbox())){
                    damagePlayer();
                    projectiles.remove(projectile);
                    System.out.println("player died");
                    continue;
                }
                //checks collisions with barriers
                for (Barrier barrier: barriers) {
                    if (projectile.getHitbox().intersect(barrier.getHitbox())){
                        damageBarrier(barrier);
                        projectiles.remove(projectile);
                        break;
                    }
                }
            }
            //player projectiles
            else if (projectile.getDirection() == -1){
                for (Barrier barrier: barriers) {
                    if (projectile.getHitbox().intersect(barrier.getHitbox())){
                        projectiles.remove(projectile);
                        break;
                    }
                }
                for (Alien alien: aliens) {
                    if (projectile.getHitbox().intersect(alien.getHitbox())){
                        projectiles.remove(projectile);
                        aliens.remove(alien);
                        score += 100;
                        System.out.println("Score is now: " + score);
                        break;
                    }
                }
            }
        }
    }

    private void damageBarrier(Barrier barrier){
        if (barrier.getHealth() <= 0){
            barriers.remove(barrier);
        }
        else {
            barrier.decreaseHealth();
        }
    }

    private void damagePlayer(){
        if (lives > 0){
            lives--;
        }
        else{
            //TODO Game Over
        }
    }

    public void playerShoot(){
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
