package tic.tac.toe.event.game;

import tic.tac.toe.GameManager.Player;

public class GameEndEvent 
{
	private Player winner;
	
	public GameEndEvent(Player winner)
	{
		this.winner = winner;
	}
	
	public Player getWinner()
	{
		return this.winner;
	}
}
