package edu.chalmers.projecttemplate.model;

import edu.chalmers.projecttemplate.model.common.IRepresentable;
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
        IRepresentable player = model.getRepresents().get(0);
        int playerSpeed = 2;

        //test for moving right (direction 1)
        int newpos1 = player.getX() + playerSpeed;
        model.setPlayerDirection(1);
        model.update();
        Assert.assertTrue(player.getX() == newpos1);

        //test for moving left (direction -1)
        int newpos2 = player.getX() - playerSpeed;
        model.setPlayerDirection(-1);
        model.update();
        Assert.assertTrue(player.getX() == newpos2);

        //test for standing still (direction 0)
        int newpos3 = player.getX();
        model.setPlayerDirection(0);
        model.update();
        Assert.assertTrue(player.getX() == newpos3);
    }
    @Test
    public void moveAliensTest(){
        SpaceInvadersModel model = new SpaceInvadersModel();
        List<IRepresentable> aliens = new ArrayList<>();
        for (IRepresentable go:model.getRepresents()) {
            if (go.getType() == "Alien"){
                aliens.add(go);
            }
        }

        List<Integer> alienPosList = new ArrayList<>();
        for (IRepresentable mo:aliens) {
            alienPosList.add(mo.getX());
        }

        for (int i = 0; i < 220; i++) {
            model.update();
        }
        boolean allMoved = true;
        for (int i = 0; i < aliens.size(); i++) {
            if (aliens.get(i).getX() != alienPosList.get(i)+20){
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

        for (IRepresentable go: model.getRepresents()) {
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
            for (IRepresentable go: model.getRepresents()) {
                if (go.getType() == "Projectile"){
                    foundProjectile = true;
                }
            }
        }

        Assert.assertTrue(foundProjectile);
    }
}
