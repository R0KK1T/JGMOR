package edu.chalmers.projecttemplate.model;

import edu.chalmers.projecttemplate.model.froggermodel.Frog;
import edu.chalmers.projecttemplate.model.froggermodel.Rectangle;
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
}
