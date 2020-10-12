package edu.chalmers.projecttemplate.model;

import edu.chalmers.projecttemplate.model.spaceInvadersModel.IPositionable;
import edu.chalmers.projecttemplate.model.spaceInvadersModel.SpaceInvadersModel;
import org.junit.Assert;
import org.junit.Test;

public class SpaceInvadersTest {
    // SPACE INVADERS TESTS
    @Test
    public void moveSpaceshipTest(){
        SpaceInvadersModel model = new SpaceInvadersModel();
        IPositionable player = model.getPositionables().get(0);
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

    }
}
