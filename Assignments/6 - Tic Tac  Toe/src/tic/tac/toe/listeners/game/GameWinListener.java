package tic.tac.toe.listeners.game;

import tic.tac.toe.event.game.GameWinEvent;

public interface GameWinListener 
{
	public void onGameWin(GameWinEvent e);
}
