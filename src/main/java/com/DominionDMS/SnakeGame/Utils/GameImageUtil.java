package com.DominionDMS.SnakeGame.Utils;

import javafx.scene.image.Image;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.net.URL;

/**
 * The GameImageUtil class offers utility methods for image processing in the Snake Game.
 * It includes functionalities for loading images from specified paths and rotating images to a desired degree.
 * This class is a utility class and cannot be instantiated.
 *
 * @author Dominion Aromolaran-modified(GameUtil)
 */
public final class GameImageUtil {

	/**
	 * Private constructor to prevent instantiation of the utility class.
	 */
	private GameImageUtil() {
		// Private constructor to prevent instantiation
	}

	/**
	 * Loads an image from a specified path and converts it to a JavaFX Image.
	 *
	 * @param imagePath The path to the image file.
	 * @return A JavaFX Image object or null if the image cannot be loaded.
	 */
	public static Image getImage(String imagePath) {
		URL url = GameImageUtil.class.getResource(imagePath);
		try {
			BufferedImage bufferedImage = ImageIO.read(url);
			return SwingFXUtils.toFXImage(bufferedImage, null);
		} catch (Exception e) {
			System.err.println("ERROR: Cannot find the specified image: " + imagePath + "\n");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Rotates a given JavaFX Image by a specified degree.
	 *
	 * @param fxImage The JavaFX Image to be rotated.
	 * @param degree The degree of rotation.
	 * @return The rotated JavaFX Image.
	 */
	public static Image rotateImage(Image fxImage, final int degree) {
		BufferedImage bufferedImage = SwingFXUtils.fromFXImage(fxImage, null);
		int w = bufferedImage.getWidth();
		int h = bufferedImage.getHeight();
		int t = bufferedImage.getColorModel().getTransparency();

		BufferedImage rotatedImage = new BufferedImage(w, h, t);
		Graphics2D graphics2d = rotatedImage.createGraphics();
		graphics2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2d.rotate(Math.toRadians(degree), (double) w / 2, (double) h / 2);
		graphics2d.drawImage(bufferedImage, 0, 0, null);
		graphics2d.dispose();

		return SwingFXUtils.toFXImage(rotatedImage, null);
	}

}