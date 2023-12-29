package com.DominionDMS.SnakeGame.Utils;
/**
 * The ScoreEntry class represents a single entry in the leaderboard of the Snake Game.
 * It encapsulates the player's name, their score, and the level at which the score was achieved.
 * This class provides a way to store and retrieve score details for the game's high score tracking.
 *
 * @author Dominion Aromolaran
 */
public class ScoreEntry {
    private final String name;
    private final int score;
    private final String level;

    /**
     * Constructs a new ScoreEntry with the specified name, score, and level.
     *
     * @param name The name of the player.
     * @param score The score achieved by the player.
     * @param level The level at which the score was achieved.
     */
    public ScoreEntry(String name, int score, String level) {
        this.name = name;
        this.score = score;
        this.level = level;
    }

    // Getters
    /**
     * Returns the name of the player.
     *
     * @return The name of the player.
     */
    public String getName() { return name; }
    /**
     * Returns the score achieved by the player.
     *
     * @return The score.
     */
    public int getScore() { return score; }
    /**
     * Returns the level at which the score was achieved.
     *
     * @return The level.
     */
    public String getLevel() { return level; }
}


