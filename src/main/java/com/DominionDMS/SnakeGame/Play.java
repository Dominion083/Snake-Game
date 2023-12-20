package com.DominionDMS.SnakeGame;

import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;


public class Play extends Pane {
	private Canvas canvas;
	public MySnake mySnake;
	private FoodModel foodModel;
	private Image background;
	private Image fail;
	private Image boom;
	private ImageView explosionView;
	private GameFrame controller;

	private boolean endScreenShown = false;



	public Play() {
		controller = new GameFrame();
		foodModel = new FoodModel();
		mySnake = new MySnake(100, 100); // Initialize with starting position
		background = GameImageLoader.getImages().get("background");
		fail = GameImageLoader.getImages().get("endgame");
		boom = new Image(getClass().getResourceAsStream("/images/boom.gif"));

		canvas = new Canvas(870, 560); // Set canvas size
		explosionView = new ImageView(boom);
		explosionView.setFitWidth(40);  // Set the new width
		explosionView.setFitHeight(40); // Set the new height
		explosionView.setPreserveRatio(true);
		explosionView.setVisible(false);

		getChildren().addAll(canvas,explosionView);

		// Set up key event handling
		setFocusTraversable(true);

		// Start game loop
		startGameLoop();

	}
	// Initialize and start music player


	private void startGameLoop() {
		new AnimationTimer() {
			@Override
			public void handle(long now) {
				draw();
			}
		}.start();
	}
	public ImageView getImageView(){
		return explosionView;
	}


	public void draw() {
		GraphicsContext gc = canvas.getGraphicsContext2D();


		if (mySnake.isAlive()) {
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // Clear the canvas
			gc.drawImage(background, 0, 0);
			endScreenShown = false;
			mySnake.draw(gc);
			if (!foodModel.isEaten()) {
				foodModel.draw(gc);
				foodModel.checkIfEaten(mySnake);
			}
			else{
				foodModel = new FoodModel();
				MusicPlayer musicPlayer = new MusicPlayer("/com/DominionDMS/SnakeGame/music/munch.mp3", false);

			}
		} else if (!endScreenShown){
			explosionView.setX(mySnake.getX());
			explosionView.setY(mySnake.getY());
			explosionView.setVisible(true);
			MusicPlayer musicPlayer = new MusicPlayer("/com/DominionDMS/SnakeGame/music/explosion.mp3", true);
			PauseTransition pause = new PauseTransition(Duration.seconds(2));
			pause.setOnFinished(event -> showEndScreen(gc));
			pause.play();
			endScreenShown = true;
		}

		drawScore(gc,mySnake);
	}
	private void showEndScreen(GraphicsContext gc) {
		gc.drawImage(fail,0,0,canvas.getWidth(),canvas.getHeight());
		explosionView.setVisible(false);

	}

	private void drawScore(GraphicsContext gc,MySnake mySnake) {
		gc.setFont(Font.font("SansSerif", FontWeight.BOLD, 30));
		gc.setFill(Color.MAGENTA);
		gc.fillText("SCORE : " + mySnake.getScore(), 20, 40);
	}
}
