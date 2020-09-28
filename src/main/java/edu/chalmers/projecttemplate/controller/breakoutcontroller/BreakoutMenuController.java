package edu.chalmers.projecttemplate.controller.breakoutcontroller;


import edu.chalmers.projecttemplate.model.breakoutmodel.BreakoutButtonModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class BreakoutMenuController extends BreakoutButtonModel  {
    @FXML
    public Button btnPlay;
    @FXML
    public Button btnScores;
    @FXML
    public Button btnHelp;
    @FXML
    public Button btnExit;
    BreakoutButtonModel breakoutButtonModel;
    List<Button> buttonList;
    //constructor
    public BreakoutMenuController() { }
    public void initialize() {
        // handle data once the fields are injected
        controlPlay();
        controlScores();
        controlHelp();
        controlExit();
        breakoutButtonModel = new BreakoutButtonModel();
        buttonList = new ArrayList<>();
        addToButtoList();
        buttonsettings();
    }
    //1. Button play
    private void controlPlay() {
        btnPlay.setOnMouseClicked(event ->
                System.out.println("Play"));

    }
    //2. Button score
    private void controlScores() {
        btnScores.setOnMouseClicked(event ->
                System.out.println("Scores"));
    }
    //3. Button score
    private void controlHelp() {
        btnHelp.setOnMouseClicked(event ->
                System.out.println("Help"));
    }
    //4. Button exit
    private void controlExit() {
        btnExit.setOnMouseClicked(event -> System.exit(0));

    }
    //Settings
    private void addToButtoList() {
        buttonList.add(btnPlay);
        buttonList.add(btnScores);
        buttonList.add(btnHelp);
        buttonList.add(btnExit);
    }
    private void buttonsettings() {
        //Add font, set cursor
        for (int i=0; i<4; i++) {
            breakoutButtonModel.setButtonFront(buttonList.get(i));
            breakoutButtonModel.setCursor(buttonList.get(i));
        }
    }


}
