package tic.tac.toe.listeners;

import tic.tac.toe.event.PlayerTurnChangeEvent;

public interface PlayerTurnListener 
{
	public void onPlayerTurnChange(PlayerTurnChangeEvent e);
}
