package edu.chalmers.projecttemplate.model.spaceInvadersModel;

import java.util.ArrayList;
import java.util.List;

public class SpaceInvadersModel {

    private Spaceship player;
    private List<Alien> aliens;
    private List<Projectile> projectiles;

    public SpaceInvadersModel() {
        player = new Spaceship(100, 100, 40, 20, 10);
        aliens = new ArrayList<>();
        projectiles = new ArrayList<>();
    }

    public void moveEntities(){
        player.move();
        for (int i = 0; i < aliens.size(); i++) {
            aliens.get(i).move();
        }
        for (int i = 0; i < projectiles.size(); i++) {
            projectiles.get(i).move();
        }
    }

    public void setPlayerDirection(int direction){
        player.setDirection(direction);
    }

    public void aliensShoot(){

    }

    public void playerShoot(){

    }

    public MovableObject getPlayer(){
        return player;
    }



}
