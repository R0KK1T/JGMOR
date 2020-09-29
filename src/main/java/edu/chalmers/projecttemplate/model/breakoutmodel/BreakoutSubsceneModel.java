package edu.chalmers.projecttemplate.model.breakoutmodel;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BreakoutSubsceneModel {
    public BreakoutSubsceneModel(){}
    public void subsceneBackgroundImage(AnchorPane thePane) throws IOException {
        InputStream inStream = Files.newInputStream(Paths.get("src/main/resources/breakoutresources/images/grey_panel.png"));
        Image backgroundImage = new Image(inStream, 575, 500, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        thePane.setBackground(new Background(background));
    }
}
