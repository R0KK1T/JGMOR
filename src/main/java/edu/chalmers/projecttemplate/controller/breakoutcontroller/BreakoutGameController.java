package edu.chalmers.projecttemplate.controller.breakoutcontroller;

import edu.chalmers.projecttemplate.view.breakoutview.BreakoutViewManager;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class BreakoutGameController {
    public ImageView btnGameExit;
    //constructor
    public BreakoutGameController() {}

    public void btnExitGameControl(MouseEvent mouseEvent) throws IOException {
        BreakoutViewManager newMenu = new BreakoutViewManager();
        Scene gameScene = newMenu.getMainScene();
        Stage gameStage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        gameStage.setScene(gameScene);
        gameStage.show();
    }
}
