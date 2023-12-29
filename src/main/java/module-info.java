module com.DominionDMS.SnakeGame {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.desktop;
    requires jlayer;
    requires javafx.graphics;

    requires org.controlsfx.controls;
    requires java.logging;
    requires javafx.media;
    requires javafx.swing;


    exports com.DominionDMS.SnakeGame.Utils;
    opens com.DominionDMS.SnakeGame.Utils to javafx.fxml;
    exports com.DominionDMS.SnakeGame.Controllers;
    opens com.DominionDMS.SnakeGame.Controllers to javafx.fxml;
    exports com.DominionDMS.SnakeGame.Model;
    opens com.DominionDMS.SnakeGame.Model to javafx.fxml;
    exports com.DominionDMS.SnakeGame.View;
    opens com.DominionDMS.SnakeGame.View to javafx.fxml;
    exports com.DominionDMS.SnakeGame.Application;
    opens com.DominionDMS.SnakeGame.Application to javafx.fxml;


}