package com.DominionDMS.SnakeGame.Controllers;

import com.DominionDMS.SnakeGame.Model.FoodModel;
import com.DominionDMS.SnakeGame.Utils.GameImageUtil;
import com.DominionDMS.SnakeGame.View.GameView;
import com.DominionDMS.SnakeGame.Model.SnakeModel;
import com.DominionDMS.SnakeGame.Model.GameModel;
import com.DominionDMS.SnakeGame.Utils.Constants;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;


/**
 *
 * @Project Snakee
 * @Description Hladdu leikinn og endurnýjaðu hann stöðugt
 * @Author Sigurður Sigurðardóttir
 * @version Ekki viss
 */


public class GameController
{

	private SnakeModel snakeModel;
	private Direction direction = Direction.RIGHT;
	private GameView view;
	private FoodModel foodModel;
	private GameModel gamemodel;

	private MusicController musicController1;

	private MusicController musicController;





	public void initialise(GameView view, SnakeModel smodel, FoodModel fmodel, GameModel gmodel) {
		this.view = view;
		this.snakeModel = smodel;
		this.foodModel = fmodel;
		this.gamemodel = gmodel;


	}
	private final AnimationTimer gameLoopTimer = new AnimationTimer() {
		@Override
		public void handle(long now) {
			try {
				Loop();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	};
	public void startGameLoop(Stage stage) {
	    Scene scene = new Scene(view, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
		stage.setScene(scene);
		scene.setOnKeyPressed(event -> handleKeyPressed(event,snakeModel));
		musicController = new MusicController("/music/frogger.mp3", true,true);
		musicController1 = new MusicController("/music/munch.mp3" ,false,false);
		gameLoopTimer.start();
	}


	private void Loop() throws IOException {

		if (snakeModel.isAlive()) {
			view.paint(true);

			if (!foodModel.isEaten()) {
				view.draw(foodModel.getImage(),foodModel.getX(), foodModel.getY());
				checkIfFoodEaten();
			}
			else{
				musicController1.play();
				foodModel.initialise();



			}
			view.drawScore(snakeModel);
		} else {
			view.paint(false);
			gamemodel.setEndStatus(true);
			view.drawScore(snakeModel);
			musicController.stop();
			musicController1.stop();
			gameLoopTimer.stop();

		}


	}


	public void handleKeyPressed(KeyEvent event, SnakeModel snakeModel) {
			switch (event.getCode()) {
				case UP:
					if (direction != Direction.DOWN) {
						direction = Direction.UP;
						view.setSnakeHead(GameImageUtil.rotateImage(view.getSnakeHead(),-90));
					}
					break;
				case DOWN:
					if (direction != Direction.UP) {
						direction = Direction.DOWN;
						view.setSnakeHead(GameImageUtil.rotateImage(view.getSnakeHead(),90));
					}
					break;
				case LEFT:
					if (direction != Direction.RIGHT) {
						direction = Direction.LEFT;
						view.setSnakeHead(GameImageUtil.rotateImage(view.getSnakeHead(),180));
					}
					break;
				case RIGHT:
					if (direction != Direction.LEFT) {
						direction = Direction.RIGHT;
						view.setSnakeHead(view.getSnakeHead());
					}
					break;
			}
		}

	public void move() {
		if (!snakeModel.isAlive()) {
			return;
		}

		// Update the position of the head based on the current direction
		switch (direction) {
			case UP:
				snakeModel.setyPosition(snakeModel.getyPosition() - snakeModel.getSpeed());
				break;
			case DOWN:
				snakeModel.setyPosition(snakeModel.getyPosition() + snakeModel.getSpeed());
				break;
			case LEFT:
				snakeModel.setxPosition(snakeModel.getxPosition() - snakeModel.getSpeed());
				break;
			case RIGHT:
				snakeModel.setxPosition(snakeModel.getxPosition() + snakeModel.getSpeed());
				break;
		}
	}
	public void checkOutOfBounds() {
		boolean outOfBounds =snakeModel.getxPosition()< 0 || snakeModel.getxPosition() >= Constants.GAME_WIDTH
				|| snakeModel.getyPosition() < 0 || snakeModel.getyPosition()>= Constants.GAME_HEIGHT;
		if (outOfBounds) {
			snakeModel.setAlive(false);
		}
	}

	public void checkSelfCollision() {
		for (Point point : snakeModel.getBodyPoints())
		{
			for (Point point2 : snakeModel.getBodyPoints())
			{
				if (point.equals(point2) && point != point2)
				{
					snakeModel.setAlive(false);
					break;
				}
			}
		}
	}
	public void  isSnakeAlive() {
		checkSelfCollision();
		checkOutOfBounds();
	}

	public int adjustXWithinBounds(int newX) {
		if (newX >= Constants.GAME_WIDTH) {
			return Constants.GAME_WIDTH - 20; // 20 is an arbitrary number, replace with appropriate constant or logic
		} else {
			return newX;
		}
	}

	public int adjustYWithinBounds(int newY) {
		if (newY >= Constants.GAME_HEIGHT) {
			return Constants.GAME_HEIGHT - 20; // Replace 20 with appropriate constant or logic
		} else {
			return newY;
		}
	}

	public void checkIfFoodEaten()	{

		if (snakeModel.getRectangle().intersects(foodModel.getRectangle()) && !foodModel.isEaten() && snakeModel.isAlive()){
			foodModel.setEaten(true);
			snakeModel.setLength(snakeModel.getLength() + 1);
			snakeModel.addScore(foodModel.getPoints());
		}
	}
	public boolean hasGameEnded()	{
       return gamemodel.endStatus();

	}



	enum Direction {
		UP, DOWN, LEFT, RIGHT
	}


}
