package edu.chalmers.projecttemplate.controller.breakoutcontroller;

import edu.chalmers.projecttemplate.controller.controllerInterface.IController;
import edu.chalmers.projecttemplate.model.breakoutmodel.BestScore;
import edu.chalmers.projecttemplate.view.breakoutview.BreakoutGameView;
import edu.chalmers.projecttemplate.view.breakoutview.BreakoutMenuView;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
/*
 * Controller for Breakout game menu
 */
public class BreakoutMenuController implements Initializable, IController {
    @FXML public Button btnPlay;
    @FXML public Button btnScores;
    @FXML public Button btnHelp;
    @FXML public Button btnExit;
    @FXML public AnchorPane subPanePlay;
    @FXML public AnchorPane subPaneScores;
    @FXML public AnchorPane subPaneHelp;
    @FXML public Button btnStart;
    @FXML public TextArea firstName;
    @FXML public TextArea lastName;
    @FXML public Label bestPlayer;
    private AnchorPane paneTohide;
    private BreakoutMenuView menuView;
    private boolean isHidden;
    private List<Button> buttonList;
    public static ArrayList<String> userInfo;
    public static BestScore bestScore;

    //constructor
    public BreakoutMenuController() {
        isHidden = true;
        userInfo = new ArrayList<>();
        bestScore = BestScore.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controlPlay();
        controlHelp();
        controlScore();
        controlExit();
        buttonList = new ArrayList<>();
        addButtonToList();
        initializeListeners();
        try {
            showBestPlayer();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*
     * BUTTONS - The menu has 5 buttons
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
    public void btnStartControl(ActionEvent actionEvent) throws IOException {
        passingUserInfo();//Passing user info
        BreakoutGameView newGame = new BreakoutGameView();
        Scene gameScene = newGame.getGameScene();
        Stage gameStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        gameStage.setTitle("Breakout Game");
        gameStage.setScene(gameScene);
        gameStage.show();
    }

    /*
     * Sub-pane : The menu has 3 sub-panes : sub-pane for play, scores and help
     */
    private void showSubPane(AnchorPane thePane) {
        if (paneTohide != null) {
            moveSubPaneRightToLeft(paneTohide);
        }
        moveSubPaneRightToLeft(thePane);
        paneTohide = thePane;
    }
    /*
     * This function show and hide a sub-pane
     */
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
        for (int i = 0; i < buttonList.size(); i++) {
            int finalI = i;
            buttonList.get(i).setOnMouseEntered(mouseEvent -> buttonList.get(finalI).setEffect(new Glow()));
            buttonList.get(i).setOnMouseExited(mouseEvent -> buttonList.get(finalI).setEffect(null));
            buttonList.get(i).setCursor((Cursor.HAND));
        }
    }

    /*
     * Get user's first & last name
     */
    private void passingUserInfo() {
        if (firstName.getText().isEmpty())
            userInfo.add("Player1");
        else
            userInfo.add(firstName.getText());
        if (lastName.getText().isEmpty())
            userInfo.add("Player1");
        else
            userInfo.add(lastName.getText());
    }
    /*
     * Show High score - Breakout 5 best players.
     */
    private void showBestPlayer() throws FileNotFoundException {
        String s = "";
        int size=0;
        if (bestScore.getBestPlayers().size()<=5)
            size = bestScore.getBestPlayers().size();
        else
            size = 5;
        for (int i=0; i<size; i++) {
            s+= bestScore.getBestPlayers().get(i).getFirstName()+"\t\t "+bestScore.getBestPlayers().get(i).getLastName()+
                    "\t\t "+bestScore.getBestPlayers().get(i).getMyScore()+"\n\n";
        }
        bestPlayer.setText(s);
    }

    @Override
    public void startGame() {
    }

    @Override
    public Scene getScene() {
        try {
            menuView = new BreakoutMenuView();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return menuView.getMainScene();
    }
}