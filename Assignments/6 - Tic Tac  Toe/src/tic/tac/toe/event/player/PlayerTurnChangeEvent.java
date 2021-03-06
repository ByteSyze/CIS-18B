package tic.tac.toe.event.player;

import tic.tac.toe.GameManager.Player;

public class PlayerTurnChangeEvent
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
