
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
		IMAGES.put("snake-head1", GameImageUtil.getImage("/images/Snake/snake-head1.png"));
		IMAGES.put("snake-head2", GameImageUtil.getImage("/images/Snake/snake-head2.png"));
		IMAGES.put("snake-head3", GameImageUtil.getImage("/images/Snake/snake-head3.png"));
		IMAGES.put("snake-body1", GameImageUtil.getImage("/images/Snake/snake-body1.png"));
		IMAGES.put("snake-body2", GameImageUtil.getImage("/images/Snake/snake-body2.png"));
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


	public static Map<String, Image> getImages() {
		return Collections.unmodifiableMap(IMAGES);
	}
}



