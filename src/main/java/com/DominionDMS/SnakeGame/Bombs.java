package com.DominionDMS.SnakeGame;

import com.DominionDMS.SnakeGame.Utils.Constants;
import com.DominionDMS.SnakeGame.Utils.GameImageLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Bombs {
    private int x;
    private int y;
    private Image image;
    private int width;
    private int height;

    private List<Bomb> bombs = new ArrayList<>();
    private Random random = new Random();

    public Bombs(int num){
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
            }while((x < Constants.SNAKE_SCORE_XEND && y < Constants.SNAKE_SCORE_YEND));
            bombs.add(new Bomb(x, y));
        }
    }

    public  List<Bomb> getBombs(){
        return bombs;
    }
    public boolean overlapsWithBomb(int x, int y) {
        for (Bomb bomb : bombs) {
            if (bomb.getRectangle().contains(x, y)) {
                return true;
            }
        }
        return false;
    }


   public class Bomb {
        private int x1;
        private int y1;

        private Point position;

        public Bomb(int x, int y) {
            this.x1 =x;
            this.y1 =y;
            this.position = new Point(x1, y1);
        }

        public Point getPosition(){
            return position;
        }

        public Rectangle2D getRectangle() {
           return new Rectangle2D(x1, y1, width, height);
       }
        // Additional methods as needed, e.g., for rendering the bomb
    }

}
