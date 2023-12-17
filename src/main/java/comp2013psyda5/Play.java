package comp2013psyda5;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

/**
 *
 * @Project Snakee
 * @Description Spilaðu leikinn
 * @Author Sigurður Sigurðardóttir
 * @version Ekki viss
 */

public class Play extends GameFrame
{

	private static final long serialVersionUID = -3641221053272056036L;

	public MySnake mySnake = new MySnake(100, 100);// x , y
	public Food food = new Food();

	public Image background = ImageLoader.images.get("UI-background");
	public Image fail = ImageLoader.images.get("game-scene-01");

	@Override
	public void keyPressed(KeyEvent e)
	{
		super.keyPressed(e);
		mySnake.keyPressed(e);
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		g.drawImage(background, 0, 0, null);

		// "Decide the position of the game."
		if (mySnake.l)
		{
			mySnake.draw(g);
			if (food.l)
			{
				food.draw(g);
				food.eaten(mySnake);
			} else
			{
				food = new Food();
			}
		} else
		{
			g.drawImage(fail, 0, 0, null);
			//	System.exit(0);

		}
		drawScore(g);
	}

	public void drawScore(Graphics g)
	{
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		g.setColor(Color.MAGENTA);
		g.drawString("SCORE : " + mySnake.score, 20, 40);
	}


	public void begin()
	{
		new Play().loadFrame();
		new MusicPlayer("src/example/frogger.mp3",true);

	}
}
