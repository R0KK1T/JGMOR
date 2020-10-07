package edu.chalmers.projecttemplate.model;

import edu.chalmers.projecttemplate.model.breakoutmodel.Brick;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BreakoutTest {
    private static Brick brick;
    @Before
    public void before() {
        brick = new Brick(10, 20, 30, 30, 4);
    }
    @After
    public void after() {
        brick = new Brick(10, 20, 30, 30, 4);
    }
    @Test
    public void getBrickPointTest() {
        assertTrue("Brick should hold 4 points: ",brick.getBrickPoint() == 4);
    }
    @Test
    public void getBrickHitTest() {
        assertTrue("Brick should contain 4 hits: ",brick.getBrickHit() == 4);
    }
    @Test
    public void getBrickStatusTest() {
        assertEquals("isActive should be true: ", brick.getBrickStatus(), true);
    }
    @Test
    public void setBrickHitTest() {
        brick.setBrickHit();
        assertTrue("Brick should have 3 hits left: ",brick.getBrickHit() == 3);
        assertEquals("isActive should be true: ", brick.getBrickStatus(), true);
    }
    @Test
    public void setBrickStatusTest() {
        // First hit
        assertTrue("Brick should contain 4 hits: ",brick.getBrickHit() == 4);
        brick.setBrickHit();
        brick.setBrickStatus();
        assertEquals("isActive should be true: ", brick.getBrickStatus(), true);

        // Second hit
        assertTrue("Brick should have 3 hits left: ",brick.getBrickHit() == 3);
        brick.setBrickHit();
        brick.setBrickStatus();
        assertEquals("isActive should be true: ", brick.getBrickStatus(), true);

        // Third hit
        assertTrue("Brick should have 2 hits left: ",brick.getBrickHit() == 2);
        brick.setBrickHit();
        brick.setBrickStatus();
        assertEquals("isActive should be true: ", brick.getBrickStatus(), true);

        // Fourth
        assertTrue("Brick should have 1 hit left: ",brick.getBrickHit() == 1);
        brick.setBrickHit();
        brick.setBrickStatus();
        assertEquals("isActive should now be false: ", brick.getBrickStatus(), false);
    }
}
