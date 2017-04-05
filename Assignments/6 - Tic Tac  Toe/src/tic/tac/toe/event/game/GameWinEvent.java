package tic.tac.toe.event.game;

import tic.tac.toe.GameManager.Player;

public class GameWinEvent 
{
	private Player winner;
	
	public GameWinEvent(Player winner)
	{
		this.winner = winner;
	}
	
	public Player getWinner()
	{
		return this.winner;
	}
}
