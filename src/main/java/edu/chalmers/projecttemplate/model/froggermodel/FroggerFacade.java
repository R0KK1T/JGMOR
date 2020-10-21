package edu.chalmers.projecttemplate.model.froggermodel;

import edu.chalmers.projecttemplate.model.common.IRepresentable;

import java.util.ArrayList;

public class FroggerFacade {
    private FroggerModel model;
    public FroggerFacade(){
        model = new FroggerModel();
    }
    public void update(){
        model.update();
    }
    public void movePlayer(int direction){
        model.movePlayer(direction);
    }
    public ArrayList<IRepresentable> getRepresents(){
        return model.getRepresents();
    }
    public int getPoints(){
        return model.getPoints();
    }
    public int getLevel(){
        return model.getLevel();
    }
    public int getCurrentLifeCount(){
        return model.getCurrentLifeCount();
    }
}
