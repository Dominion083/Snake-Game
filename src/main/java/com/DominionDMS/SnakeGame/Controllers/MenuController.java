
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
/**
 * The MenuController class handles the functionality of the main menu in the Snake Game.
 * It manages the interactions with various UI components such as buttons, text fields, and radio buttons,
 * allowing the user to configure game settings like sound, difficulty level, and theme.
 *
 * @author Dominion Aromolaran
 */
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
        /**
         * Initializes the MenuController with a GameController and GameModel.
         *
         * @param controller The game controller to interact with.
         * @param model      The model representing game settings.
         */
        @FXML
        public static void initialise(GameController controller, GameModel model) {
            MenuController.controller = controller;
            MenuController.gameModel = model;


        }
        /**
         * Sets up the game based on the user input and game settings.
         *
         * @throws IOException If there is an error during game setup.
         */
        @FXML
        private void gameSetup() throws IOException {
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
        /**
         * Handles the change of sound settings based on user interaction.
         *
         * @param event The action event triggered by the user.
         */
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
        /**
         * Handles the change of sound effects settings based on user interaction.
         *
         * @param event The action event triggered by the user.
         */
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
        /**
         * Initializes the leaderboard view.
         *
         * @param event The action event triggered by the user.
         * @throws IOException If there is an error loading the leaderboard.
         */
        @FXML
        private void initialiseLeaderboard(ActionEvent event) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(SnakeGame.class.getResource("/FXML/HighScores.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) Leaderboard.getScene().getWindow();
            stage.setScene(scene);
        }

        /**
         * Changes the game theme based on user selection.
         *
         * @param event The action event triggered by the user.
         */
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
        /**
         * Changes the game level/difficulty based on user selection.
         *
         * @param event The action event triggered by the user.
         */
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


     /**
     * Exits the game.
     */
    @FXML
    private void exit() {
     controller.exit();
    }

    /**
     * Allows the user to send a report via email.
     */
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