package edu.chalmers.projecttemplate.model;

import edu.chalmers.projecttemplate.model.froggermodel.Frog;
import edu.chalmers.projecttemplate.model.froggermodel.Rectangle;
import edu.chalmers.projecttemplate.model.spaceInvadersModel.MovableObject;
import edu.chalmers.projecttemplate.model.spaceInvadersModel.SpaceInvadersModel;
import edu.chalmers.projecttemplate.model.spaceInvadersModel.Spaceship;
import org.junit.Assert;
import org.junit.Test;

public class ProjectTest {
    @Test
    public void intersectTest(){
        Rectangle rect1 = new Rectangle(10,10,10,10);
        Rectangle rect2 = new Rectangle(10,20,10,10);
        Assert.assertFalse(rect1.intersect(rect2));
        Rectangle rect3 = new Rectangle(10,10,10,10);
        Rectangle rect4 = new Rectangle(15,10,10,10);
        Assert.assertTrue(rect3.intersect(rect4));
    }
    @Test
    public void moveFrog(){
        Frog frog = new Frog(10, 10, 10, 10);
        frog.moveRight();
        Assert.assertTrue(frog.getHitbox().getX() == 11);
        frog.moveUp();
        Assert.assertTrue(frog.getHitbox().getY() == 9);
        frog.moveLeft();
        Assert.assertTrue(frog.getHitbox().getX() == 10);
        frog.moveDown();
        Assert.assertTrue(frog.getHitbox().getY() == 10);
    }

    // SPACE INVADERS TESTS
    @Test
    public void moveSpaceshipTest(){
        SpaceInvadersModel model = new SpaceInvadersModel();
        MovableObject player = model.getPlayer();

        //test for moving right (direction 1)
        int newpos1 = player.getXpos() + player.getSpeed();
        model.setPlayerDirection(1);
        model.moveEntities();
        Assert.assertTrue(player.getXpos() == newpos1);

        //test for moving left (direction -1)
        int newpos2 = player.getXpos() - player.getSpeed();
        model.setPlayerDirection(-1);
        model.moveEntities();
        Assert.assertTrue(player.getXpos() == newpos2);

        //test for standing still (direction 0)
        int newpos3 = player.getXpos();
        model.setPlayerDirection(0);
        model.moveEntities();
        Assert.assertTrue(player.getXpos() == newpos3);

    }
}
