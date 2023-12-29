
package com.DominionDMS.SnakeGame.Utils;

import javafx.scene.image.Image;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/**
 * The GameImageLoader class is responsible for loading and storing all the images used in the Snake Game.
 * It loads images for the snake, animations, objects, and food items from specified file paths and stores them
 * in a map for easy retrieval. This class ensures that images are only loaded once and are available throughout
 * the game whenever required.
 *
 * @author Dominion Aromolaran-modified(ImageUtil)
 */
public class GameImageLoader {
	private static final Map<String, Image> IMAGES = new HashMap<>();

	// Static initializer block to load images
	static {
		loadImages();
	}
	/**
	 * Loads images into the IMAGES map. This method is called statically to initialize all game images.
	 */
	private static void loadImages() {
		// snake
		IMAGES.put("snake-head1", GameImageUtil.getImage("/images/Snake/snake-head1.png"));
		IMAGES.put("snake-head2", GameImageUtil.getImage("/images/Snake/snake-head3.png"));
		IMAGES.put("snake-head3", GameImageUtil.getImage("/images/Snake/snake-head2.png"));
		IMAGES.put("snake-body1", GameImageUtil.getImage("/images/Snake/snake-body1.png"));
		IMAGES.put("snake-body2", GameImageUtil.getImage("/images/Snake/snake-body3.png"));
		IMAGES.put("snake-body3", GameImageUtil.getImage("/images/Snake/snake-body2.png"));
		IMAGES.put("snake-icon", GameImageUtil.getImage("/images/Objects/snake-logo.png"));
		//animation and objects
		IMAGES.put("boom", GameImageUtil.getImage("/images/Objects/boom.gif"));
		IMAGES.put("bomb", GameImageUtil.getImage("/images/Objects/bomb.png"));
		// food
		IMAGES.put("0", GameImageUtil.getImage("/images/Food/food-kiwi.png"));
		IMAGES.put("1", GameImageUtil.getImage("/images/Food/food-lemon.png"));
		IMAGES.put("2", GameImageUtil.getImage("/images/Food/food-litchi.png"));
		IMAGES.put("3", GameImageUtil.getImage("/images/Food/food-mango.png"));
		IMAGES.put("4", GameImageUtil.getImage("/images/Food/food-apple.png"));
		IMAGES.put("5", GameImageUtil.getImage("/images/Food/food-banana.png"));
		IMAGES.put("6", GameImageUtil.getImage("/images/Food/food-blueberry.png"));
		IMAGES.put("7", GameImageUtil.getImage("/images/Food/food-cherry.png"));
		IMAGES.put("8", GameImageUtil.getImage("/images/Food/food-durian.png"));
		IMAGES.put("9", GameImageUtil.getImage("/images/Food/food-grape.png"));
		IMAGES.put("10", GameImageUtil.getImage("/images/Food/food-grapefruit.png"));
		IMAGES.put("11", GameImageUtil.getImage("/images/Food/food-peach.png"));
		IMAGES.put("12", GameImageUtil.getImage("/images/Food/food-pear.png"));
		IMAGES.put("13", GameImageUtil.getImage("/images/Food/food-orange.png"));
		IMAGES.put("14", GameImageUtil.getImage("/images/Food/food-pineapple.png"));
		IMAGES.put("15", GameImageUtil.getImage("/images/Food/food-strawberry.png"));
		IMAGES.put("16", GameImageUtil.getImage("/images/Food/food-watermelon.png"));
		IMAGES.put("background1", GameImageUtil.getImage("/images/Background/Game-background1.png"));
		IMAGES.put("background2", GameImageUtil.getImage("/images/Background/Game-background2.png"));
		IMAGES.put("background3", GameImageUtil.getImage("/images/Background/Game-background3.png"));
		IMAGES.put("endgame", GameImageUtil.getImage("/images/Food/end.png"));

	}


	/**
	 * Provides access to the loaded images. Returns an unmodifiable map of image names to Image objects.
	 *
	 * @return An unmodifiable view of the IMAGES map, containing all loaded images.
	 */
	public static Map<String, Image> getImages() {
		return Collections.unmodifiableMap(IMAGES);
	}
}



