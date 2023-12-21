package com.DominionDMS.SnakeGame.Model;

public class GameModel {
    private boolean endStatus = false;

    public void setEndStatus(boolean status) {
        this.endStatus = status;
    }
    public boolean endStatus() {
        return endStatus;
    }
}
