package com.DominionDMS.SnakeGame.Controllers;
import com.DominionDMS.SnakeGame.Application.SnakeGame;
import com.DominionDMS.SnakeGame.Model.Leaderboard;
import com.DominionDMS.SnakeGame.Utils.ScoreEntry;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The HighScoreController is responsible for managing and displaying high scores in the Snake Game.
 * It handles loading, updating, and saving the high scores to a file.
 * This class is linked with the high score screen in the JavaFX application.
 *
 *  @author Dominion Aromolaran
 */
public class HighScoreController {

    @FXML private Button Back;
    @FXML private TableView<ScoreEntry> scoreTable;
    @FXML private TableColumn<ScoreEntry, String> nameColumn;
    @FXML private TableColumn<ScoreEntry, Integer> scoreColumn;
    @FXML private TableColumn<ScoreEntry, String> levelColumn;

    private static Leaderboard leaderboard ;
    private static boolean create = false;
    private final String filePath = "src/main/resources/HighScores.csv";

    /**
     * Initializes the high score controller by loading scores from a file and setting up the TableView.
     */
    @FXML
    private void initialize() {
        try {
            if(!create){
            leaderboard = new Leaderboard();
            leaderboard.loadScoresFromFile(filePath);
            }
            else{create = false;}
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
            levelColumn.setCellValueFactory(new PropertyValueFactory<>("level"));
            updateLeaderboardView();
        } catch (IOException e) {
            e.printStackTrace(); // Or handle the exception as you see fit
        }


    }
    /**
     * Creates a new leaderboard instance and loads scores from a file.
     *
     * @throws IOException If there is an issue reading the file.
     */
    public void create() throws IOException {
        create = true;
        leaderboard = new Leaderboard();
        leaderboard.loadScoresFromFile(filePath);

    }

    /**
     * Updates the leaderboard view with the latest high scores.
     */
    @FXML
    private void updateLeaderboardView() {
        scoreTable.setItems(FXCollections.observableArrayList(leaderboard.getTopScores()));
    }
    /**
     * Adds a new score entry to the leaderboard and updates the high score file.
     *
     * @param newEntry The new score entry to add.
     */
    @FXML
    public void checkAndAddScore(ScoreEntry newEntry) {
        // Add the new score
        leaderboard.addScore(newEntry);

        // Check if the leaderboard has more than 10 entries and trim if necessary


        // Write the (potentially updated) top scores to the file
        try {
            leaderboard.writeScoresToFile(filePath);
        } catch (IOException e) {
            e.printStackTrace(); // Or handle as needed
        }
    }
    /**
     * Handles the action to return to the main menu.
     *
     * @param event The action event triggered by the user interaction.
     * @throws IOException If there is an issue loading the main menu scene.
     */
    @FXML
    private void mainMenu(ActionEvent event) throws IOException {
        Stage stage = (Stage) Back.getScene().getWindow();
        stage.setScene(SnakeGame.returnStartScene());
    }

}

