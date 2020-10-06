package edu.chalmers.projecttemplate.controller.snakecontroller;



import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.util.prefs.Preferences;

public class SettingsViewController extends GoBack {
    @FXML
    private Button backBtn;
    @FXML
    private TextField upText;
    @FXML
    private TextField downText;
    @FXML
    private TextField rightText;
    @FXML
    private TextField leftText;
    @FXML
    private CheckBox scoreCheckBox;

    private Preferences prefs;

    private final String UP = "UP";
    private final String DOWN = "DOWN";
    private final String RIGHT = "RIGHT";
    private final String LEFT = "LEFT";

    public SettingsViewController() {
        prefs = Preferences.userRoot().node(SettingsViewController.class.getName());
    }

    // set the default controls
    private void setDefCont() {
        prefs.put(UP, UP);
        prefs.put(DOWN, DOWN);
        prefs.put(RIGHT, RIGHT);
        prefs.put(LEFT, LEFT);
    }

    // set controls text
    private void setContText() {
        upText.setText(prefs.get(UP, ""));
        downText.setText(prefs.get(DOWN, ""));
        rightText.setText(prefs.get(RIGHT, ""));
        leftText.setText(prefs.get(LEFT, ""));
    }

    private void setCheckBox() {
        scoreCheckBox.setSelected(prefs.getBoolean("renderScore", true));
    }

    private void setDefCheckBox() {
        prefs.putBoolean("renderScore", false);
    }

    // reset the controls to the default values
    public void reset() {
        setDefCont();
        setContText();

        setDefCheckBox();
        setCheckBox();
    }

    @FXML
    // this method is called every time when the user open the settings view
    public void initialize() {
        setContText();

        setCheckBox();
    }

}