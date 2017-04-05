package tic.tac.toe.event;

import tic.tac.toe.manager.GameManager.Player;

public class PlayerTurnChangeEvent extends CancellableEvent
{
	private Player lastTurn, nextTurn;
	
	public PlayerTurnChangeEvent(Player lastTurn, Player nextTurn)
	{
		this.lastTurn = lastTurn;
		this.nextTurn = nextTurn;
	}
	
	public Player getLastTurn()
	{
		return this.lastTurn;
	}
	
	public Player getNextTurn()
	{
		return this.nextTurn;
	}
}
