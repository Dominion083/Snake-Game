package com.DominionDMS.SnakeGame.Utils;

public class ScoreEntry {
    private final String name;
    private final int score;
    private final String level;

    public ScoreEntry(String name, int score, String level) {
        this.name = name;
        this.score = score;
        this.level = level;
    }

    // Getters
    public String getName() { return name; }
    public int getScore() { return score; }
    public String getLevel() { return level; }
}