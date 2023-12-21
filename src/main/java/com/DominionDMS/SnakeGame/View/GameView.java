package com.DominionDMS.SnakeGame.View;

import com.DominionDMS.SnakeGame.Controllers.GameController;
import com.DominionDMS.SnakeGame.Controllers.MusicController;
import com.DominionDMS.SnakeGame.Model.FoodModel;
import com.DominionDMS.SnakeGame.Model.SnakeModel;
import com.DominionDMS.SnakeGame.Model.GameModel;
import com.DominionDMS.SnakeGame.Utils.Constants;
import com.DominionDMS.SnakeGame.Utils.GameImageLoader;
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

import java.awt.*;


public class GameView extends Pane {
	private Canvas canvas;
	private GameController controller;
	private MusicController explosionSound;
	private SnakeModel snakeModel;

	private FoodModel foodModel;
	private Image background;
	private Image fail;
	private Image boom;
	private Image body;
	private ImageView explosionView;

	private Image imgSnakeHead ;
	private Image newimgSnakeHead;


	public void initialise(GameController controller, FoodModel fmodel, SnakeModel smodel) {
		this.controller = controller;
		this.foodModel = fmodel;
		this.snakeModel = smodel; // Initialize with starting position

		background = GameImageLoader.getImages().get("background");
		fail = GameImageLoader.getImages().get("endgame");
		boom = new Image(getClass().getResourceAsStream("/images/boom.gif"));
		imgSnakeHead= GameImageLoader.getImages().get("snake-head-right");
		body = GameImageLoader.getImages().get("snake-body");
		newimgSnakeHead = imgSnakeHead;

		canvas = new Canvas(Constants.GAME_WIDTH, Constants.GAME_HEIGHT); // Set canvas size
		explosionView = new ImageView(boom);
		explosionView.setFitWidth(Constants.BOOM_WIDTH);  // Set the new width
		explosionView.setFitHeight(Constants.BOOM_HEIGHT); // Set the new height
		explosionView.setPreserveRatio(true);
		explosionView.setVisible(false);
	    explosionSound = new MusicController("/music/explosion.mp3", false,false);

		getChildren().addAll(canvas,explosionView);

		setFocusTraversable(true);


	}
	// Initialize and start music player



	public ImageView getExplosion(){
		return explosionView;
	}

	public Image getSnakeHead(){
		return imgSnakeHead;
	}

	public void setSnakeHead(Image image){
		newimgSnakeHead = image;
	}
	public Image getSnakeBody(){
		return body;
	}




	public void paint(boolean alive) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		if (alive) {
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
			gc.drawImage(background, 0, 0);
			drawSnake(gc);
		} else {
			handleGameOver(gc);
		}
	}

	private void handleGameOver(GraphicsContext gc) {
		int x = controller.adjustXWithinBounds(snakeModel.getxPosition());
		int y = controller.adjustYWithinBounds(snakeModel.getyPosition());
		explosionView.setX(x);
		explosionView.setY(y);
		explosionView.setVisible(true);
		explosionSound.play();
		PauseTransition pause = new PauseTransition(Duration.seconds(2));
		pause.setOnFinished(event -> showEndScreen(gc));
		pause.play();
	}
	private void showEndScreen(GraphicsContext gc) {
		explosionSound.stop();
		gc.drawImage(fail,0,0,canvas.getWidth(),canvas.getHeight());
		explosionView.setVisible(false);

	}

	public void drawSnake(GraphicsContext gc) {
		controller.isSnakeAlive();

		int x = snakeModel.getxPosition();
		int y = snakeModel.getyPosition();

		snakeModel.getBodyPoints().add(new Point(x, y));
		manageSnakeBody();

		gc.drawImage(newimgSnakeHead, x, y);
		drawBody(gc);
		controller.move();
	}

	private void manageSnakeBody() {
		if (snakeModel.getBodyPoints().size() == (snakeModel.getLength() + 1) * snakeModel.getBodyNum()) {
			snakeModel.getBodyPoints().remove(0);
		}
	}

	public void drawBody(GraphicsContext gc) {
		int length = snakeModel.getBodyPoints().size() - 1 - snakeModel.getBodyNum();

		for (int i = length; i >= snakeModel.getBodyNum(); i -= snakeModel.getBodyNum())
		{
			Point point = snakeModel.getBodyPoints().get(i);
			gc.drawImage(body, point.x, point.y);
		}
	}
	public void draw(Image image, int x, int y){
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(image, x, y);
	};
	public void drawScore(SnakeModel snakeModel) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFont(Font.font("SansSerif", FontWeight.BOLD, 30));
		gc.setFill(Color.MAGENTA);
		gc.fillText("SCORE : " + snakeModel.getScore(), Constants.SNAKE_SCORE_X, Constants.SNAKE_SCORE_Y);
	}








}
