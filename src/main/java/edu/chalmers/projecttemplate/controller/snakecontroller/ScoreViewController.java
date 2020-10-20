package edu.chalmers.projecttemplate.controller.snakecontroller;

import edu.chalmers.projecttemplate.model.snakemodel.GameScene;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ScoreViewController extends GoBack implements Initializable {
    @FXML
    private Button backBtn;

    @FXML
    private Label lblScores;

    private int oldScore = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(oldScore < GameScene.score) {
            oldScore = GameScene.score;
            lblScores.setText(GameScene.score + "");
        }
        else {
            lblScores.setText(oldScore + "");
        }

    }
}
