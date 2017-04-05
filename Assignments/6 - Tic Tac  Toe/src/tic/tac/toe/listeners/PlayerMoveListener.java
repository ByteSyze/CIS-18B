package tic.tac.toe.listeners;

import tic.tac.toe.event.player.PlayerMoveEvent;

public interface PlayerMoveListener
{
	public void onPlayerMove(PlayerMoveEvent e);
}
