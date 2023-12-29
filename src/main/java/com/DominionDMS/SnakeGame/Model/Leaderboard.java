package com.DominionDMS.SnakeGame.Model;
import java.io.*;
import java.util.*;

import com.DominionDMS.SnakeGame.Utils.Constants;
import com.DominionDMS.SnakeGame.Utils.ScoreEntry;

/**
 * The Leaderboard class manages the high scores for the Snake Game.
 * It allows for adding new scores, retrieving the top scores, and saving/loading scores to/from a file.
 * @author Dominion Aromolaran
 */
public class Leaderboard {
    private final List<ScoreEntry> entries = new ArrayList<>();
    /**
     * Adds a new score to the leaderboard. If the leaderboard exceeds the maximum size,
     * it trims the lowest scores and sorts by level and score.
     *
     * @param entry The score entry to be added.
     */
    public void addScore(ScoreEntry entry) {
        entries.add(entry);
        if (entries.size() > Constants.NUMBER_HIGHSCORES) {
            entries.sort(Comparator.comparing(ScoreEntry::getScore).reversed());
            entries.subList(Constants.NUMBER_HIGHSCORES, entries.size()).clear();
        }
        entries.sort(Comparator.comparing(ScoreEntry::getLevel).reversed()
                .thenComparing(ScoreEntry::getScore).reversed());

    }
    /**
     * Retrieves the top scores from the leaderboard.
     *
     * @return An unmodifiable list of the top score entries.
     */
    public List<ScoreEntry> getTopScores() {
        return Collections.unmodifiableList(entries);
    }

    /**
     * Loads the scores from a specified file into the leaderboard.
     * The file format is expected to be CSV with columns for name, level, and score.
     *
     * @param filePath The file path of the CSV file containing the scores.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public void loadScoresFromFile(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 3) {
                    String name = values[0];
                    String level = values[1];
                    int score = Integer.parseInt(values[2]);

                    addScore(new ScoreEntry(name, score, level));
                }
            }
        }
    }

    /**
     * Writes the current top scores to a specified file in CSV format.
     * The output file format contains columns for name, level, and score.
     *
     * @param filePath The file path where the scores will be written.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void writeScoresToFile(String filePath) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("Name,Level,Score\n");

            // Write the top scores
            for (ScoreEntry entry : this.getTopScores()) {
                bw.write(entry.getName() + "," + entry.getLevel() + "," + entry.getScore() + "\n");
            }
        }
    }

}


