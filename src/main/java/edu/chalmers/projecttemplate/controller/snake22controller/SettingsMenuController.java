package edu.chalmers.projecttemplate.controller.snake22controller;

import edu.chalmers.projecttemplate.view.snake22view.Snake22View;
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

    GameConfiguration gameConfiguration = Snake22View.gameConfiguration;

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
        setUpGameSpeed();
        setUpWallCollisions();
        setUpBoardSize();
        Snake22View.mainStage.setScene(Snake22View.mainMenuScene);
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
        if (gameSpeed.getValue().equals("SLOW")) {
            gameConfiguration.setGameSpeed(120);
        } else if (gameSpeed.getValue().equals("NORMAL")){
            gameConfiguration.setGameSpeed(80);
        } else if (gameSpeed.getValue().equals("FAST")){
            gameConfiguration.setGameSpeed(40);
        } else if(gameSpeed.getValue().equals("INSANE!")){
            gameConfiguration.setGameSpeed(18);
        } else {    // just in case...
            gameConfiguration.setGameSpeed(80);
        }

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
        if (wallCollisions.getValue().equals("ON")){
            gameConfiguration.setGameOverOnWallCollision(true);
        } else {
            gameConfiguration.setGameOverOnWallCollision(false);
        }
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
        if (gameboardSize.getValue().equals("BIG")){
            gameConfiguration.setBoardWidth(800);
            gameConfiguration.setBoardHeight(800);
        } else if (gameboardSize.getValue().equals("MEDIUM")){
            gameConfiguration.setBoardWidth(640);
            gameConfiguration.setBoardHeight(640);
        } else {
            gameConfiguration.setBoardWidth(480);
            gameConfiguration.setBoardHeight(480);
        }
    }
}
