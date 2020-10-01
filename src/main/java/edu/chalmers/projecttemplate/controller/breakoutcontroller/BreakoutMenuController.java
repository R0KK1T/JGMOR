package edu.chalmers.projecttemplate.controller.breakoutcontroller;

import edu.chalmers.projecttemplate.model.breakoutmodel.BreakoutButtonModel;
import edu.chalmers.projecttemplate.model.breakoutmodel.BreakoutSubsceneModel;
import edu.chalmers.projecttemplate.view.breakoutview.BreakoutGameView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BreakoutMenuController  {
    @FXML public Button btnPlay;
    @FXML public Button btnScores;
    @FXML public Button btnHelp;
    @FXML public Button btnExit;
    @FXML public AnchorPane subscenePlay;
    @FXML public AnchorPane subsceneScores;
    @FXML public AnchorPane subsceneHelp;
    @FXML public Button btnStart;
    private AnchorPane subsceneToHide;
    BreakoutButtonModel breakoutButtonModel;
    BreakoutSubsceneModel breakoutSubsceneModel;
    List<Button> buttonList;
    //constructor
    public BreakoutMenuController() { }
    public void initialize() throws IOException {
        // handle data once the fields are injected
        controlBtnPlay();
        controlBtnScores();
        controlBtnHelp();
        controlBtnExit();
        breakoutButtonModel = new BreakoutButtonModel();
        breakoutSubsceneModel = new BreakoutSubsceneModel();
        buttonList = new ArrayList<>();
        addToButtoList();
        buttonsSettings();
        menuSubsceneSettings();
    }
    //1. Button play
    private void controlBtnPlay() {
        btnPlay.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                showSubscene(subscenePlay);
            }
        });
    }
    //2. Button score
    private void controlBtnScores() {
        btnScores.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                showSubscene(subsceneScores);
            }
        });
    }
    //3. Button score
    private void controlBtnHelp() {
        btnHelp.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                showSubscene(subsceneHelp);
            }
        });
    }
    //4. Button exit
    private void controlBtnExit() {
        btnExit.setOnMouseClicked(event -> System.exit(0));

    }
    //5. Button start
    public void btnStartControl(ActionEvent actionEvent) throws IOException {
        BreakoutGameView newGame = new BreakoutGameView();
        Scene gameScene = newGame.getGameScene();
        Stage gameStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        gameStage.setScene(gameScene);
        gameStage.show();
    }

    /** =================== Settings ================== **/
    private void addToButtoList() {
        buttonList.add(btnPlay);
        buttonList.add(btnScores);
        buttonList.add(btnHelp);
        buttonList.add(btnExit);
    }
    private void buttonsSettings() {
        //Add font, set cursor
        for (int i=0; i<4; i++) {
            breakoutButtonModel.setButtonFront(buttonList.get(i));
            breakoutButtonModel.setCursor(buttonList.get(i));
        }
    }
    private void menuSubsceneSettings() throws IOException {
        //Add background-image grey_panel
        breakoutSubsceneModel.subsceneBackgroundImage(subscenePlay);
        breakoutSubsceneModel.subsceneBackgroundImage(subsceneScores);
        breakoutSubsceneModel.subsceneBackgroundImage(subsceneHelp);
    }
    private void showSubscene(AnchorPane thePane) {
        if (subsceneToHide != null) {
            breakoutSubsceneModel.moveSubsceneRightToLeft(subsceneToHide);
        }
        breakoutSubsceneModel.moveSubsceneRightToLeft(thePane);
        subsceneToHide = thePane;
    }

}
