package edu.chalmers.projecttemplate.controller.spaceInvadersController;

import edu.chalmers.projecttemplate.model.spaceInvadersModel.SpaceInvadersModel;
import edu.chalmers.projecttemplate.view.spaceInvadersView.SpaceInvadersView;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;

public class SpaceInvadersController {
    SpaceInvadersModel model;
    SpaceInvadersView view;
    AnimationTimer timer;

    public SpaceInvadersController() throws Exception {
        model = new SpaceInvadersModel();
        view = new SpaceInvadersView(model.getSizeX(), model.getSizeY());

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                model.update();
                callForRedraw();
            }
        };
    }

    private void callForRedraw(){
        //clear
        view.clearDrawingArea();

        //draw Player
        view.draw(model.getPlayer().getXpos(), model.getPlayer().getYpos(),
                model.getPlayer().getWidth(), model.getPlayer().getHeight(), model.getPlayer().getType());

        //draw Aliens
        for (int i = 0; i < model.getAliens().size(); i++) {
            view.draw(model.getAliens().get(i).getXpos(), model.getAliens().get(i).getYpos(),
                    model.getAliens().get(i).getWidth(), model.getAliens().get(i).getHeight(), model.getAliens().get(i).getType());
        }
    }

    public void startGame(){
        timer.start();
    }

    public Scene getScene(){
        return view.getScene();
    }
}
