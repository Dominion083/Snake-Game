package com.DominionDMS.SnakeGame.Model;

import com.DominionDMS.SnakeGame.Utils.GameImageLoader;
import com.DominionDMS.SnakeGame.Utils.Constants;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

import java.util.Random;

public class FoodModel  {

	private static int points ;
	private static final Random random = new Random();
	private int x;
	private int y;
	private Image image;
	private int width;
	private int height;
	private boolean eaten;

	public FoodModel() {

	}

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

	public boolean isEaten() {

		return eaten;
	}
	public void setEaten(boolean x) {
		eaten = x;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int x) {
		points = x;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImage() {
		return image;
	}

	public Rectangle2D getRectangle() {
		return new Rectangle2D(x, y, width, height);
	}

}
