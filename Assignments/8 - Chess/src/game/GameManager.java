package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class GameManager
{
	private static GameManager instance;
	
	public static GameManager instance()
	{
		if(instance != null)
			return instance;
		else
		{
			return (instance = new GameManager());
		}
	}
	
	private Timer boardUpdateTimer;
	
	private Game2D game2D;
	
	private float fps = 30f;
	private float updateInterval = 1000f/fps;
	
	public GameManager()
	{
		ActionListener updater = new ActionListener(){
			public void actionPerformed(ActionEvent arg0)
			{
				game2D.repaint();
			}
		};
		
		boardUpdateTimer = new Timer((int)updateInterval, updater);
	}
	
	public boolean startGame(Game2D game)
	{
		if(this.game2D == null)
		{
			this.game2D = game;
			
			JFrame frame = new JFrame(String.format("Play %s", game.getTitle()));
			
			//frame.setLayout(new BorderLayout());
			//frame.add(game, BorderLayout.CENTER);
			frame.add(game.getWrapper());
			frame.pack();
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			boardUpdateTimer.start();
			
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public Game2D getGame2D()
	{
		return game2D;
	}
}
