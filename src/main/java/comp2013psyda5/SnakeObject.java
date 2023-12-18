package comp2013psyda5;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.geometry.Rectangle2D;

public abstract class SnakeObject {
    int x;
    int y;
    Image image;
    int width;
    int height;
    public boolean isAlive;


    public abstract void draw(GraphicsContext gc);

    public Rectangle2D getRectangle() {
        return new Rectangle2D(x, y, width, height);
    }

}
