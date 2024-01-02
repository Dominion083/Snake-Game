package com.DominionDMS.SnakeGame.Controllers;

import com.DominionDMS.SnakeGame.Application.SnakeGame;
import com.DominionDMS.SnakeGame.Model.BombModel;
import com.DominionDMS.SnakeGame.Model.FoodModel;
import com.DominionDMS.SnakeGame.Utils.GameImageUtil;
import com.DominionDMS.SnakeGame.Utils.ScoreEntry;
import com.DominionDMS.SnakeGame.View.GameView;
import com.DominionDMS.SnakeGame.Model.SnakeModel;
import com.DominionDMS.SnakeGame.Model.GameModel;
import com.DominionDMS.SnakeGame.Utils.Constants;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;




/**
 * The GameController class manages the main game logic for the Snake Game.
 * It handles game initialization, the main game loop, snake movement, and game state updates.
 * The class is responsible for integrating the game model, view, and handling user inputs.
 *
 * @author Dominion Aromolaran-modified (GameFrame)
 */
public class GameController
{
	// Class fields and methods...
	private SnakeModel snakeModel;
	private Direction direction ;
	private GameView view;
	private FoodModel foodModel;
	private GameModel gamemodel;

	private String level;
	private final HighScoreController scoreController = new HighScoreController();
	private MusicController musicController1;
	private MusicController musicController;
	private Scene scene;



	/**
	 * Initializes the game controller with necessary components.
	 * Sets up the view, models, and creates the game scene.
	 *
	 * @param view The GameView component of the game.
	 * @param smodel The SnakeModel representing the player's snake.
	 * @param fmodel The FoodModel representing the food in the game.
	 * @param gmodel The GameModel containing game settings and state.
	 */
	public void initialise(GameView view, SnakeModel smodel, FoodModel fmodel, GameModel gmodel) {

		this.view = view;
		this.snakeModel = smodel;
		this.foodModel = fmodel;
		this.gamemodel = gmodel;
		musicController1 = new MusicController("/music/munch.mp3" ,false,false);
		scene = new Scene(view, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
	}
	/**
	 * Starts the game loop. This method sets up the scene, initializes game elements,
	 * and begins the main game loop.
	 *
	 * @param stage The stage on which the game is displayed.
	 * @param replay Indicates whether this is a new game or a replay.
	 * @throws IOException if an error occurs during game setup.
	 */
	public void startGameLoop(Stage stage,boolean replay) throws IOException {
		direction = Direction.RIGHT;
		if (!replay){
			setTheme(gamemodel.getTheme());
			setLevel(gamemodel.getLevel());
		}

		stage.setScene(scene);
		scene.setOnKeyPressed(event -> handleKeyPressed(event,snakeModel));
		scene.getFocusOwner();
		foodModel.initialise(view.getBombs());
		scoreController.create();
		gameLoopTimer.start();
	}
	/**
	 * Main game loop managed by an AnimationTimer.
	 * This loop is responsible for continually updating the game state and rendering the view.
	 */
	private final AnimationTimer gameLoopTimer = new AnimationTimer() {
		@Override
		public void handle(long now) {

			Loop();
		}
	};

	/**
	 * Main loop of the game that handles updates to the game state.
	 * Checks the snake's life status, updates the game view, and manages the food consumption.
	 */
	public void Loop() {
        isSnakeAlive();
		if (snakeModel.isAlive()) {
			view.paint(true);

			if (!foodModel.isEaten()) {
				view.draw(foodModel.getImage(),foodModel.getX(), foodModel.getY());
				checkIfFoodEaten();
			}
			else{
				if(gamemodel.getEfects()){
				musicController1.play();
				}
				foodModel.initialise(view.getBombs());

			}
			view.drawScore(snakeModel);
		} else {
			view.paint(false);
			view.drawScore(snakeModel);
			gameLoopTimer.stop();
			ScoreEntry newScore = new ScoreEntry(gamemodel.getName(), snakeModel.getScore(), level);
			scoreController.checkAndAddScore(newScore);
			resetGame();


		}


	}
	/**
	 * Sets the theme of the game based on the selected option.
	 * The theme influences the visual appearance of the game.
	 *
	 * @param theme An integer representing the chosen theme.
	 */
	public void setTheme(int theme) {
		switch (theme) {
			case 1:
				view.setUpTheme1();
				break;
			case 2:
				view.setUpTheme2();
				break;
			case 3:
				view.setUpTheme3();
				break;

		}
	}
	/**
	 * Sets the difficulty level of the game.
	 * The level determines the speed of the snake and points gained per food item.
	 *
	 * @param level An integer representing the chosen difficulty level.
	 */
		public void setLevel(int level){
		switch (level){
			case 1:
				this.level = "Beginner";
				view.setUpLevel1();
				snakeModel.setSpeed(Constants.SNAKE_EASY_SPEED);
				foodModel.setPoints(Constants.EASY_POINTS);
				break;
			case 2:
				this.level = "Intermediate";
				view.setUpLevel2();
				snakeModel.setSpeed(Constants.SNAKE_MEDIUM_SPEED);
				foodModel.setPoints(Constants.INTERMEDIATE_POINTS);
				break;
			case 3:
				this.level = "Professional";
				view.setUpLevel3();
				snakeModel.setSpeed(Constants.SNAKE_DIFFICULT_SPEED);
				foodModel.setPoints(Constants.PROFESSIONAL_POINTS);
				break;

		}
	}
	/**
	 * Handles key press events to control the snake's movement and other game actions.
	 * Changes the direction of the snake based on arrow key inputs and pauses the game if the space bar is pressed.
	 * Pauses the game if pause is pressed
	 * @param event The KeyEvent representing the key pressed.
	 * @param snakeModel The SnakeModel representing the player's snake.
	 */
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
				case SPACE:
					pause();
					break;
			}
		}
	/**
	 * Updates the position of the snake based on its current direction.
	 * Called on every frame to move the snake in the game.
	 */
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
	/**
	 * Checks if the snake has moved out of the game bounds.
	 * If the snake has moved out, its alive status is set to false.
	 */
	public void checkOutOfBounds() {
		boolean outOfBounds =snakeModel.getxPosition()< 0 || snakeModel.getxPosition() >= Constants.GAME_WIDTH
				|| snakeModel.getyPosition() < 0 || snakeModel.getyPosition()>= Constants.GAME_HEIGHT;
		if (outOfBounds) {
			snakeModel.setAlive(false);
		}
	}
	/**
	 * Checks for collisions between the snake and itself.
	 * If a self-collision is detected, sets the snake's alive status to false.
	 */
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
	/**
	 * Checks for collisions between the snake and bombs on higher difficulty levels.
	 * If a collision with a bomb is detected, sets the snake's alive status to false.
	 */
	public void checkBombCollision() {
		if (snakeModel.getLength() > 1) {
			for (BombModel.Bomb bomb : view.getBombs().getBombs()) {
				if (snakeModel.getRectangle().intersects(bomb.getRectangle()) && snakeModel.isAlive()) {
					snakeModel.setAlive(false);
					break;
				}
			}
		}
	}
	/**
	 * Checks if the snake is still alive by evaluating collision conditions.
	 * Updates the game state based on the snake's alive status.
	 */
	public void  isSnakeAlive() {
		if(gamemodel.getLevel()>1){
		checkBombCollision();}
		checkSelfCollision();
		checkOutOfBounds();

	}
	/**
	 * Adjusts the snake's X position to ensure it remains within game bounds.
	 * This is used for the explosion animation.
	 * @param newX The new X position of the snake.
	 * @return The adjusted X position, constrained within game bounds.
	 */
	public int adjustXWithinBounds(int newX) {
		if (newX >= Constants.GAME_WIDTH) {
			return Constants.GAME_WIDTH - 20; // 20 is an arbitrary number, replace with appropriate constant or logic
		} else {
			return newX;
		}
	}
	/**
	 * Adjusts the snake's Y position to ensure it remains within game bounds.
	 * This is used for the explosion animation.
	 * @param newY The new Y position of the snake.
	 * @return The adjusted Y position, constrained within game bounds.
	 */
	public int adjustYWithinBounds(int newY) {
		if (newY >= Constants.GAME_HEIGHT) {
			return Constants.GAME_HEIGHT - 20; // Replace 20 with appropriate constant or logic
		} else {
			return newY;
		}
	}
	/**
	 * Checks if the snake has eaten food.
	 * If the snake's head intersects with the food, the food is marked as eaten, and the snake grows.
	 */
	public void checkIfFoodEaten()	{

		if (snakeModel.getRectangle().intersects(foodModel.getRectangle()) && !foodModel.isEaten() && snakeModel.isAlive()){
			foodModel.setEaten(true);
			snakeModel.setLength(snakeModel.getLength() + 1);
			snakeModel.addScore(foodModel.getPoints());
		}
	}
	/**
	 * Initiates the background music for the game.
	 */
	public void playMusic(){
		musicController = new MusicController("/music/frogger.mp3", true,true);

	}
	/**
	 * Pauses the game loop and displays the pause menu.
	 */
	public void pause(){
		view.pauseMenu(true);
		gameLoopTimer.stop();

	}
	/**
	 * Resumes the game loop from a paused state.
	 */
	public void resume(){
		gameLoopTimer.start();

	}

	/**
	 * Resets the game to its initial state.
	 */
	public void resetGame(){
        snakeModel.initialise(Constants.SNAKE_START_X,Constants.SNAKE_START_Y);
		view.resetHead();
	}

	/**
	 * Starts a new game loop, typically used for replaying the game.
	 *
	 * @param stage The stage on which the game is displayed.
	 * @throws IOException if an error occurs during game setup.
	 */
	public void replay(Stage stage) throws IOException {
		startGameLoop(stage, true);

	}
	/**
	 * Stops the background music if it is playing.
	 */
	public void stopMusic(){
		musicController.stop();
	}
	/**
	 * Closes the game application.
	 */
	public void exit(){
		Platform.exit();
	}
	/**
	 * Checks if sound effects are enabled in the game settings.
	 *
	 * @return true if sound effects are enabled, false otherwise.
	 */
	public boolean effects(){
		return gamemodel.getEfects();
	}
	/**
	 * Transitions to the main menu scene.
	 *
	 * @param stage The stage on which the main menu is displayed.
	 * @throws IOException if an error occurs during scene transition.
	 */
	public void mainMenu(Stage stage) throws IOException {
		stage.setScene(SnakeGame.returnStartScene());

	}

	/**
	 * Represents the possible directions the snake can move in the game.
	 * This enum is used to control the movement of the snake on the game board.
	 */
	enum Direction {
		UP, DOWN, LEFT, RIGHT
	}


}
