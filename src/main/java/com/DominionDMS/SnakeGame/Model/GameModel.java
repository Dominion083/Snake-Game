package com.DominionDMS.SnakeGame.Model;

public class GameModel {

    private int level = 0;
    private int theme = 0;
    private String Playername;
    private boolean effects = true;
    private boolean music = true;

    public int getTheme(){return theme;}
    public int getLevel(){return level;}
    public void setTheme(int theme) {
        this.theme= theme;
    }
    public void setLevel(int level) {this.level= level;}
    public void setMusic(boolean status) {
        this.music= status;
    }
    public void setEffects(boolean status) {
        this.effects = status;
    }
    public void setName(String name) {
        this.Playername = name;
    }
    public String getName() {
        return Playername;
    }
    public boolean getEfects() {
        return effects;
    }

}
