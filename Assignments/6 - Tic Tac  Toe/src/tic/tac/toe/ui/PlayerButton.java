package tic.tac.toe.ui;

import tic.tac.toe.GameManager.Player;

public class PlayerButton extends TicTacButton
{
	private static final long serialVersionUID = 3177370613696009059L;
	
	private Player player;

	protected PlayerButton(int x, int y, Player player) 
	{
		super(x, y);
		
		this.player = player;
		
		this.setText(player.name());
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
