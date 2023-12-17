package comp2013psyda5;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;

public class FoodModel {
	private Image image;
	private int points = 10; // Adjusted the score value to a property, assuming 521 is the score for eating the food
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean isEaten = false;

	public FoodModel() {
		this.image = GameImageLoader.getImages().get(String.valueOf(new Random().nextInt(10)));
		this.width = (int) image.getWidth();
		this.height = (int) image.getHeight();


		randomPosition();
	}

	public void randomPosition() {
		// Assuming these are game board dimensions for demonstration purposes
		int maxWidth = 870 - width; // Width of game board boundary
		int maxHeight = 560 - height; // Height of game board boundary
		this.x = new Random().nextInt(maxWidth);
		this.y = new Random().nextInt(maxHeight) + 40; // +40 to account for UI elements like score etc.
	}

	public boolean isEaten() {
		return isEaten;
	}

	public void setEaten(boolean eaten) {
		isEaten = eaten;
	}

	public void draw(GraphicsContext gc) {
		if (!isEaten) {
			gc.drawImage(image, x, y);
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
