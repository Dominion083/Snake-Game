package com.DominionDMS.SnakeGame.Controllers;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * The MusicController class handles the audio playback functionality in the Snake Game.
 * It manages media playback, including play, pause, stop, and looping of audio tracks.
 *
 * @author Dominion Aromolaran-modified(MusicPlayer)
 */
public class MusicController {
	private static final Logger LOGGER = Logger.getLogger(MusicController.class.getName());
	private MediaPlayer mediaPlayer;
	private boolean loop;
	private boolean play;
	private String filename;
	/**
	 * Constructs a new MusicController with specified music file, loop, and play settings.
	 *
	 * @param filename The path to the audio file.
	 * @param loop     Boolean indicating whether the audio should loop.
	 * @param play     Boolean indicating whether the audio should play immediately.
	 */
	public MusicController(String filename, boolean loop,boolean play) {
		this.filename = filename;
		this.loop = loop;
		this.play = play;

		initializeMediaPlayer();
	}

	/**
	 * Initializes the MediaPlayer object and sets up the media file for playback.
	 */
	private void initializeMediaPlayer() {
		try {
			String mediaPath = getClass().getResource(filename).toExternalForm();
			Media media = new Media(mediaPath);
			mediaPlayer = new MediaPlayer(media);


			if (loop) {
				mediaPlayer.setOnEndOfMedia(new Runnable() {
					public void run(){mediaPlayer.seek(Duration.ZERO);}
				});
			}
          if(play){
			mediaPlayer.play();
		  }
		}  catch (NullPointerException e) {
			LOGGER.log(Level.SEVERE, "File URL is null for: " + filename, e);
		}
	}
	/**
	 * Plays the audio file. If the media player is not initialized, it initializes it first.
	 */
	public void play() {
		if (mediaPlayer != null) {
			initializeMediaPlayer();
			mediaPlayer.play();
		} else {
			LOGGER.log(Level.SEVERE, "MediaPlayer is not initialized");
		}
	}

	/**
	 * Stops the audio playback and disposes of the media player resources.
	 */
	public void stop() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.dispose();
		} else {
			LOGGER.log(Level.SEVERE, "MediaPlayer is not initialized");
		}
	}


}
