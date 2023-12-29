package com.DominionDMS.SnakeGame.Model;

import com.DominionDMS.SnakeGame.Utils.Constants;
import com.DominionDMS.SnakeGame.Utils.GameImageLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * The SnakeModel class represents the model of the snake in the Snake Game.
 * It handles the snake's position, dimensions, movement, scoring, and other game-related attributes.
 * @author Dominion Aromolaran-modified(MySnake and SnakeObject)
 */
public class SnakeModel {
    // Fields declaration...
    private int xPosition;
    private int yPosition;
    private int width;
    private int height;
    private int speedXY = Constants.SNAKE_EASY_SPEED;
    private int length;
    private int score;
    private int bodyNum;
    private boolean isAlive ;
    private final Image image = GameImageLoader.getImages().get("snake-body1");

    private List<Point> bodyPoints ;


    /**
     * Initializes the snake's position, dimensions, and other starting attributes.
     *
     * @param x The initial x-coordinate of the snake.
     * @param y The initial y-coordinate of the snake.
     */
    public void initialise(int x, int y){
        this.xPosition = x;
        this.yPosition = y;
        this.width = (int) image.getWidth();
        this.height = (int) image.getHeight();
        length = 1;
        score =0;
        isAlive = true;
        bodyPoints = new LinkedList<>();

    }
    // Getters and setters...

    /**
     * Retrieves a list of points representing the snake's body.
     *
     * @return A list of points for the snake's body.
     */
    public List<Point> getBodyPoints(){
        return bodyPoints;
    }

    /**
     * Sets the snake's x-coordinate.
     *
     * @param x The new x-coordinate of the snake.
     */
    public void setxPosition(int x) {
        this.xPosition = x;
    }

    /**
     * Sets the snake's y-coordinate.
     *
     * @param y The new y-coordinate of the snake.
     */
    public void setyPosition(int y)
    {
        this.yPosition = y;

    }
    /**
     * Sets the snake's score
     *
     * @param score The new score.
     */
    public void setScore(int score)
    {
        this.score = score;

    }
    /**
     * Sets the snake's alive status
     *
     * @param x snake's alive status.
     */
    public void setAlive(boolean x)
    {
        this.isAlive = x;

    }
    /**
     * Checks if the snake is alive.
     *
     * @return True if the snake is alive, false otherwise.
     */
    public boolean isAlive()
    {
        return isAlive;

    }

    /**
     * Returns the x-coordinate of the snake's position.
     *
     * @return The x-coordinate.
     */
    public int getxPosition()
    {
        return xPosition;

    }
    /**
     * Returns the y-coordinate of the snake's position.
     *
     * @return The y-coordinate.
     */
    public int getyPosition()
    {
        return yPosition;
    }

    /**
     * Updates the snake's speed.
     *
     * @param speed The new speed of the snake.
     */
    public void setSpeed(int speed)
    {
      this.speedXY = speed;
      this.bodyNum = width/speed;

    }
    /**
     * Retrieves the snake's current speed.
     *
     * @return The speed of the snake.
     */
    public int getSpeed()
    {
        return speedXY;

    }
    /**
     * Returns the snake's length.
     *
     * @return snake's length.
     */
    public int getLength()
    {
        return length;
    }

    /**
     * Returns the snake's score.
     *
     * @return snake's score.
     */
    public int getScore()
    {
        return score;
    }

    /**
     * Returns the number of body segments of the snake.
     *
     * @return The number of body segments.
     */
    public  int getBodyNum() {
        return bodyNum;
    }

    /**
     * Adds points to the snake's current score.
     *
     * @param points The points to add to the score.
     */
    public void addScore(int points)
    {
        score += points;
    }

    /**
     * Sets the length of the snake.
     *
     * @param length The new length of the snake.
     */
    public void setLength(int length)
    {
        this.length = length;
    }

    /**
     * Returns the width of the snake.
     *
     * @return The width of the snake.
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * Returns the height of the snake.
     *
     * @return The height of the snake.
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * Gets the bounding rectangle of the snake for collision detection.
     *
     * @return A Rectangle2D representing the bounding box of the snake.
     */
    public Rectangle2D getRectangle() {
        return new Rectangle2D(xPosition, yPosition, width, height);
    }
}




