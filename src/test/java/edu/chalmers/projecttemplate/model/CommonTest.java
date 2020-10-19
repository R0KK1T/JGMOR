package edu.chalmers.projecttemplate.model;

import edu.chalmers.projecttemplate.model.common.IRectangle;
import edu.chalmers.projecttemplate.model.common.Rectangle;
import org.junit.Assert;
import org.junit.Test;

public class CommonTest {
    @Test
    public void intersectTest(){
        IRectangle rect1 = new Rectangle(10,10,10,10);
        IRectangle rect2 = new Rectangle(10,20,10,10);
        Assert.assertFalse(rect1.intersect(rect2));
        IRectangle rect3 = new Rectangle(10,10,10,10);
        IRectangle rect4 = new Rectangle(15,10,10,10);
        Assert.assertTrue(rect3.intersect(rect4));
        IRectangle rect5 = new Rectangle(10,10,100,100);
        IRectangle rect6 = new Rectangle(97,50,10,10);
        Assert.assertTrue(rect5.intersect(rect6));
    }
}
