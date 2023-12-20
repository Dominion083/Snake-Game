
package com.DominionDMS.SnakeGame.Utils;

import javafx.scene.image.Image;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GameImageLoader {
	private static final Map<String, Image> IMAGES = new HashMap<>();

	static {
		loadImages();
	}

	private static void loadImages() {
		// snake
		IMAGES.put("snake-head-right", GameImageUtil.getImage("/images/snake-head-right.png"));
		IMAGES.put("snake-body", GameImageUtil.getImage("/images/snake-body.png"));
		IMAGES.put("snake-icon", GameImageUtil.getImage("/images/snake-logo.png"));
		//animation
		IMAGES.put("boom", GameImageUtil.getImage("/images/boom.gif"));
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
		IMAGES.put("background", GameImageUtil.getImage("/images/UI-background.png"));
		IMAGES.put("endgame", GameImageUtil.getImage("/images/end.png"));

	}


	public static Map<String, Image> getImages() {
		return Collections.unmodifiableMap(IMAGES);
	}
}



