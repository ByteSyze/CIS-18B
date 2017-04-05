package tic.tac.toe.ui;

import javax.swing.JButton;

import tic.tac.toe.GameManager.Player;

/**
 * A simple JButton extension containing an X and Y button index.
 * */
public class TicTacButton extends JButton
{
	public static final String DEFAULT_BUTTON_TEXT = "[  ]";
	
	private int x, y;
	
	private Player player;
	
	public TicTacButton(int x, int y)
	{
		super(DEFAULT_BUTTON_TEXT);
		
		this.x = x;
		this.y = y;
		
		player = null;
	}
	
	public int getXIndex()
	{
		return this.x;
	}
	
	public int getYIndex()
	{
		return this.y;
	}
	
	public void setPlayer(Player player)
	{
		this.player = player;
	}
	
	public Player getPlayer()
	{
		return this.player;
	}
}
