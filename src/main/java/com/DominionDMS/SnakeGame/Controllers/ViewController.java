
package com.DominionDMS.SnakeGame.Controllers;

import com.DominionDMS.SnakeGame.Model.GameModel;
import com.DominionDMS.SnakeGame.View.GameView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

    public class ViewController {
        @FXML
        private Button Start;
        @FXML
        private Button Leaderboard;
        @FXML
        private Button report;
        @FXML
        private TextField nameText;
        static GameModel model;
        static GameController controller;

        @FXML
        private Button startButton; // Assuming there's a Start button in your FXML

        public static void initialise(GameController controller, GameModel model) {
            ViewController.controller = controller;
            ViewController.model = model;
        }

        @FXML
        private void gameSetup() {
            if (nameText.getText().trim().isEmpty()) {
                // Display a warning dialog if the name is not entered
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Name Required");
                alert.setHeaderText(null);
                alert.setContentText("Please enter your name before playing");
                alert.showAndWait();
            } else {

                Stage stage = (Stage) Start.getScene().getWindow();
                controller.startGameLoop(stage);
            }
        }
    }