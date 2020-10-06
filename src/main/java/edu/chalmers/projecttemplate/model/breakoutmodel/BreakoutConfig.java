package edu.chalmers.projecttemplate.model.breakoutmodel;

import javafx.animation.TranslateTransition;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
 * simple  class - Breakout configuration
 */
public class BreakoutConfig {
    private boolean isHidden;
    private final String FONT_PATH = "src/main/resources/breakoutresources/files/kenvector_future.ttf";
    public BreakoutConfig(){
        isHidden=true;
    }
    /*
     * Pane settings
     */
    public void setBackgroundImage(AnchorPane thePane, String name) throws IOException {
        InputStream inStream = Files.newInputStream(Paths.get("src/main/resources/breakoutresources/images/"+name+""));
        Image backgroundImage = new Image(inStream, thePane.getPrefWidth(), thePane.getPrefHeight(), false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        thePane.setBackground(new Background(background));
    }
    public void moveSubPaneRightToLeft(AnchorPane thePane) {
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
     * Button Settings
     */
    public void setButtonNormaleStyle(Button button, String name, int width, int height) throws IOException {
        //Background image
        InputStream inStream = Files.newInputStream(Paths.get("src/main/resources/breakoutresources/images/"+name+""));
        Image backgroundImage = new Image(inStream, width, height, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        button.setBackground(new Background(background));
        button.setOnMouseEntered(event ->
                button.setCursor(Cursor.HAND));
        try {
            button.setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
        } catch (FileNotFoundException e) {
            button.setFont(Font.font("Verdana", 23));
        }
    }
    public void setButtonFreeStyle(Button button) {
        button.setOnMouseEntered(event ->
                button.setCursor(Cursor.HAND));
        try {
            button.setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
        } catch (FileNotFoundException e) {
            button.setFont(Font.font("Verdana", 23));
        }
    }
}

