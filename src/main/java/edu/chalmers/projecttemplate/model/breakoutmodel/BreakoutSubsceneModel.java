package edu.chalmers.projecttemplate.model.breakoutmodel;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BreakoutSubsceneModel {
    private boolean isHidden;
    private boolean isHidden1;
    public BreakoutSubsceneModel(){
        isHidden=true;
        isHidden1=true;
    }
    public void subsceneBackgroundImage(AnchorPane thePane) throws IOException {
        InputStream inStream = Files.newInputStream(Paths.get("src/main/resources/breakoutresources/images/grey_panel.png"));
        Image backgroundImage = new Image(inStream, 575, 500, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        thePane.setBackground(new Background(background));
    }
    public void moveSubsceneRightToLeft(AnchorPane thePane) {
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
}
