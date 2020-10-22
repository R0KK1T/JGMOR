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
    public void alienShootTest(){
        SpaceInvadersModel model = new SpaceInvadersModel();
        Boolean notFound = true;

        while(notFound){
            model.update();
            for (IRepresentable go: model.getRepresents()) {
                if (go.getType() == "Projectile"){
                    Assert.assertTrue(true);
                    notFound = false;
                    break;
                }
            }
        }
    }

    @Test
    public void gameOverTest(){
        SpaceInvadersModel model = new SpaceInvadersModel();

        while(true){
            model.update();
            if (model.getGameOver()){
                Assert.assertTrue(true);
                break;
            }
        }
    }

    @Test
    public void damagePlayerTest(){
        SpaceInvadersModel model = new SpaceInvadersModel();
        int startingLives = model.getLives();

        while(true){
            model.update();
            if (model.getLives() < startingLives){
                Assert.assertTrue(true);
                break;
            }
        }
    }

    @Test
    public void killAlienTest(){
        SpaceInvadersModel model = new SpaceInvadersModel();
        int numberOfAliensStart = 0;
        int numberOfAliensCurrent = 0;

        for (IRepresentable go: model.getRepresents()) {
            if (go.getType() == "Alien"){
                numberOfAliensStart++;
            }
        }

        while(true){
            numberOfAliensCurrent = 0;
            for (IRepresentable go: model.getRepresents()) {
                if (go.getType() == "Alien"){
                    numberOfAliensCurrent++;
                }
            }

            model.update();
            model.playerShoot();

            if (numberOfAliensCurrent < numberOfAliensStart){
                Assert.assertTrue(true);
                break;
            }
        }
    }
}
