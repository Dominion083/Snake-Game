
package com.DominionDMS.SnakeGame.Controllers;

import com.DominionDMS.SnakeGame.Application.SnakeGame;
import com.DominionDMS.SnakeGame.Model.GameModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

public class MenuController {
        @FXML
        private Button Start;
        @FXML
        private Button Leaderboard;
        @FXML
        private Button report;
        @FXML
        private Button exitButton;
        @FXML
        private TextField nameText;
        @FXML
        private RadioButton Sound;
        @FXML
        private RadioButton SoundEffects;
        @FXML
        private MenuButton Difficulty;
        @FXML
        private MenuButton Theme;
        @FXML
        private MenuItem Professional;
        @FXML
        private MenuItem Intermediate;
        @FXML
        private MenuItem Beginner;
        @FXML
        private MenuItem Summer;
        @FXML
        private MenuItem Christmas;
        @FXML
        private MenuItem Halloween;
        static GameModel gameModel;
        static GameController controller;

        @FXML
        public static void initialise(GameController controller, GameModel model) {
            MenuController.controller = controller;
            MenuController.gameModel = model;


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
                if (gameModel.getTheme() == 0) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Theme required");
                    alert.setHeaderText(null);
                    alert.setContentText("Please select a theme");
                    alert.showAndWait();
                } else {
                    if (gameModel.getLevel() == 0) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Level required");
                        alert.setHeaderText(null);
                        alert.setContentText("Please select a level");
                        alert.showAndWait();
                    } else {
                        gameModel.setName(nameText.getText().trim());
                        Stage stage = (Stage) Start.getScene().getWindow();
                        controller.startGameLoop(stage,false);
                    }

                }
            }
        }
        @FXML
        private void changeSound(ActionEvent event) {
            if (Sound.isSelected()) {
                // Mute the game
                gameModel.setMusic(true);
                controller.playMusic();
            } else {
                // Unmute the game
                gameModel.setMusic(false);
               controller.stopMusic();
            }
        }
        @FXML
        private void changeSoundEffects(ActionEvent event) {
            if (SoundEffects.isSelected()) {
                // Mute the game
                gameModel.setEffects(true);
            } else {
                // Unmute the game
                gameModel.setEffects(false);
            }
        }
        @FXML
        private void initialiseLeaderboard(ActionEvent event) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(SnakeGame.class.getResource("/FXML/HighScores.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) Leaderboard.getScene().getWindow();
            stage.setScene(scene);
        }


        @FXML
        private void changeTheme(ActionEvent event) {
            if( event.getSource() == Summer ){
                Theme.setText(Summer.getText());
                gameModel.setTheme(1);
            }
            else if( event.getSource() == Christmas) {
                Theme.setText(Christmas.getText());
                gameModel.setTheme(2);

            }
            else if( event.getSource() == Halloween ) {
                Theme.setText(Halloween.getText());
                gameModel.setTheme(3);

            }

        }
        @FXML
        private void changeLevel(ActionEvent event) {
            if( event.getSource() == Beginner ){
                Difficulty.setText(Beginner.getText());
                gameModel.setLevel(1);
            }
            else if( event.getSource() == Intermediate) {
                Difficulty.setText(Intermediate.getText());
                gameModel.setLevel(2);

            }
            else if( event.getSource() == Professional) {
                Difficulty.setText(Professional.getText());
                gameModel.setLevel(3);

            }

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