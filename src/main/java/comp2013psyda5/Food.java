package comp2013psyda5;

import java.awt.Graphics;
import java.util.Random;

public class Food extends GameFrame.SnakeObject
{

	private static final long serialVersionUID = -3641221053272056036L;



	public Food()	{
		this.l = true;

		this.i = ImageLoader.images.get(String.valueOf(new Random().nextInt(10)));

		this.w = i.getWidth(null);
		this.h = i.getHeight(null);

		this.x = (int) (Math.random() * (870 - w + 10));
		this.y = (int) (Math.random() * (560 - h - 40));
	}

	public void eaten(GameFrame.MySnake mySnake)	{

		if (mySnake.getRectangle().intersects(this.getRectangle()) && l && mySnake.l)		{
			this.l = false;
			mySnake.changeLength(mySnake.getLength() + 1);
			mySnake.score += 521;
		}
	}
	@Override
	public void draw(Graphics g)
	{
		g.drawImage(i, x, y, null);
	}
}
