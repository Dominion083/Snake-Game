package com.DominionDMS.SnakeGame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;

public class FoodModel extends Object {

	private int points = 10; // Adjusted the score value to a property, assuming 521 is the score for eating the food

	private boolean eaten;

	public FoodModel() {
		this.image = GameImageLoader.getImages().get(String.valueOf(new Random().nextInt(10)));
		this.width = (int) image.getWidth();
		this.height = (int) image.getHeight();
		this.eaten = false;
		randomPosition();
	}

	public void randomPosition() {
		// Assuming these are game board dimensions for demonstration purposes
		int maxWidth = Constants.GAME_WIDTH - width; // Width of game board boundary
		int maxHeight = Constants.GAME_HEIGHT - height; // Height of game board boundary
		this.x = new Random().nextInt(maxWidth);
		this.y = new Random().nextInt(maxHeight); // +40 to account for UI elements like score etc.
	}

	public boolean isEaten() {
		return eaten;
	}


	public void checkIfEaten(MySnake mySnake)	{

			if (mySnake.getRectangle().intersects(this.getRectangle()) && !eaten && mySnake.isAlive()){
			eaten = true;
			mySnake.changeLength(mySnake.getLength() + 1);
			mySnake.addScore(points);
		}
	}



	// Getters and setters as needed
	public int getPoints() {
		return points;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	/*public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}*/
}
