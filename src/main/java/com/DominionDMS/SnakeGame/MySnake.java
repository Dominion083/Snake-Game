package com.DominionDMS.SnakeGame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class MySnake extends Object {
    private int speedXY;
    private int length;
    private int score = 0;
    private int num;
    private boolean alive ;
    private Direction direction = Direction.RIGHT;
    private Image imgSnakeHead ;
    private Image newimgSnakeHead  ;


    private List<Point> bodyPoints = new LinkedList<>();

    public MySnake(int x, int y) {
        this.x = x;
        this.y = y;
        this.image = GameImageLoader.getImages().get("snake-body");
        this.width = (int) image.getWidth();
        this.height = (int) image.getHeight();
        speedXY = 5;
        length = 1;
        num = width / speedXY;
        alive = true;
        imgSnakeHead= GameImageLoader.getImages().get("snake-head-right");
        newimgSnakeHead = imgSnakeHead;

    }
    public int getLength()
    {
        return length;
    }

    public int getScore()
    {
        return score;
    }

    public int getX()
    {
        if(x>=Constants.GAME_WIDTH){
            return Constants.GAME_WIDTH-20;
        } else{
            return x;
        }

    }

    public int getY()
    {
        if(y>=Constants.GAME_HEIGHT){
            return Constants.GAME_HEIGHT-20;
        } else{
            return y;
        }

    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
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
                    newimgSnakeHead = GameImageUtil.rotateImage(imgSnakeHead, -90);
                }
                break;
            case DOWN:
                if (direction != Direction.UP) {
                    direction = Direction.DOWN;
                    newimgSnakeHead = GameImageUtil.rotateImage(imgSnakeHead, 90);
                }
                break;
            case LEFT:
                if (direction != Direction.RIGHT) {
                    direction = Direction.LEFT;
                    newimgSnakeHead = GameImageUtil.rotateImage(imgSnakeHead, -180);
                }
                break;
            case RIGHT:
                if (direction != Direction.LEFT) {
                    direction = Direction.RIGHT;
                    newimgSnakeHead = imgSnakeHead;
                }
                break;
        }
    }



    public void move() {
        if (!alive) {
            return;
        }

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
        checkSelfCollision();
        checkOutOfBounds();
        bodyPoints.add(new Point(getX(), getY()));
        if (bodyPoints.size() == (this.length + 1) * num) {
            bodyPoints.remove(0);
        }

        gc.drawImage(newimgSnakeHead, x, y);
        drawBody(gc);
        move();


    }
    public boolean isAlive() {
      checkSelfCollision();
      checkOutOfBounds();
      return alive;
    }



    private void drawBody(GraphicsContext gc) {
        int length = bodyPoints.size() - 1 - num;

        for (int i = length; i >= num; i -= num)
        {
            Point point = bodyPoints.get(i);
            gc.drawImage(image, point.x, point.y);
        }
    }

    private void checkOutOfBounds() {
        boolean outOfBounds = x < 0 || x >= Constants.GAME_WIDTH || y < 0 || y >= Constants.GAME_HEIGHT;
        if (outOfBounds) {
            alive = false;
        }
    }

    private void checkSelfCollision() {
        for (Point point : bodyPoints)
        {
            for (Point point2 : bodyPoints)
            {
                if (point.equals(point2) && point != point2)
                {
                    alive = false;
                }
            }
        }
    }




}

enum Direction {
    UP, DOWN, LEFT, RIGHT
}


