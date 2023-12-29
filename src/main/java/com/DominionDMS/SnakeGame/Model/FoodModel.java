package com.DominionDMS.SnakeGame.Model;

import com.DominionDMS.SnakeGame.Utils.GameImageLoader;
import com.DominionDMS.SnakeGame.Utils.Constants;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

import java.util.Random;
/**
 * The FoodModel class represents the food items in the Snake Game.
 * It manages the positioning, appearance, and point value of food items.
 *
 * @author Dominion Aromolaran-modified(Food)
 */
public class FoodModel  {

	private static int points ;
	private static final Random random = new Random();
	private int x;
	private int y;
	private Image image;
	private int width;
	private int height;
	private boolean eaten;


	/**
	 * Initializes the food item's position and appearance.
	 * If a BombModel is provided, the food is placed in a location not overlapping with any bombs.
	 *
	 * @param bombs The BombModel that may contain bomb positions to avoid.
	 */
	public void initialise(BombModel bombs) {
		this.image = GameImageLoader.getImages().get(String.valueOf(random.nextInt(16)));
		this.width = (int) image.getWidth();
		this.height = (int) image.getHeight();
		this.eaten = false;
		int maxWidth = Constants.GAME_WIDTH - width; // Width of game board boundary
		int maxHeight = Constants.GAME_HEIGHT - height; // Height of game board boundary
		if(bombs==null){
			do {
				this.x = random.nextInt(Constants.GAME_WIDTH - width);
				this.y = random.nextInt(Constants.GAME_HEIGHT - height);
				// Adjust for score area, if needed
			} while(x < Constants.SNAKE_SCORE_XEND && y < Constants.SNAKE_SCORE_YEND ||( (x>= Constants.PAUSE_X && y <= (Constants.PAUSE_Y + height))));
		}
		else {
			do {
				this.x = random.nextInt(Constants.GAME_WIDTH - width);
				this.y = random.nextInt(Constants.GAME_HEIGHT - height);
				// Adjust for score area, if needed
			} while (bombs.overlapsWithBomb(x, y) || (x < Constants.SNAKE_SCORE_XEND && y < Constants.SNAKE_SCORE_YEND));
		}
	}
	/**
	 * Returns whether the food item has been eaten.
	 *
	 * @return True if the food has been eaten, otherwise false.
	 */
	public boolean isEaten() {
		return eaten;
	}
	/**
	 * Sets the eaten status of the food item.
	 *
	 * @param x The eaten status to be set.
	 */
	public void setEaten(boolean x) {
		eaten = x;
	}

	/**
	 * Returns the point value of the food item.
	 *
	 * @return The point value.
	 */
	public int getPoints() {
		return points;
	}
	/**
	 * Sets the point value of the food item.
	 *
	 * @param x The point value to be set.
	 */
	public void setPoints(int x) {
		points = x;
	}
	/**
	 * Returns the x-coordinate of the food item's position.
	 *
	 * @return The x-coordinate.
	 */
	public int getX() {
		return x;
	}
	/**
	 * Returns the y-coordinate of the food item's position.
	 *
	 * @return The y-coordinate.
	 */
	public int getY() {
		return y;
	}
	/**
	 * Returns the image representing the food item.
	 *
	 * @return The Image object.
	 */
	public Image getImage() {
		return image;
	}
	/**
	 * Returns a Rectangle2D object representing the food item's bounding box.
	 *
	 * @return A Rectangle2D object.
	 */
	public Rectangle2D getRectangle() {
		return new Rectangle2D(x, y, width, height);
	}

}
