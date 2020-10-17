package edu.chalmers.projecttemplate.model;

import edu.chalmers.projecttemplate.model.froggermodel.*;
import edu.chalmers.projecttemplate.model.common.Rectangle;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class FroggerTest {
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
        LaneFactory factory = new LaneFactory(10, 10,3, 10);

        //Roadlane
        Lane roadLane = factory.createRoadLane(3, 10);

        Assert.assertTrue(!roadLane.isRiver());

        for (int i = 0; i < roadLane.getObstacles().size(); i++) {
            Assert.assertTrue(roadLane.getObstacles().get(i).getVelocity()!=0);
            Assert.assertTrue(roadLane.getObstacles().get(i).getType() == ObstacleType.CAR);
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
        Assert.assertTrue(riverLane.isRiver());
        for (int i = 0; i < riverLane.getObstacles().size(); i++) {
            Assert.assertTrue(riverLane.getObstacles().get(i).getWidth()==30);
            Assert.assertTrue(riverLane.getObstacles().get(i).getType() == ObstacleType.LOG);
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
        Assert.assertTrue(finishLine.getObstacles().size() == 4);
        for (int i = 0; i < finishLine.getObstacles().size(); i++) {
            Assert.assertTrue(finishLine.getObstacles().get(i).getVelocity() == 0);
            Assert.assertTrue(finishLine.getObstacles().get(i).getType() == ObstacleType.GRASS);
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
        FroggerModel model = new FroggerModel();
        int amountOfRoads = 0;
        int amountOfRivers = 0;
        int amountOfEmpty = 0;
        int amountOfFinish = 0;
        //Correct number of lanes
        Assert.assertTrue(model.getRows() == model.getLanes().size());
        //Correct number of each lane type
        for (int i = 0; i < model.getLanes().size(); i++) {
            if(model.getLanes().get(i).isRiver()){
                amountOfRivers++;
            }
            else if(model.getLanes().get(i).getObstacles().size() == 0){
                amountOfEmpty++;
            }
            else if(model.getLanes().get(i).getObstacles().get(0).getVelocity() != 0){
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
        for (int i = 1; i < model.getLanes().size()+1; i++) {
            Assert.assertTrue(model.getLanes().get(i-1).getY() == (model.getWindowSizeY()-(model.getSquareDimension()*i )));
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
    public void frogAttachAndDetach(){
        Frog frog = new Frog(25, 25, 25,25, 25);
        Obstacle obs = new Obstacle(25, 25, 75, 25, 5, ObstacleType.LOG);

        frog.attach(obs);
        obs.move();
        frog.update();

        Assert.assertTrue(frog.getX() == obs.getX() && frog.getX() == 30);

        frog.moveUp();
        //Detach is called via frog.update()
        frog.update();
        obs.move();

        Assert.assertTrue(frog.getX() != obs.getX());
    }
    @Test
    public void collisionDetection(){
        FroggerModel model = new FroggerModel();
        //Move frog all the way to the left
        for (int i = 0; i < model.getColumns()/2; i++) {
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
        Assert.assertTrue(model.getPlayer().getX()==(model.getSquareDimension()*(model.getColumns()/2)) && model.getPlayer().getY()==model.getWindowSizeY()-model.getSquareDimension());

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
        Assert.assertTrue(model.getPlayer().getX()==(model.getSquareDimension()*(model.getColumns()/2)) && model.getPlayer().getY()==model.getWindowSizeY()-model.getSquareDimension());

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
        ArrayList<IPositionable> poss = model.getPositionables();
        for (int i = 0; i < model.getLifeCount(); i++) {
            //Move frog all the way to the left
            for (int j = 0; j < model.getColumns()/2; j++) {
                model.getPlayer().moveLeft();
            }
            //Move frog one step up
            model.getPlayer().moveUp();
            //Move frog to right until it intersects with an obstacle
            while(model.collisionDetected(model.getCurrentPlayerLane()) == null){
                model.getPlayer().moveRight();
            }
            model.update();
        }

        Assert.assertTrue(model.getCurrentLifeCount() == model.getLifeCount());
        Assert.assertTrue(poss != model.getPositionables());
    }
    @Test
    public void moveFrogViaController(){
        FroggerModel model = new FroggerModel();
        int x = model.getPlayer().getX();
        int y = model.getPlayer().getY();
        model.movePlayer(1);
        Assert.assertTrue(model.getPlayer().getY() == y - model.getSquareDimension());
        model.movePlayer(2);
        Assert.assertTrue(model.getPlayer().getY() == y);
        model.movePlayer(3);
        Assert.assertTrue(model.getPlayer().getX() == x + model.getSquareDimension());
        model.movePlayer(4);
        Assert.assertTrue(model.getPlayer().getX() == x);
    }
    @Test
    public void madeItToTheFinishLine(){
        FroggerModel model = new FroggerModel();
        model.movePlayer(3);
        while(model.getCurrentPlayerLane() != model.getLanes().get(model.getLanes().size() - 1)){
            model.movePlayer(1);
        }
        int x = model.getPlayer().getX();
        int y = model.getPlayer().getY();
        ArrayList<IPositionable> poss = model.getPositionables();
        model.update();
        Assert.assertTrue(model.getPlayer().getX() != x && model.getPlayer().getY() != y);
        Assert.assertTrue(poss.size() != model.getPositionables().size());
    }
}
