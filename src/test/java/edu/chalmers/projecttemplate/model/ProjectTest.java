package edu.chalmers.projecttemplate.model;

import edu.chalmers.projecttemplate.model.froggermodel.*;
import edu.chalmers.projecttemplate.model.common.Rectangle;
import edu.chalmers.projecttemplate.model.spaceInvadersModel.MovableObject;
import edu.chalmers.projecttemplate.model.spaceInvadersModel.SpaceInvadersModel;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class ProjectTest {
    @Test
    public void intersectTest(){
        Rectangle rect1 = new Rectangle(10,10,10,10);
        Rectangle rect2 = new Rectangle(10,20,10,10);
        Assert.assertFalse(rect1.intersect(rect2));
        Rectangle rect3 = new Rectangle(10,10,10,10);
        Rectangle rect4 = new Rectangle(15,10,10,10);
        Assert.assertTrue(rect3.intersect(rect4));
        Rectangle rect5 = new Rectangle(10,10,100,100);
        Rectangle rect6 = new Rectangle(97,50,10,10);
        Assert.assertTrue(rect5.intersect(rect6));
    }
    @Test
    public void moveFrog(){
        Frog frog = new Frog(10, 10, 10, 10, 1);
        frog.moveRight();
        Assert.assertTrue(frog.getHitbox().getX() == 11);
        frog.moveUp();
        Assert.assertTrue(frog.getHitbox().getY() == 9);
        frog.moveLeft();
        Assert.assertTrue(frog.getHitbox().getX() == 10);
        frog.moveDown();
        Assert.assertTrue(frog.getHitbox().getY() == 10);
    }
    @Test
    public void createLane(){
        LaneFactory factory = new LaneFactory(10, 3, 10);

        //Roadlane
        Lane roadLane = factory.createRoadLane(3, 10);
        Car car = new Car(10,10,10,10,10);

        Assert.assertTrue(!roadLane.isRiver());
        for (int i = 0; i < roadLane.getObstacles().size(); i++) {
            Assert.assertTrue(roadLane.getObstacles().get(i).getClass()==car.getClass());
        }

        for (int i = 0; i < roadLane.getObstacles().size(); i++) {
            for (int j = 0; j < roadLane.getObstacles().size(); j++) {
                if(i != j){
                    Assert.assertFalse(roadLane.getObstacles().get(i).getHitbox().intersect(roadLane.getObstacles().get(j).getHitbox()));
                }
            }
        }


        //Riverlane
        Lane riverLane = factory.createRiverLane(3, 150);
        Log log = new Log(10, 10, 10, 10, 10);
        Assert.assertTrue(riverLane.isRiver());
        for (int i = 0; i < riverLane.getObstacles().size(); i++) {
            Assert.assertTrue(riverLane.getObstacles().get(i).getClass()==log.getClass());
        }

        for (int i = 0; i < riverLane.getObstacles().size(); i++) {
            for (int j = 0; j < riverLane.getObstacles().size(); j++) {
                if(i != j){
                    Assert.assertFalse(riverLane.getObstacles().get(i).getHitbox().intersect(riverLane.getObstacles().get(j).getHitbox()));
                }
            }
        }

        //Empty/safe lane
        Lane safeLane = factory.createEmptyLane(300);
        Assert.assertTrue(safeLane.getObstacles().size() == 0);
        Assert.assertTrue(!safeLane.isRiver());

        //Finish line
        Lane finishLine = factory.createFinishLane(4,450);
        Grass grass = new Grass(10, 10, 10,10,10);
        Assert.assertTrue(finishLine.getObstacles().size() == 4);
        for (int i = 0; i < finishLine.getObstacles().size(); i++) {
            Assert.assertTrue(finishLine.getObstacles().get(i).getClass()==grass.getClass());
        }
        Assert.assertTrue(!finishLine.isRiver());
        for (int i = 0; i < finishLine.getObstacles().size(); i++) {
            for (int j = 0; j < finishLine.getObstacles().size(); j++) {
                if(i != j){
                    Assert.assertFalse(finishLine.getObstacles().get(i).getHitbox().intersect(finishLine.getObstacles().get(j).getHitbox()));
                }
            }
        }
    }
    @Test
    public void initFroggerModel(){
        FroggerModel game = new FroggerModel();
        Car car = new Car(10,10,10,10,10);
        int amountOfRoads = 0;
        int amountOfRivers = 0;
        int amountOfEmpty = 0;
        int amountOfFinish = 0;
        //Correct number of lanes
        Assert.assertTrue(game.getRows() == game.getLanes().size());
        //Correct number of each lane type
        for (int i = 0; i < game.getLanes().size(); i++) {
            if(game.getLanes().get(i).isRiver()){
                amountOfRivers++;
            }
            else if(game.getLanes().get(i).getObstacles().size() == 0){
                amountOfEmpty++;
            }
            else if(game.getLanes().get(i).getObstacles().get(0).getClass() == car.getClass()){
                amountOfRoads++;
            }
            else{
                amountOfFinish++;
            }
        }
        Assert.assertTrue(amountOfRivers == 5);
        Assert.assertTrue(amountOfRoads == 5);
        Assert.assertTrue(amountOfEmpty == 2);
        Assert.assertTrue(amountOfFinish == 1);
        //Correct y-coordinates for all lanes
        for (int i = 1; i < game.getLanes().size()+1; i++) {
            Assert.assertTrue(game.getLanes().get(i-1).getY() == (game.getWindowSizeY()-(game.getSquareDimension()*i )));
        }
    }
    @Test
    public void checkLaneFrog(){
        FroggerModel model = new FroggerModel();
        for (int i = 0; i < model.getRows(); i++) {
            Assert.assertTrue(model.getCurrentPlayerLane()==model.getLanes().get(i));
            model.getPlayer().moveUp();
        }
    }
    @Test
    public void frogAttach(){
        Frog frog = new Frog(25, 25, 25,25, 25);
        Obstacle obs = new Log(25, 25, 25, 25, 5);

        frog.attach(obs);
        obs.move();
        frog.update();

        Assert.assertTrue(frog.getX() == obs.getX() && frog.getX() == 30);
    }
    @Test
    public void collisionDetection(){
        FroggerModel model = new FroggerModel();
        //Move frog all the way to the left
        for (int i = 0; i < 6; i++) {
            model.getPlayer().moveLeft();
        }
        //Move frog one step up
        model.getPlayer().moveUp();
        //Move frog to right until it intersects with an obstacle
        while(model.collisionDetected(model.getCurrentPlayerLane()) == null){
            model.getPlayer().moveRight();
        }
        //Run update to check if it gives the correct consequences from intersection.
        model.update();
        Assert.assertTrue(model.getCurrentLifeCount() == model.getLifeCount() - 1);
        Assert.assertTrue(model.getPlayer().getX()==150 && model.getPlayer().getY()==300);

        //Move frog all the way to the left and up middle safe lane
        for (int i = 0; i < 6; i++) {
            model.getPlayer().moveLeft();
            model.getPlayer().moveUp();
        }
        //One more step to enter first riverLane
        model.getPlayer().moveUp();
        //Move frog to right until it doesn't intersect with an obstacle
        while(model.collisionDetected(model.getCurrentPlayerLane()) != null){
            model.getPlayer().moveRight();
        }
        model.update();
        Assert.assertTrue(model.getCurrentLifeCount() == model.getLifeCount() - 2);
        Assert.assertTrue(model.getPlayer().getX()==150 && model.getPlayer().getY()==300);

        //Move frog all the way to the left and up middle safe lane
        for (int i = 0; i < 6; i++) {
            model.getPlayer().moveLeft();
            model.getPlayer().moveUp();
        }
        //One more step to enter first riverLane
        model.getPlayer().moveUp();

        //Move frog to right until it intersects with an obstacle
        while(model.collisionDetected(model.getCurrentPlayerLane()) == null){
            model.getPlayer().moveRight();
        }
        model.update();
        Assert.assertTrue(model.getPlayer().getRiverObs() == model.collisionDetected(model.getCurrentPlayerLane()));
    }
    @Test
    public void zeroLives(){
        FroggerModel model = new FroggerModel();

        model.getPlayer().moveLeft();
        int x = model.getPlayer().getX();
        ArrayList<Lane> lanes = model.getLanes();
        for (int i = 0; i < model.getLifeCount()-1; i++) {
            model.loseLife();
        }
        Assert.assertTrue(model.getCurrentLifeCount() == 1);
        model.loseLife();
        Assert.assertTrue(model.getCurrentLifeCount() == model.getLifeCount());
        Assert.assertTrue(model.getPlayer().getX() != x);
        Assert.assertTrue(model.getLanes() != lanes);
    }
}
