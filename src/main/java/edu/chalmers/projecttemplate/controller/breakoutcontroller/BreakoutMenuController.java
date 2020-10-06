package edu.chalmers.projecttemplate.controller.breakoutcontroller;

import edu.chalmers.projecttemplate.model.breakoutmodel.BreakoutMenuModel;
import edu.chalmers.projecttemplate.view.breakoutview.BreakoutGameView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BreakoutMenuController implements Initializable {
    @FXML public Button btnPlay;
    @FXML public Button btnScores;
    @FXML public Button btnHelp;
    @FXML public Button btnExit;
    @FXML public AnchorPane subscenePlay;
    @FXML public AnchorPane subsceneScores;
    @FXML public AnchorPane subsceneHelp;
    @FXML public Button btnStart;
    @FXML public AnchorPane mainPane;

    BreakoutMenuModel menuModel;
    //constructor
    public BreakoutMenuController() {}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            menuModel = new BreakoutMenuModel(mainPane, subscenePlay, subsceneScores, subsceneHelp, btnPlay, btnScores, btnHelp, btnExit);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Button start **breakoutButtonModel.setButtonFreeStyle(btnStart)**;
    public void btnStartControl(ActionEvent actionEvent) throws IOException {
        BreakoutGameView newGame = new BreakoutGameView();
        Scene gameScene = newGame.getGameScene();
        Stage gameStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        gameStage.setScene(gameScene);
        gameStage.show();
    }

}
