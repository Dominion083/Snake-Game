package com.psyda5.SnakeGame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.geometry.Rectangle2D;

public abstract class Object {

    int x;
    int y;
    Image image;
    int width;
    int height;


    public void draw(GraphicsContext gc){
        gc.drawImage(image, x, y);
    };

    public Rectangle2D getRectangle() {
        return new Rectangle2D(x, y, width, height);
    }

}
