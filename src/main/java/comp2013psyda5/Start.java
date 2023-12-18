package comp2013psyda5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;


public class Start extends Application{
    /*@Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Snake");
        stage.setScene(scene);
        stage.show();
    }

 */
    @Override
    public void start(Stage stage) throws Exception {
        Play view = new Play();
        GameFrame controller = new GameFrame();
        Scene scene = new Scene(view, 870, 560);
        stage.setScene(scene);

        scene.setOnKeyPressed(event -> controller.handleKeyPressed(event,view.mySnake));

        stage.setTitle("Snake Game");
        stage.show();
        MusicPlayer musicPlayer = new MusicPlayer("/music/frogger.mp3", true);
        musicPlayer.play();

    }

    public static void main(String[] args)
    {

        launch();
    }


}
