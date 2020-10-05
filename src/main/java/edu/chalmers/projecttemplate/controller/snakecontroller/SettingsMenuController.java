package edu.chalmers.projecttemplate.controller.snakecontroller;

import edu.chalmers.projecttemplate.view.snakeview.SnakeView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;


public class SettingsMenuController implements Initializable {


    ObservableList gameSpeedChoiceList = FXCollections.observableArrayList();
    ObservableList wallCollisionsChoiceList = FXCollections.observableArrayList();
    ObservableList gameboardSizeChoiceList = FXCollections.observableArrayList();


    @FXML
    ChoiceBox<String> gameSpeed;

    @FXML
    ChoiceBox<String> wallCollisions;

    @FXML
    ChoiceBox<String> gameboardSize;

    @FXML
    Button mainMenuButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDataToGameSpeedChoiceList();
        loadDataToWallCollisionsChoiceList();
        loadDataToGameboardSizeChoiceListChoiceList();
    }


    @FXML
    public void mainMenu(ActionEvent event) {
        SnakeView.mainStage.setScene(SnakeView.mainMenuScene);
    }


    // Handling game speed
    public void loadDataToGameSpeedChoiceList() {
        gameSpeedChoiceList.removeAll(gameSpeedChoiceList);

        String slowSpeed = "SLOW";
        String normalSpeed = "NORMAL";
        String fastSpeed = "FAST";
        String insaneSpeed = "INSANE!";

        gameSpeedChoiceList.addAll(slowSpeed, normalSpeed, fastSpeed, insaneSpeed);
        gameSpeed.getItems().addAll(gameSpeedChoiceList);
        gameSpeed.setValue("NORMAL");
    }

    public void setUpGameSpeed() {

    }

    public void loadDataToWallCollisionsChoiceList() {
        wallCollisionsChoiceList.removeAll(wallCollisionsChoiceList);

        String wallCollisionsOn = "ON";
        String wallCollisionsOff = "OFF";

        wallCollisionsChoiceList.addAll(wallCollisionsOn, wallCollisionsOff);
        wallCollisions.getItems().addAll(wallCollisionsChoiceList);
        wallCollisions.setValue("OFF");
    }

    public void setUpWallCollisions(){

    }

    public void loadDataToGameboardSizeChoiceListChoiceList() {
        gameboardSizeChoiceList.removeAll(gameboardSizeChoiceList);
        String gameboardSizeSmall = "SMALL";
        String gameboardSizeMedium = "MEDIUM";
        String gameboardSizeBig = "BIG";

        gameboardSizeChoiceList.addAll(gameboardSizeSmall, gameboardSizeMedium, gameboardSizeBig);
        gameboardSize.getItems().addAll(gameboardSizeChoiceList);
        gameboardSize.setValue("MEDIUM");
    }

    public void setUpBoardSize(){

    }

}
