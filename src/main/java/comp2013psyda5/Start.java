package comp2013psyda5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;


public class Start {
    /*@Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Snake");
        stage.setScene(scene);
        stage.show();
    }

    public static void main (String[]args)
    {
     launch();
    }*/

    public static void main(String[] args)
    {
        new Play().loadFrame();
        MusicPlayer.getMusicPlay("src/main/resources/music/frogger.mp3");

    }

}
