package tic.tac.toe.listeners;

import tic.tac.toe.event.player.PlayerTurnChangeEvent;

public interface PlayerTurnListener 
{
	public void onPlayerTurnChange(PlayerTurnChangeEvent e);
}
