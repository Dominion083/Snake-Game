
package comp2013psyda5;

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
		IMAGES.put("snake-head-right", GameUtil.getImage("/images/snake-head-right.png"));
		IMAGES.put("snake-body", GameUtil.getImage("/images/snake-body.png"));
		// obstacles
		IMAGES.put("0", GameUtil.getImage("/images/food-kiwi.png"));
		IMAGES.put("1", GameUtil.getImage("/images/food-lemon.png"));
		IMAGES.put("2", GameUtil.getImage("/images/food-litchi.png"));
		IMAGES.put("3", GameUtil.getImage("/images/food-mango.png"));
		IMAGES.put("4", GameUtil.getImage("/images/food-apple.png"));
		IMAGES.put("5", GameUtil.getImage("/images/food-banana.png"));
		IMAGES.put("6", GameUtil.getImage("/images/food-blueberry.png"));
		IMAGES.put("7", GameUtil.getImage("/images/food-cherry.png"));
		IMAGES.put("8", GameUtil.getImage("/images/food-durian.png"));
		IMAGES.put("9", GameUtil.getImage("/images/food-grape.png"));
		IMAGES.put("10", GameUtil.getImage("/images/food-grapefruit.png"));
		IMAGES.put("11", GameUtil.getImage("/images/food-peach.png"));
		IMAGES.put("12", GameUtil.getImage("/images/food-pear.png"));
		IMAGES.put("13", GameUtil.getImage("/images/food-orange.png"));
		IMAGES.put("14", GameUtil.getImage("/images/food-pineapple.png"));
		IMAGES.put("15",GameUtil.getImage("/images/food-strawberry.png"));
		IMAGES.put("16", GameUtil.getImage("/images/food-watermelon.png"));
		IMAGES.put("UI-background", GameUtil.getImage("/images/UI-background.png"));
		IMAGES.put("game-scene-01", GameUtil.getImage("/images/game-scene-01.jpg"));
	}

	private static Image loadImage(String path) {
		return new Image(GameImageLoader.class.getResourceAsStream(path));
	}

	public static Map<String, Image> getImages() {
		return Collections.unmodifiableMap(IMAGES);
	}
}



