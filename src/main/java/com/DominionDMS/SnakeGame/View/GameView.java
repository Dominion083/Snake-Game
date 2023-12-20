package com.DominionDMS.SnakeGame.View;

import com.DominionDMS.SnakeGame.Controllers.GameController;
import com.DominionDMS.SnakeGame.Controllers.MusicController;
import com.DominionDMS.SnakeGame.Model.FoodModel;
import com.DominionDMS.SnakeGame.Model.SnakeModel;
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

	public SnakeModel snakeModel;
	private FoodModel foodModel;
	private Image background;
	private Image fail;
	private Image boom;
	private Image body;
	private ImageView explosionView;

	private boolean end = false;
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

		canvas = new Canvas(870, 560); // Set canvas size
		explosionView = new ImageView(boom);
		explosionView.setFitWidth(40);  // Set the new width
		explosionView.setFitHeight(40); // Set the new height
		explosionView.setPreserveRatio(true);
		explosionView.setVisible(false);

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




	public void paint(boolean x) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		if(x){
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // Clear the canvas
			gc.drawImage(background, 0, 0);
			end = false;
			drawSnake(gc);
		}
		else{
			explosionView.setX(snakeModel.getxPosition());
			explosionView.setY(snakeModel.getyPosition());
			explosionView.setVisible(true);
			MusicController musicController = new MusicController("/music/explosion.mp3", false);
			PauseTransition pause = new PauseTransition(Duration.seconds(2));
			pause.setOnFinished(event -> showEndScreen(gc));
			pause.play();
			end = true;
		}

	}
	public void drawSnake(GraphicsContext gc) {
		int x = snakeModel.getxPosition();
		int y= snakeModel.getyPosition();
		controller.checkSelfCollision();
		controller.checkOutOfBounds();
		snakeModel.getBodyPoints().add(new Point(x, y));
		if (snakeModel.getBodyPoints().size() == (snakeModel.getLength()+ 1) * snakeModel.getBodyNum()) {
			snakeModel.getBodyPoints().remove(0);
		}

		gc.drawImage(newimgSnakeHead, x, y);
		drawBody(gc);
		controller.move();

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
		gc.fillText("SCORE : " + snakeModel.getScore(), 20, 40);
	}

	private void showEndScreen(GraphicsContext gc) {
		gc.drawImage(fail,0,0,canvas.getWidth(),canvas.getHeight());
		explosionView.setVisible(false);

	}
	public boolean getEndScreen() {
      return end;
	}






}
