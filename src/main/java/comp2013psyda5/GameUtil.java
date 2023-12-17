package comp2013psyda5;

import javafx.scene.image.Image;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.net.URL;

public final class GameUtil {

	private GameUtil() {
		// Private constructor to prevent instantiation
	}

	public static Image getImage(String imagePath) {
		URL url = GameUtil.class.getResource(imagePath);
		try {
			BufferedImage bufferedImage = ImageIO.read(url);
			return SwingFXUtils.toFXImage(bufferedImage, null);
		} catch (Exception e) {
			System.err.println("ERROR: Cannot find the specified image: " + imagePath + "\n");
			e.printStackTrace();
			return null;
		}
	}

	public static Image rotateImage(Image fxImage, final int degree) {
		BufferedImage bufferedImage = SwingFXUtils.fromFXImage(fxImage, null);
		int w = bufferedImage.getWidth();
		int h = bufferedImage.getHeight();
		int t = bufferedImage.getColorModel().getTransparency();

		BufferedImage rotatedImage = new BufferedImage(w, h, t);
		Graphics2D graphics2d = rotatedImage.createGraphics();
		graphics2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2);
		graphics2d.drawImage(bufferedImage, 0, 0, null);
		graphics2d.dispose();

		return SwingFXUtils.toFXImage(rotatedImage, null);
	}
}
