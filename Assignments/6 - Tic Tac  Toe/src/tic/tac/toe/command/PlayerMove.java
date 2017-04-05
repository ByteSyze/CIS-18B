package tic.tac.toe.command;

import tic.tac.toe.gui.TicTacButton;
import tic.tac.toe.manager.GameManager.Player;

public class PlayerMove implements ReversibleCommand
{
	private TicTacButton button;
	private Player player;
	
	public PlayerMove(Player player, TicTacButton button)
	{
		this.player = player;
		this.button = button;
	}

	@Override
	public void execute() 
	{
		button.setText(player.name());
	}

	@Override
	public void undo() 
	{
		button.setText(TicTacButton.DEFAULT_BUTTON_TEXT);
	}

}
