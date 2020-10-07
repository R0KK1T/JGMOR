package edu.chalmers.projecttemplate.model.breakoutmodel;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BreakoutMenuModel extends Button {
    AnchorPane mainPane;
    AnchorPane subPanePlay;
    AnchorPane subPaneScore;
    AnchorPane subPaneHelp;
    AnchorPane paneTohide;
    Button play;
    Button score;
    Button help;
    Button exit;
    List<AnchorPane> paneList;
    List<Button> buttonList;
    BreakoutConfig breakoutConfig;
    public BreakoutMenuModel(AnchorPane mainPane, AnchorPane subPanePlay, AnchorPane subPaneScore, AnchorPane subPaneHelp, Button play, Button score,
                             Button help, Button exit) throws IOException {
        this.mainPane = mainPane;
        this.subPanePlay = subPanePlay;
        this.subPaneScore = subPaneScore;
        this.subPaneHelp = subPaneHelp;
        this.play = play;
        this.score = score;
        this.help = help;
        this.exit = exit;
        paneList = new ArrayList<>();
        buttonList = new ArrayList<>();
        breakoutConfig = new BreakoutConfig();
        menuInit();
    }
    //Initalize menu model
    private void menuInit() throws IOException {
        setMainMenuStyle();

        addButtonToList();
        setButtonStyle();
        addSubPaneToList();
        setSubPaneStyle();
    }

    /*
     * Main menu's style
     */
    private void setMainMenuStyle() throws IOException {
        breakoutConfig.setBackgroundImage(mainPane, "bc2.jpg");
    }


    //Add button to list
    private void addButtonToList() {
        buttonList.add(play);
        buttonList.add(score);
        buttonList.add(help);
        buttonList.add(exit);
    }
    //Button's style
    private void setButtonStyle() throws IOException {
        for (int i=0; i<4; i++) {
            breakoutConfig.setButtonNormaleStyle(buttonList.get(i), "yellow_button00.png", 190, 49);
        }
    }

    /*
     * SUBPANE
     */

    //Add subpane to list
    private void addSubPaneToList() {
        paneList.add(subPanePlay);
        paneList.add(subPaneScore);
        paneList.add(subPaneHelp);
    }

    //Subpane's style
    private void setSubPaneStyle() throws IOException {
        for (int i=0; i<3; i++)
            breakoutConfig.setBackgroundImage(paneList.get(i), "pane.png");

    }


}