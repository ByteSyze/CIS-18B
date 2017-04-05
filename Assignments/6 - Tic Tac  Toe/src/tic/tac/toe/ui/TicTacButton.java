package tic.tac.toe.ui;

import javax.swing.JButton;

/**
 * A simple JButton extension containing an X and Y button index.
 * */
public class TicTacButton extends JButton
{
	private static final long serialVersionUID = -817835188536263116L;

	public static final String DEFAULT_BUTTON_TEXT = "[  ]";
	
	private int x, y;
	
	protected TicTacButton(int x, int y)
	{
		super(DEFAULT_BUTTON_TEXT);
		
		this.x = x;
		this.y = y;
	}
	
	public int getXIndex()
	{
		return this.x;
	}
	
	public int getYIndex()
	{
		return this.y;
	}
}
