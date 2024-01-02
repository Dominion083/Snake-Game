package com.DominionDMS.SnakeGame.View;

import com.DominionDMS.SnakeGame.Application.SnakeGame;
import com.DominionDMS.SnakeGame.Model.BombModel;
import com.DominionDMS.SnakeGame.Controllers.GameController;
import com.DominionDMS.SnakeGame.Controllers.MusicController;
import com.DominionDMS.SnakeGame.Model.FoodModel;
import com.DominionDMS.SnakeGame.Model.SnakeModel;
import com.DominionDMS.SnakeGame.Utils.Constants;
import com.DominionDMS.SnakeGame.Utils.GameImageLoader;
import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;

/**
 * This class represents the visual aspect of the Snake game. It handles the rendering
 * of the game components such as the snake, food, bombs, and the game environment.
 * It also manages the pause button ,pause and end game menus.
 *
 * @author Dominion Aromolaran-modified(Play and GameFrame)
 */

public class GameView extends Pane {
	private static final int VBOX_SPACING = 10;
	private static final int BUTTON_MIN_WIDTH = 200;
	private static final int BUTTON_MIN_HEIGHT = 50;
	private static final int PADDING_TOP = 10;
	private static final int PADDING_RIGHT = 20;
	private static final int PADDING_BOTTOM = 10;
	private static final int PADDING_LEFT = 20;
	private Canvas canvas;
	private GameController controller;
	private MusicController explosionSound;
	private SnakeModel snakeModel;
	private Button pauseButton;
	private FoodModel foodModel;
	private Image background;
	private Image fail;
	private Image boom;
	private Image body;
	private Image bombImage;
	private ImageView explosionView;
	private BombModel bombs;
	private Image imgSnakeHead ;
	private Image newimgSnakeHead;
	private VBox pauseMenu;
	private VBox endMenu;
	private int level;


	/**
	 * Initializes the game view with necessary components and models.
	 *
	 * @param controller The game controller.
	 * @param fmodel The food model.
	 * @param smodel The snake model.
	 */
	public void initialise(GameController controller, FoodModel fmodel, SnakeModel smodel) {
		this.controller = controller;
		this.foodModel = fmodel;
		this.snakeModel = smodel;



		pauseButton = new Button("||"); // Use a symbol or text for pause
		pauseButton.setFont(Font.font("SansSerif", FontWeight.BOLD, 24));// A color that doesn't interfere much
		pauseButton.setBackground(null); // Make the background of the button transparent
		pauseButton.setLayoutX(Constants.PAUSE_X); // Place it on the top right corner
		pauseButton.setLayoutY(Constants.PAUSE_Y);
		pauseButton.setOnAction(event -> {
			controller.pause();
			pauseMenu.setVisible(true);

		});

		pauseMenu = new VBox(VBOX_SPACING); // VBox with spacing of 10
		pauseMenu.setAlignment(Pos.CENTER); // Center align the buttons
		pauseMenu.setVisible(false); // Initially invisible

		Button resumeButton = createStyledButton("Resume");
		Button exitButton = createStyledButton("Exit");
		Button mainMenuButton = createStyledButton("Main Menu");
		pauseMenu.getChildren().addAll(resumeButton, exitButton, mainMenuButton);

		resumeButton.setOnAction(event -> {
			pauseMenu.setVisible(false);
			controller.resume();

		});
		exitButton.setOnAction(event -> {
			controller.exit();

		});
		mainMenuButton.setOnAction(event -> {
			try {
				pauseMenu.setVisible(false);
				controller.resetGame();
				Stage stage = (Stage) mainMenuButton.getScene().getWindow();
				controller.mainMenu(stage);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		});

		pauseMenu.setLayoutX((double) Constants.GAME_WIDTH / 2 - 100); // Adjust as necessary
		pauseMenu.setLayoutY((double) Constants.GAME_HEIGHT / 2 - 50);

		endMenu = new VBox(VBOX_SPACING); // VBox with spacing of 10
		endMenu.setAlignment(Pos.CENTER); // Center align the buttons
		endMenu.setVisible(false); // Initially invisible

		Button replayButton = createStyledButton("Play Again");
		Button exitButton1 = createStyledButton("Exit");
		Button mainMenuButton1 = createStyledButton("Main Menu");
		Button leaderBoardButton = createStyledButton("LeaderBoard");

		endMenu.getChildren().addAll(replayButton, mainMenuButton1,leaderBoardButton,exitButton1);

		replayButton.setOnAction(event -> {
			endMenu.setVisible(false);
			Stage stage = (Stage) replayButton.getScene().getWindow();
			try {
				controller.replay(stage);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		exitButton1.setOnAction(event -> {
			controller.exit();

		});
		mainMenuButton1.setOnAction(event -> {
			endMenu.setVisible(false);
			try {
				Stage stage = (Stage) mainMenuButton1.getScene().getWindow();
				controller.mainMenu(stage);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		});
	    leaderBoardButton.setOnAction(event -> {
			endMenu.setVisible(false);

			try {
				FXMLLoader fxmlLoader = new FXMLLoader(SnakeGame.class.getResource("/FXML/HighScores.fxml"));
				Scene scene = new Scene(fxmlLoader.load());
				Stage stage = (Stage) leaderBoardButton.getScene().getWindow();
				stage.setScene(scene);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}


		});
		endMenu.setLayoutX((double) Constants.GAME_WIDTH / 2 - 100); // Adjust as necessary
		endMenu.setLayoutY((double) Constants.GAME_HEIGHT / 2 - 50);


		fail = GameImageLoader.getImages().get("endgame");
		boom = new Image(getClass().getResourceAsStream("/images/Objects/boom.gif"));
		bombImage = GameImageLoader.getImages().get("bomb");
		canvas = new Canvas(Constants.GAME_WIDTH, Constants.GAME_HEIGHT); // Set canvas size
		explosionView = new ImageView(boom);
		explosionView.setFitWidth(Constants.BOOM_WIDTH);  // Set the new width
		explosionView.setFitHeight(Constants.BOOM_HEIGHT); // Set the new height
		explosionView.setPreserveRatio(true);
		explosionView.setVisible(false);
		explosionSound = new MusicController("/music/explosion.mp3", false,false);


		getChildren().addAll(canvas,explosionView,pauseButton,pauseMenu,endMenu);
		pauseButton.setFocusTraversable(false);

	}
	/**
	 * Retrieves the current image of the snake's head.
	 *
	 * @return The Image object representing the snake's head.
	 */
	public Image getSnakeHead(){
		return imgSnakeHead;
	}

	/**
	 * Sets a new image for the snake's head.
	 *
	 * @param image The new Image object for the snake's head.
	 */
	public void setSnakeHead(Image image){
		newimgSnakeHead = image;
	}

	/**
	 * Resets the image of the snake's head to the original image.
	 */
	public void resetHead(){
		newimgSnakeHead = imgSnakeHead;
	}


	/**
	 * Retrieves the bomb model used in the game.
	 *
	 * @return The bomb model.
	 */
	public BombModel getBombs(){
		return bombs;
	}

	public void pauseMenu(boolean x){
		pauseMenu.setVisible(x);
	}
	/**
	 * Sets up the game view for theme 1(Summer), including background and snake images.
	 */
	public void setUpTheme1() {
		this.background = GameImageLoader.getImages().get("background1");
		this.body = GameImageLoader.getImages().get("snake-body1");
		this.imgSnakeHead = GameImageLoader.getImages().get("snake-head1");
		refocus();

	}
	/**
	 * Sets up the game view for theme 2(Christmas), including background and snake images.
	 */
	public void setUpTheme2() {

			this.background = GameImageLoader.getImages().get("background2");
			this.body = GameImageLoader.getImages().get("snake-body2");
			this.imgSnakeHead = GameImageLoader.getImages().get("snake-head2");
			refocus();

	}
	/**
	 * Sets up the game view for theme 3(Halloween), including background and snake images.
	 */
	public void setUpTheme3() {

		this.background = GameImageLoader.getImages().get("background3");
		this.body = GameImageLoader.getImages().get("snake-body3");
		this.imgSnakeHead= GameImageLoader.getImages().get("snake-head3");
		refocus();

	}
	/**
	 * Sets up the game environment for level 1. This level does not include bombs.
	 * The method also calls refocus to ensure the UI elements are properly aligned.
	 */
	public void setUpLevel1(){
        bombs= null;
		this.level = 1;
		refocus();
	}
	/**
	 * Sets up the game environment for level 2. This level includes a predefined number
	 * of bombs as specified in Constants.LEVEL2_BOMB_NUM.
	 * The method also calls refocus to ensure the UI elements are properly aligned.
	 */
	public void setUpLevel2(){

		bombs = new BombModel(Constants.LEVEL2_BOMB_NUM);
		this.level = 2;
		refocus();
	}
	/**
	 * Sets up the game environment for level 3. This level includes a higher number
	 * of bombs as specified in Constants.LEVEL3_BOMB_NUM for increased difficulty.
	 * The method also calls refocus to ensure the UI elements are properly aligned.
	 */
	public void setUpLevel3(){

		bombs = new BombModel(Constants.LEVEL3_BOMB_NUM);
		this.level = 3;
		refocus();
	}

	/**
	 * Refocuses the game view to handle user input and updates visuals.
	 */
	public void refocus(){
		pauseButton.setVisible(true);
		newimgSnakeHead = imgSnakeHead;
		requestFocus();
		setFocusTraversable(true);

	}
	/**
	 * Creates a styled button with specified text. The button is styled with a black background,
	 * white text color, Arial font, and set dimensions based on class constants.
	 *
	 * @param text The text to display on the button.
	 * @return Button A newly created Button with specified styles and text.
	 */

	private Button createStyledButton(String text) {
		Button button = new Button(text);
		button.setStyle("-fx-background-color: black; -fx-text-fill: white;");
		button.setFont(new Font("Arial", 24)); // Set font size
		button.setMinWidth(BUTTON_MIN_WIDTH); // Use constant for minimum width
		button.setMinHeight(BUTTON_MIN_HEIGHT); // Use constant for minimum height
		button.setPadding(new Insets(PADDING_TOP, PADDING_RIGHT, PADDING_BOTTOM, PADDING_LEFT));
		return button;
	}



	/**
	 * Paints the game components on the canvas, including the snake, food, and bombs.
	 *
	 * @param alive Boolean indicating if the snake is alive.
	 */
	public void paint(boolean alive) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		if (alive) {
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
			gc.drawImage((background), 0, 0,canvas.getWidth(),canvas.getHeight());
			drawSnake(gc);
			if (level ==2 || level == 3){
			drawBombs(gc);}
		} else {
			handleGameOver(gc);
		}
	}
	/**
	 * Handles the game over scenario, displaying the explosion and the end screen.
	 *
	 * @param gc The graphics context for drawing on the canvas.
	 */
	private void handleGameOver(GraphicsContext gc) {
		int x = controller.adjustXWithinBounds(snakeModel.getxPosition());
		int y = controller.adjustYWithinBounds(snakeModel.getyPosition());
		explosionView.setX(x);
		explosionView.setY(y);
		explosionView.setVisible(true);
		if (controller.effects()){
		explosionSound.play();}
		PauseTransition pause = new PauseTransition(Duration.seconds(2));
		pause.setOnFinished(event -> showEndScreen(gc));
		pause.play();
	}
	/**
	 * Shows the end screen ad sets the endMenu, pauseButton and explosionView as false.
	 *
	 * @param gc The graphics context for drawing on the canvas.
	 */
	private void showEndScreen(GraphicsContext gc) {
		gc.drawImage(fail,0,0,canvas.getWidth(),canvas.getHeight());
		pauseButton.setVisible(false);
		endMenu.setVisible(true);
		explosionView.setVisible(false);


	}
	/**
	 * Draws the snake on the canvas based on its current position and state.
	 *
	 * @param gc The graphics context for drawing on the canvas.
	 */
	public void drawSnake(GraphicsContext gc) {
		controller.isSnakeAlive();
		int x = snakeModel.getxPosition();
		int y = snakeModel.getyPosition();

		manageSnakeBody(x,y);
		gc.drawImage(newimgSnakeHead, x, y);
		drawBody(gc);
		controller.move();
	}
	/**
	 * Manages the body of the snake, updating its position as the snake moves.
	 *
	 * @param x The x-coordinate of the snake's head.
	 * @param y The y-coordinate of the snake's head.
	 */
	private void manageSnakeBody(int x, int y) {
		snakeModel.getBodyPoints().add(new Point(x, y));
		if (snakeModel.getBodyPoints().size() == (snakeModel.getLength() + 1) * snakeModel.getBodyNum()) {
			snakeModel.getBodyPoints().remove(0);
		}
	}
	/**
	 * Draws bombs on the game canvas. This method iterates through the bomb list and
	 * renders each bomb at its respective position on the game field.
	 *
	 * @param gc GraphicsContext for drawing on the canvas.
	 */
	public void drawBombs(GraphicsContext gc) {
		for (BombModel.Bomb bomb : bombs.getBombs()) {
			Point position = bomb.getPosition();
			gc.drawImage(bombImage, position.x, position.y);
		}
	}
	/**
	 * Draws the snake's body on the game canvas. The method renders each segment of the snake's body.
	 *
	 * @param gc GraphicsContext for drawing on the canvas.
	 */
	private void drawBody(GraphicsContext gc) {
		int length = snakeModel.getBodyPoints().size() - 1 - snakeModel.getBodyNum();

		for (int i = length; i >= snakeModel.getBodyNum(); i -= snakeModel.getBodyNum())
		{
			Point point = snakeModel.getBodyPoints().get(i);
			gc.drawImage(body, point.x, point.y);
		}
	}
	/**
	 * Draws an image on the canvas at the specified x and y coordinates.
	 *
	 * @param image The image to be drawn.
	 * @param x     The x-coordinate on the canvas where the image will be placed.
	 * @param y     The y-coordinate on the canvas where the image will be placed.
	 */
	public void draw(Image image, int x, int y){
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(image, x, y);
	}
	/**
	 * Renders the current score of the snake model on the game canvas.
	 *
	 * @param snakeModel The snake model containing the current score to be displayed.
	 */
	public void drawScore(SnakeModel snakeModel) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFont(Font.font("SansSerif", FontWeight.BOLD, 30));
		gc.setFill(Color.MAGENTA);
		gc.fillText("SCORE : " + snakeModel.getScore(), Constants.SNAKE_SCORE_X, Constants.SNAKE_SCORE_Y);
	}
}
