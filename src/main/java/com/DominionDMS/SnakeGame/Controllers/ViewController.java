package com.DominionDMS.SnakeGame.Controllers;

import com.DominionDMS.SnakeGame.Model.FoodModel;
import com.DominionDMS.SnakeGame.Model.GameModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ViewController {
    static GameController controller;
    static GameModel model;


    @FXML
    public static void initialise(GameController controller) {
        ViewController.controller = controller;
        ViewController.model = model;
    }

}