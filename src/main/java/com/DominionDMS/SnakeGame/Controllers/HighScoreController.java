package com.DominionDMS.SnakeGame.Controllers;
import com.DominionDMS.SnakeGame.Application.SnakeGame;
import com.DominionDMS.SnakeGame.Model.Leaderboard;
import com.DominionDMS.SnakeGame.Utils.GameImageUtil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;


public class HighScoreController {

    @FXML private Button Back;
    @FXML private TableView<GameImageUtil.ScoreEntry> scoreTable;
    @FXML private TableColumn<GameImageUtil.ScoreEntry, String> nameColumn;
    @FXML private TableColumn<GameImageUtil.ScoreEntry, Integer> scoreColumn;
    @FXML private TableColumn<GameImageUtil.ScoreEntry, String> levelColumn;

    private static Leaderboard leaderboard ;
    private static boolean create;
    private final String filePath = "src/main/resources/HighScores.csv";

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
    public void create() throws IOException {
        create = true;
        leaderboard = new Leaderboard();
        leaderboard.loadScoresFromFile(filePath);

    }

    @FXML
    private void updateLeaderboardView() {
        scoreTable.setItems(FXCollections.observableArrayList(leaderboard.getTopScores()));
    }
    @FXML
    public void checkAndAddScore(GameImageUtil.ScoreEntry newEntry) {
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



    @FXML
    private void mainMenu(ActionEvent event) throws IOException {
        Stage stage = (Stage) Back.getScene().getWindow();
        stage.setScene(SnakeGame.returnStartScene());
    }

}

