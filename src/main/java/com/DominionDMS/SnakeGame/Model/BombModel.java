package com.DominionDMS.SnakeGame.Model;

import com.DominionDMS.SnakeGame.Utils.Constants;
import com.DominionDMS.SnakeGame.Utils.GameImageLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
/**
 * The BombModel class manages the bombs in the Snake Game.
 * It is responsible for creating, positioning, and managing bomb objects within the game field.
 *
 * @author Dominion Aromolaran
 */
public class BombModel {
    private int x;
    private int y;
    private Image image;
    private int width;
    private int height;

    private List<Bomb> bombs = new ArrayList<>();
    private Random random = new Random();

    /**
     * Constructs a BombModel with a specified number of bombs.
     *
     * @param num The number of bombs to be created and managed.
     */
    public BombModel(int num){
        bombs = new LinkedList<>();
        this.image = GameImageLoader.getImages().get("bomb");
        this.width = (int) image.getWidth();
        this.height = (int) image.getHeight();
        int maxWidth = Constants.GAME_WIDTH - width; // Width of game board boundary
        int maxHeight = Constants.GAME_HEIGHT - height;
        for (int i = 0; i < num; i++) {
            do {
                this.x = random.nextInt(maxWidth);
                this.y = random.nextInt(maxHeight);
            }while((x < Constants.SNAKE_SCORE_XEND && y < Constants.SNAKE_SCORE_YEND) ||  (x>= Constants.PAUSE_X && y <= (Constants.PAUSE_Y + height)));
            bombs.add(new Bomb(x, y));
        }
    }

    /**
     * Returns a list of all the bombs currently in the game.
     *
     * @return A list of Bomb objects.
     */
    public  List<Bomb> getBombs(){
        return bombs;
    }

    /**
     * Checks if a given point overlaps with any bomb in the game.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     * @return True if the point overlaps with any bomb, otherwise false.
     */
    public boolean overlapsWithBomb(int x, int y) {
        for (Bomb bomb : bombs) {
            if (bomb.getRectangle().contains(x, y)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Represents a single bomb in the Snake Game.
     */
    public class Bomb {
        private int x1;
        private int y1;

        private Point position;

        /**
         * Constructs a new Bomb at the specified coordinates.
         *
         * @param x The x-coordinate of the bomb.
         * @param y The y-coordinate of the bomb.
         */
        public Bomb(int x, int y) {
            this.x1 =x;
            this.y1 =y;
            this.position = new Point(x1, y1);
        }

        /**
         * Returns the position of the bomb.
         *
         * @return A Point object representing the bomb's position.
         */
        public Point getPosition(){
            return position;
        }

        /**
         * Returns a Rectangle2D object representing the bomb's bounding box.
         *
         * @return A Rectangle2D object.
         */
        public Rectangle2D getRectangle() {
           return new Rectangle2D(x1, y1, width, height);
       }
        // Additional methods as needed, e.g., for rendering the bomb
    }

}
