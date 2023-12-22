package com.DominionDMS.SnakeGame.Model;

public class GameModel {
    private boolean endStatus = false;
    private int level;
    private int theme = 0;

    public int getTheme(){return theme;}
    public int getLevel(){return level;}
    public void setTheme(int theme) {
        this.theme= theme;
    }
    public void setLevel(int level) {
        this.level= level;
    }
    public void setEndStatus(boolean status) {
        this.endStatus = status;
    }
    public boolean endStatus() {
        return endStatus;
    }
}
