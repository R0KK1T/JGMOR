package edu.chalmers.projecttemplate.controller.breakoutcontroller;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class BreakoutMenuController  {
    @FXML
    public Button btnPlay;
    @FXML
    public Button btnScores;
    @FXML
    public Button btnHelp;
    @FXML
    public Button btnExit;
    //constructor
    public BreakoutMenuController() { }
    public void initialize() {
        // handle data once the fields are injected
        controlPlay();
    }
    //1. Button play
    private void controlPlay() {
        btnPlay.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });

    }

}
