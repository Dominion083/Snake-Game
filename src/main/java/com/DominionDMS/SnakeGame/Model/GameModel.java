package com.DominionDMS.SnakeGame.Model;
/**
 * The GameModel class represents the core settings and states for the Snake Game.
 * It manages the game's difficulty level, theme, player name, and audio settings.
 *
 * @author Dominion Aromolaran
 */

public class GameModel {

    private int level = 0;
    private int theme = 0;
    private String Playername;
    private boolean effects = true;
    private boolean music = true;

    /**
     * Retrieves the current theme setting of the game.
     *
     * @return The theme ID.
     */
    public int getTheme(){return theme;}

    /**
     * Retrieves the current level setting of the game.
     *
     * @return The level ID.
     */
    public int getLevel(){return level;}

    /**
     * Sets the game's theme.
     *
     * @param theme The theme ID to set.
     */
    public void setTheme(int theme) {
        this.theme= theme;
    }

    /**
     * Sets the game's difficulty level.
     *
     * @param level The level ID to set.
     */
    public void setLevel(int level) {this.level= level;}

    /**
     * Toggles the music setting on or off.
     *
     * @param status The boolean value representing the music setting.
     */
    public void setMusic(boolean status) {
        this.music= status;
    }

    /**
     * Toggles the sound effects setting on or off.
     *
     * @param status The boolean value representing the sound effects setting.
     */
    public void setEffects(boolean status) {
        this.effects = status;
    }

    /**
     * Sets the name of the player.
     *
     * @param name The player's name.
     */
    public void setName(String name) {
        this.Playername = name;
    }

    /**
     * Retrieves the name of the player.
     *
     * @return The player's name.
     */
    public String getName() {
        return Playername;
    }

    /**
     * Retrieves the current setting of the sound effects.
     *
     * @return True if sound effects are enabled, false otherwise.
     */
    public boolean getEfects() {
        return effects;
    }

}
