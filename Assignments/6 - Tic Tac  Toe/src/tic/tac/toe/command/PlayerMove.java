package tic.tac.toe.command;

import tic.tac.toe.GameManager;
import tic.tac.toe.GameManager.Player;
import tic.tac.toe.ui.TicTacButton;

public class PlayerMove implements ReversibleCommand
{
	private TicTacButton button;
	private Player player;
	
	private String oldText;
	private Player oldPlayer;
	
	public PlayerMove(Player player, TicTacButton button)
	{
		this.player = player;
		this.button = button;
		
		this.oldText = button.getText();
		this.oldPlayer = button.getPlayer();
	}

	@Override
	public void execute() 
	{
		button.setPlayer(player);
		button.setText(player.name());
		
		GameManager.getInstance().checkforWin(player);
	}

	@Override
	public void undo() 
	{
		button.setPlayer(oldPlayer);
		button.setText(oldText);
	}

}
