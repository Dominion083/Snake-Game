package com.DominionDMS.SnakeGame;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MusicPlayer {
	private static final Logger LOGGER = Logger.getLogger(MusicPlayer.class.getName());

	private MediaPlayer mediaPlayer;

	public MusicPlayer(String filename, boolean loop) {
		try {
			String mediaPath = getClass().getResource(filename).toExternalForm();
			Media media = new Media(mediaPath);
			mediaPlayer = new MediaPlayer(media);


			if (loop) {
				mediaPlayer.setOnEndOfMedia(new Runnable() {
					public void run(){mediaPlayer.seek(Duration.ZERO);}
				});
			}

			mediaPlayer.play();
		}  catch (NullPointerException e) {
			LOGGER.log(Level.SEVERE, "File URL is null for: " + filename, e);
		}
	}

	public void play() {
		if (mediaPlayer != null) {
			mediaPlayer.play();
		} else {
			LOGGER.log(Level.SEVERE, "MediaPlayer is not initialized");
		}
	}

	public void pause() {
		if (mediaPlayer != null) {
			mediaPlayer.pause();
		} else {
			LOGGER.log(Level.SEVERE, "MediaPlayer is not initialized");
		}
	}

	public void stop() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.dispose();
		} else {
			LOGGER.log(Level.SEVERE, "MediaPlayer is not initialized");
		}
	}

	// Optionally, if you need to change the track
	public void setMusic(String filename) {
		try {
			Media media = new Media(getClass().getResource(filename).toURI().toString());
			mediaPlayer.stop();
			mediaPlayer.dispose();
			mediaPlayer = new MediaPlayer(media);
		} catch (URISyntaxException e) {
			LOGGER.log(Level.SEVERE, "Could not find file: " + filename, e);
		} catch (NullPointerException e) {
			LOGGER.log(Level.SEVERE, "File URL is null for: " + filename, e);
		}
	}
}
