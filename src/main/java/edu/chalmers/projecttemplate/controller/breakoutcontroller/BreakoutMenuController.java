package edu.chalmers.projecttemplate.controller.breakoutcontroller;

import edu.chalmers.projecttemplate.view.breakoutview.BreakoutGameView;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
/*
 * Controller for breakout game menu
 */
public class BreakoutMenuController implements Initializable {
    @FXML public Button btnPlay;
    @FXML public Button btnScores;
    @FXML public Button btnHelp;
    @FXML public Button btnExit;
    @FXML public AnchorPane subPanePlay;
    @FXML public AnchorPane subPaneScores;
    @FXML public AnchorPane subPaneHelp;
    @FXML public Button btnStart;
    @FXML public AnchorPane mainPane;
    private AnchorPane paneTohide;
    private BreakoutGameView newGame;
    private boolean isHidden;
    private List<Button> buttonList;
    //constructor
    public BreakoutMenuController() {
        isHidden=true;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            newGame = new BreakoutGameView();
            controlPlay();
            controlHelp();
            controlScore();
            controlExit();
            buttonList = new ArrayList<>();
            addButtonToList();
            initializeListeners();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * BUTTONS
     */

    //1. Button play
    private void controlPlay() {
        btnPlay.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                showSubPane(subPanePlay);
            }
        });
    }
    //2. Button score
    private void controlScore() {
        btnScores.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                showSubPane(subPaneScores);
            }
        });
    }
    //3. Button help
    private void controlHelp() {
        btnHelp.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                showSubPane(subPaneHelp);
            }
        });
    }
    //4. Button exit
    private void controlExit() {
        btnExit.setOnMouseClicked(event -> System.exit(0));

    }

    // 5. Button start
    public void btnStartControl(ActionEvent actionEvent) {
        Scene gameScene = newGame.getGameScene();
        Stage gameStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        gameStage.setTitle("Breakout Game");
        gameStage.setScene(gameScene);
        gameStage.show();
    }
    /*
     * Subpane : play - scores - help
     */
    private void showSubPane(AnchorPane thePane) {
        if (paneTohide != null) {
            moveSubPaneRightToLeft(paneTohide);
        }
        moveSubPaneRightToLeft(thePane);
        paneTohide = thePane;
    }
    private void moveSubPaneRightToLeft(AnchorPane thePane) {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(thePane);
        if (isHidden) {
            transition.setToX(-625);
            isHidden = false;
        } else {
            transition.setToX(0);
            isHidden = true;
        }
        transition.play();
    }
    /*
     * Initialize listeners
     */
    private void addButtonToList() {
        buttonList.add(btnPlay);
        buttonList.add(btnScores);
        buttonList.add(btnHelp);
        buttonList.add(btnExit);
        buttonList.add(btnStart);
    }
    private void initializeListeners() {
        //Buttons
        for (int i=0; i<buttonList.size(); i++) {
            int finalI = i;
            buttonList.get(i).setOnMouseEntered(mouseEvent -> buttonList.get(finalI).setEffect(new Glow()));
            buttonList.get(i).setOnMouseExited(mouseEvent -> buttonList.get(finalI).setEffect(null));
            buttonList.get(i).setCursor((Cursor.HAND));
        }
    }
}