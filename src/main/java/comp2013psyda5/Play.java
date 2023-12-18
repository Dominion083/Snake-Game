package comp2013psyda5;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Play extends Pane {
	private Canvas canvas;
	public MySnake mySnake;
	private FoodModel foodModel;
	private Image background;
	private Image fail;
    private GameFrame controller;


	public Play() {
		controller = new GameFrame();
		foodModel = new FoodModel();
		mySnake = new MySnake(100, 100); // Initialize with starting position
		background = GameImageLoader.getImages().get("UI-background");
		fail = GameImageLoader.getImages().get("game-scene-01");

		canvas = new Canvas(870, 560); // Set canvas size
		getChildren().add(canvas);

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



	public void draw() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // Clear the canvas
		gc.drawImage(background, 0, 0);

		if (mySnake.isAlive()) {
			mySnake.draw(gc);
			if (!foodModel.isEaten()) {
				foodModel.draw(gc);
				foodModel.checkIfEaten(mySnake);
			}
			else{
				foodModel = new FoodModel();
			}
		} else {
			gc.drawImage(fail, 0, 0);
		}
		drawScore(gc,mySnake);
	}

	private void drawScore(GraphicsContext gc,MySnake mySnake) {
		gc.setFont(Font.font("SansSerif", FontWeight.BOLD, 30));
		gc.setFill(Color.MAGENTA);
		gc.fillText("SCORE : " + mySnake.getScore(), 20, 40);
	}
}
