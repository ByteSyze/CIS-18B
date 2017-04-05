package tic.tac.toe.ui;

import tic.tac.toe.GameManager.Player;

public final class ButtonFactory
{
	public static TicTacButton createButton(int x, int y, Player player)
	{
		if(player == null)
			return new TicTacButton(x, y);
		else
			return new PlayerButton(x, y, player);
	}

}
