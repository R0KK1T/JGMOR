package edu.chalmers.projecttemplate.model.breakoutmodel;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BreakoutButtonModel extends Button {
    private final String FONT_PATH = "src/main/resources/breakoutresources/files/kenvector_future.ttf";
    public BreakoutButtonModel(){}
    public void setButtonFront(Button button) {
        try {
            button.setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
        } catch (FileNotFoundException e) {
            button.setFont(Font.font("Verdana", 23));
        }
    }
    public void setCursor(Button button) {
        button.setOnMouseEntered(event ->
                button.setCursor(Cursor.HAND));
    }
}
