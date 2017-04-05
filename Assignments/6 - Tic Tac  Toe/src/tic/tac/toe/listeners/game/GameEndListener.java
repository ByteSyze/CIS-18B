package tic.tac.toe.listeners.game;

import tic.tac.toe.event.game.GameEndEvent;

public interface GameEndListener 
{
	public void onGameEnd(GameEndEvent e);
}
