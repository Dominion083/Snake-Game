package org.example;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class MusicPlayer extends Thread
{
	private String m_filename;
	public Player m_player;

	public MusicPlayer(String filename)
	{
		this.m_filename = filename;
	}

	public void play()
	{
		new Thread()
		{
			@Override
			public void run()
			{
				super.run();
				try
				{
					//BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(filename));
					m_player = new Player(new BufferedInputStream(new FileInputStream(m_filename)));
					m_player.play();

				} catch (Exception e)
				{
					System.out.println(e);
				}
			}
		}.start();
	}



	public static void getMusicPlay(String filename)
	{
		MusicPlayer musicPlayer = new MusicPlayer(filename);
		musicPlayer.play();
	}
}
