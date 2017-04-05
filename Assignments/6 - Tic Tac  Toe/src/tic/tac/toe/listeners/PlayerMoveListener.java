package tic.tac.toe.listeners;

import tic.tac.toe.event.PlayerMoveEvent;

public interface  PlayerMoveListener
{
	public void onPlayerMove(PlayerMoveEvent e);
}
