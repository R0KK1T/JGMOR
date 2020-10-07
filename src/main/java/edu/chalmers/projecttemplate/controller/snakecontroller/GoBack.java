package edu.chalmers.projecttemplate.controller.snakecontroller;

import edu.chalmers.projecttemplate.model.snakemodel.MyLogger;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public abstract class GoBack {

    public void back(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();

        Stage stage = (Stage) button.getScene().getWindow();
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("snakeresources/views/WelcomeView.fxml"));
        } catch (Exception ex) {
            MyLogger.WARN("snakeresources/views/WelcomeView.fxml file not found");
            System.exit(0);
        }

        stage.setScene(new Scene(root));
        stage.show();
    }
}
