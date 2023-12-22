package com.DominionDMS.SnakeGame.Application;

import com.DominionDMS.SnakeGame.Controllers.GameController;

import com.DominionDMS.SnakeGame.Controllers.MenuController;
import com.DominionDMS.SnakeGame.Model.FoodModel;
import com.DominionDMS.SnakeGame.Model.SnakeModel;
import com.DominionDMS.SnakeGame.Model.GameModel;
import com.DominionDMS.SnakeGame.Utils.Constants;
import com.DominionDMS.SnakeGame.Utils.GameImageLoader;
import com.DominionDMS.SnakeGame.View.GameView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class SnakeGame extends Application{
    GameView view;
    GameController controller;
    GameModel gamemodel;
    FoodModel foodmodel;
    SnakeModel snakemodel;
    static Scene scene;



    @Override
    public void start(Stage stage) throws Exception {
        view = new GameView();
        controller = new GameController();
        foodmodel = new FoodModel();
        snakemodel = new SnakeModel();
        gamemodel = new GameModel();


        snakemodel.initialise(Constants.SNAKE_START_X,Constants.SNAKE_START_Y);
        controller.initialise(view,snakemodel,foodmodel,gamemodel);
        view.initialise(controller,foodmodel,snakemodel);
        MenuController.initialise(controller,gamemodel);



        Image icon = GameImageLoader.getImages().get("snake-icon");

        FXMLLoader fxmlLoader = new FXMLLoader(SnakeGame.class.getResource("/FXML/Menu.fxml"));
        scene = new Scene(fxmlLoader.load());
        stage.setTitle("Snake Yipee");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(icon);
        controller.playMusic();
        stage.setOnCloseRequest(event -> {
            Platform.exit();
        });
        stage.show();



    }
    public static Scene returnScene(){
        return scene;
    }


     public static void main(String[] args)
    {

        launch();
    }


}
