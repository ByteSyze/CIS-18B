package tic.tac.toe.event;

import tic.tac.toe.gui.TicTacButton;
import tic.tac.toe.manager.GameManager.Player;

public class PlayerMoveEvent extends CancellableEvent
{
	private Player player;
	private TicTacButton button;
	
	public PlayerMoveEvent(Player player, TicTacButton button)
	{
		this.player = player;
		this.button = button;
	}
	
	public Player getPlayer()
	{
		return this.player;
	}
	
	public TicTacButton getButton()
	{
		return this.button;
	}
}
