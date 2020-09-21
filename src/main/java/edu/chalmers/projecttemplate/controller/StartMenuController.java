package edu.chalmers.projecttemplate.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class StartMenuController implements Initializable {
    @FXML
    private Label title;
    @FXML
    private Button quitBtn;
    @FXML
    private Button optionsBtn;
    @FXML
    private Button selectGameBtn;
    @FXML
    private AnchorPane anchorBack;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void quitGame(){
        System.exit(1);
    }
}
