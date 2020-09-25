package edu.chalmers.projecttemplate.model.breakoutmodel;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BreakoutMenuButtonModel extends Button {
    private final String FONT_PATH = "src/main/resources/breakoutresources/files/kenvector_future.ttf";
    //constructor
    public BreakoutMenuButtonModel(String text) {

    }

    private void setButtonFront() {
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", 23));
        }
    }
    private void setButtonPressedStyle() {
        setPrefHeight(45);
        setLayoutY(getLayoutY()+4);
    }
    private void  setButtonReleasedStyle() {
        setPrefHeight(49);
        setLayoutY(getLayoutY()-4);
    }
    private void initializeButtonListeners() {
        setOnMousePressed(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                setButtonPressedStyle();
            }
        });
        setOnMouseReleased(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                setButtonReleasedStyle();
            }
        });
        setOnMouseEntered(mouseEvent -> setEffect(new DropShadow()));
        setOnMouseExited(mouseEvent -> setEffect(null));

    }
}
