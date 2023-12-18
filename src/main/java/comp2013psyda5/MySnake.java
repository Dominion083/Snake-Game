package comp2013psyda5;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class MySnake extends SnakeObject{
    private int speedXY;
    private int length;
    private int x, y;
    private int score = 0;
    private int num;
    private boolean alive = true;
    private Direction direction = Direction.RIGHT;
    private Image imgSnakeHead = GameImageLoader.getImages().get("snake-head-right");
    private Image imgSnakeBody = GameImageLoader.getImages().get("snake-body");
    private List<Point> bodyPoints = new LinkedList<>();

    public MySnake(int x, int y) {
        this.x = x;
        this.y = y;
        this.image = GameImageLoader.getImages().get("snake-body");
        this.width = (int) image.getWidth();
        this.height = (int) image.getHeight();
        this.speedXY = 5;
        this.length = 1;
        this.num = width / speedXY;

    }
    public int getLength()
    {
        return length;
    }

    public int getScore()
    {
        return score;
    }

    public void addScore(int points)
    {
        score += points;
    }

    public void changeLength(int length)
    {
        this.length = length;
    }


    public void keyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                if (direction != Direction.DOWN) {
                    direction = Direction.UP;
                    imgSnakeHead = GameImageUtil.rotateImage(imgSnakeHead, 90);
                }
                break;
            case DOWN:
                if (direction != Direction.UP) {
                    direction = Direction.DOWN;
                    imgSnakeHead = GameImageUtil.rotateImage(imgSnakeHead, -90);
                }
                break;
            case LEFT:
                if (direction != Direction.RIGHT) {
                    direction = Direction.LEFT;
                    imgSnakeHead = GameImageUtil.rotateImage(imgSnakeHead, -180);
                }
                break;
            case RIGHT:
                if (direction != Direction.LEFT) {
                    direction = Direction.RIGHT;
                    imgSnakeHead = GameImageUtil.rotateImage(imgSnakeHead, 0);
                }
                break;
        }
    }



    public void move() {
        // Update the position of the head based on the current direction
        switch (direction) {
            case UP:
                y -= speedXY;
                break;
            case DOWN:
                y += speedXY;
                break;
            case LEFT:
                x -= speedXY;
                break;
            case RIGHT:
                x += speedXY;
                break;
        }
    }




    public void draw(GraphicsContext gc) {
        checkOutOfBounds();
        checkSelfCollision();

        bodyPoints.add(new Point(x, y));
        if (bodyPoints.size() == (this.length + 1) * num)
        {
            bodyPoints.remove(0);
        }

        gc.drawImage(imgSnakeHead, x, y);
        drawBody(gc);
        move();

    }
    public boolean isAlive() {
      return alive;
    }



    private void drawBody(GraphicsContext gc) {
        // Draw the snake's body
        for (Point point : bodyPoints) {
            gc.drawImage(imgSnakeBody, point.x, point.y);
        }
    }

    private void checkOutOfBounds() {
        boolean outOfBounds = x < 0 || x >= 870 || y < 0 || y >= 560;
        if (outOfBounds) {
            alive = false;
        }
    }

    private void checkSelfCollision() {
        if (!bodyPoints.isEmpty()) {
            Point head = bodyPoints.getFirst();
            for (int i = 1; i < bodyPoints.size(); i++) {
                Point bodyPart = bodyPoints.get(i);
                if (head.equals(bodyPart)) {
                    alive = false;
                    break;
                }
            }
        }
    }


}

enum Direction {
    UP, DOWN, LEFT, RIGHT
}


