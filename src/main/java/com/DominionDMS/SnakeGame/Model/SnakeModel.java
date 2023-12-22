package com.DominionDMS.SnakeGame.Model;

import com.DominionDMS.SnakeGame.Utils.Constants;
import com.DominionDMS.SnakeGame.Utils.GameImageLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class SnakeModel {
    private int xPosition;
    private int yPosition;
    private int width;
    private int height;
    private int speedXY;
    private int length;
    private int score = 0;
    private int bodyNum;
    private boolean isAlive ;

    private List<Point> bodyPoints = new LinkedList<>();


    public void initialise(int x, int y){
        this.xPosition = x;
        this.yPosition = y;
        Image image = GameImageLoader.getImages().get("snake-body1");
        this.width = (int) image.getWidth();
        this.height = (int) image.getHeight();
        speedXY = 5;
        length = 1;
        bodyNum = width / speedXY;
        this.isAlive = true;

    }
    public List<Point> getBodyPoints(){
        return bodyPoints;
    }

    public void setxPosition(int x) {
        this.xPosition = x;
    }


    public void setyPosition(int y)
    {
        this.yPosition = y;

    }
    public void setAlive(boolean x)
    {
        this.isAlive = x;

    }
    public boolean isAlive()
    {
        return isAlive;

    }


    public int getxPosition()
    {
        return xPosition;

    }
    public int getyPosition()
    {
        return yPosition;
    }


    public void setSpeed(int speed)
    {
      this.speedXY = speed;
      this.bodyNum = width/speed;

    }
    public int getSpeed()
    {
        return speedXY;

    }
    public int getLength()
    {
        return length;
    }

    public int getScore()
    {
        return score;
    }

    public  int getBodyNum() {
        return bodyNum;
    }

    public void addScore(int points)
    {
        score += points;
    }

    public void setLength(int length)
    {
        this.length = length;
    }
    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public Rectangle2D getRectangle() {
        return new Rectangle2D(xPosition, yPosition, width, height);
    }
}




