package com.DominionDMS.SnakeGame.Utils;

/**
 * The Constants class provides a central place to manage game-wide constant values.
 * It includes constants for game dimensions, initial positions, speeds, and other configurations
 * used throughout the Snake Game.
 * @author Dominion Aromolaran
 */
public class Constants {
    // Constants declaration...

    // Game dimensions
    public static final int GAME_WIDTH = 870;
    public static final int GAME_HEIGHT = 560;

    // Bomb dimensions
    public static final int BOOM_HEIGHT = 30;
    public static final int BOOM_WIDTH = 30;

    // Initial positions
    public static final int SNAKE_START_X = 100;
    public static final int SNAKE_START_Y = 100;

    // UI positions
    public static final int PAUSE_X = 790;
    public static final int PAUSE_Y = 10;
    public static final int SNAKE_SCORE_X = 20;
    public static final int SNAKE_SCORE_Y = 40;
    public static final int SNAKE_SCORE_XEND = 100;
    public static final int SNAKE_SCORE_YEND = 120;

    // Snake speeds
    public static final int SNAKE_EASY_SPEED = 5;
    public static final int SNAKE_MEDIUM_SPEED = 6;
    public static final int SNAKE_DIFFICULT_SPEED = 7;

    // Bomb quantities
    public static final int LEVEL2_BOMB_NUM = 6;
    public static final int LEVEL3_BOMB_NUM = 9;

    // High score configuration
    public static final int NUMBER_HIGHSCORES = 10;

    // Scoring points
    public static final int EASY_POINTS = 10;
    public static final int INTERMEDIATE_POINTS = 20;
    public static final int PROFESSIONAL_POINTS = 30;

    // Private constructor to prevent instantiation
    private Constants() {
    }
}
