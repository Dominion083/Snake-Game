package com.DominionDMS.SnakeGame;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;



/**
 *
 * @Project Snakee
 * @Description Hladdu leikinn og endurnýjaðu hann stöðugt
 * @Author Sigurður Sigurðardóttir
 * @version Ekki viss
 */


public class GameFrame
{
	private Canvas canvas;
	private MySnake mySnake;
	private FoodModel foodModel;
	private Image background;
	private Image fail;

	private Play play1;


	private static final long serialVersionUID = -3149926831770554380L;


	public GameFrame() {

	}

	public void handleKeyPressed(KeyEvent event, MySnake mySnake) {
		mySnake.keyPressed(event);
	}




}
