package com.DominionDMS.SnakeGame.Application;

import com.DominionDMS.SnakeGame.Controllers.GameController;

import com.DominionDMS.SnakeGame.Controllers.ViewController;
import com.DominionDMS.SnakeGame.Model.FoodModel;
import com.DominionDMS.SnakeGame.Model.SnakeModel;
import com.DominionDMS.SnakeGame.Model.GameModel;
import com.DominionDMS.SnakeGame.Utils.Constants;
import com.DominionDMS.SnakeGame.Utils.GameImageLoader;
import com.DominionDMS.SnakeGame.View.GameView;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class SnakeGame extends Application{
    /*@Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SnakeGame.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Snake");
        stage.setScene(scene);
        stage.show();
    }
 */
    GameView view;
    GameController controller;

    GameModel gamemodel;

    FoodModel foodmodel;
    SnakeModel snakemodel;

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

        Scene scene = new Scene(view, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        stage.setScene(scene);
        Image icon = GameImageLoader.getImages().get("snake-icon");
        stage.getIcons().add(icon);
        scene.setOnKeyPressed(event -> controller.handleKeyPressed(event,snakemodel));
        stage.setTitle("Snake Game");
        stage.show();
        controller.startGameLoop();



    }

    public static void main(String[] args)
    {

        launch();
    }


}
