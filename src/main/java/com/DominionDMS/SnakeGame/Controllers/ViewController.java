
package com.DominionDMS.SnakeGame.Controllers;

import com.DominionDMS.SnakeGame.Model.GameModel;
import com.DominionDMS.SnakeGame.View.GameView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URI;

public class ViewController {
        @FXML
        private Button Start;
        @FXML
        private Button Leaderboard;
        @FXML
        private Button report;
        private Button exitButton;
        @FXML
        private TextField nameText;
        @FXML
        private RadioButton Sound;
        @FXML
        private MenuButton Difficulty;
        @FXML
        private MenuButton Theme;
        @FXML
        private MenuItem Hard;
        @FXML
        private MenuItem Medium;
        @FXML
        private MenuItem Easy;
        @FXML
        private MenuItem Summer;
        @FXML
        private MenuItem Christmas;
        @FXML
        private MenuItem Halloween;
        static GameModel model;
        static GameController controller;

        private boolean theme = false;

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
                if(model.getTheme()==0){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Theme required");
                    alert.setHeaderText(null);
                    alert.setContentText("Please select a theme");
                    alert.showAndWait();
                }

                else {
                    Stage stage = (Stage) Start.getScene().getWindow();
                    controller.startGameLoop(stage, model.getTheme());
                }

            }
        }
        @FXML
        private void changeSound(ActionEvent event) {
            if (Sound.isSelected()) {
                // Mute the game
                controller.stopMusic();
            } else {
                // Unmute the game
               controller.playMusic();
            }
        }
        @FXML
        private void monochrome() {

        }
        @FXML
        private void changeTheme(ActionEvent event) {
            if( event.getSource() == Summer ){
                Theme.setText(Summer.getText());
                model.setTheme(1);
            }
            else if( event.getSource() == Christmas) {
                Theme.setText(Christmas.getText());
                model.setTheme(2);

            }
            else if( event.getSource() == Halloween ) {
                Theme.setText(Halloween.getText());
                model.setTheme(3);

            }

        }
        @FXML
        private void changeLevel() {

        }
        @FXML
        private void setTheme() {

        }
    @FXML
    private void exit() {
     controller.exit();
    }

    @FXML
    private void report() {
        try {
            Desktop.getDesktop().mail(new URI("mailto:psyda5@nottingham.ac.uk")); // Replace with your email address
        } catch (Exception ex) {
            // Handle any exceptions here
            ex.printStackTrace();
        }
    }





    }