package edu.chalmers.projecttemplate.model;

import edu.chalmers.projecttemplate.model.spaceInvadersModel.GameObject;
import edu.chalmers.projecttemplate.model.spaceInvadersModel.SpaceInvadersModel;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SpaceInvadersTest {
    // SPACE INVADERS TESTS
    @Test
    public void moveSpaceshipTest(){
        SpaceInvadersModel model = new SpaceInvadersModel();
        GameObject player = model.getGameObjects().get(0);
        int playerSpeed = 2;

        //test for moving right (direction 1)
        int newpos1 = player.getXpos() + playerSpeed;
        model.setPlayerDirection(1);
        model.update();
        Assert.assertTrue(player.getXpos() == newpos1);

        //test for moving left (direction -1)
        int newpos2 = player.getXpos() - playerSpeed;
        model.setPlayerDirection(-1);
        model.update();
        Assert.assertTrue(player.getXpos() == newpos2);

        //test for standing still (direction 0)
        int newpos3 = player.getXpos();
        model.setPlayerDirection(0);
        model.update();
        Assert.assertTrue(player.getXpos() == newpos3);
    }
    @Test
    public void moveAliensTest(){
        SpaceInvadersModel model = new SpaceInvadersModel();
        List<GameObject> aliens = new ArrayList<>();
        for (GameObject go:model.getGameObjects()) {
            if (go.getType() == "Alien"){
                aliens.add(go);
            }
        }

        List<Integer> alienPosList = new ArrayList<>();
        for (GameObject mo:aliens) {
            alienPosList.add(mo.getXpos());
        }

        for (int i = 0; i < 220; i++) {
            model.update();
        }
        boolean allMoved = true;
        for (int i = 0; i < aliens.size(); i++) {
            if (aliens.get(i).getXpos() != alienPosList.get(i)+20){
                allMoved = false;
            }
        }
        Assert.assertTrue(allMoved);

    }
    @Test
    public void playerShootTest(){
        SpaceInvadersModel model = new SpaceInvadersModel();
        boolean foundProjectile = false;

        model.playerShoot();

        for (GameObject go: model.getGameObjects()) {
            if (go.getType() == "Projectile"){
                foundProjectile = true;
            }
        }

        Assert.assertTrue(foundProjectile);
    }
    @Test
    public void AlienShootTest(){
        SpaceInvadersModel model = new SpaceInvadersModel();
        boolean foundProjectile = false;

        for (int i = 0; i < 200000; i++) {
            model.update();
            for (GameObject go: model.getGameObjects()) {
                if (go.getType() == "Projectile"){
                    foundProjectile = true;
                }
            }
        }

        Assert.assertTrue(foundProjectile);
    }
}
