package com.DominionDMS.SnakeGame.Model;
import java.io.*;
import java.util.*;

import com.DominionDMS.SnakeGame.Utils.Constants;
import com.DominionDMS.SnakeGame.Utils.ScoreEntry;

public class Leaderboard {
    private final List<ScoreEntry> entries = new ArrayList<>();

    public void addScore(ScoreEntry entry) {
        entries.add(entry);
        if (entries.size() > Constants.NUMBER_HIGHSCORES) {
            entries.sort(Comparator.comparing(ScoreEntry::getScore).reversed());
            entries.subList(Constants.NUMBER_HIGHSCORES, entries.size()).clear();
        }
        entries.sort(Comparator.comparing(ScoreEntry::getLevel).reversed()
                .thenComparing(ScoreEntry::getScore).reversed());

    }

    public List<ScoreEntry> getTopScores() {
        return Collections.unmodifiableList(entries);
    }

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


