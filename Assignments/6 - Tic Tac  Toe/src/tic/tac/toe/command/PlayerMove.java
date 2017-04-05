package tic.tac.toe.command;

import java.awt.Component;

import tic.tac.toe.Board;
import tic.tac.toe.GameManager;
import tic.tac.toe.GameManager.Player;
import tic.tac.toe.event.player.PlayerTurnChangeEvent;
import tic.tac.toe.ui.PlayerButton;
import tic.tac.toe.ui.TicTacButton;

public class PlayerMove implements ReversibleCommand
{
	private Board gameBoard; 
	
	private PlayerButton button;
	//private Player player;
	
	private TicTacButton oldButton;
	
	public PlayerMove(Board gameBoard, Player player, PlayerButton button)
	{
		this.gameBoard = gameBoard;
		
		//this.player = player;
		this.button = button;
		
		//this.oldText = button.getText();
		//this.oldPlayer = button.getPlayer();
	}

	@Override
	public void execute() 
	{
		//button.setPlayer(player);
		//button.setText(player.name());
		
		for(Component c : gameBoard.getComponents())
		{
			if(c instanceof TicTacButton)
			{
				TicTacButton b = (TicTacButton)c;
				
				if((b.getXIndex() == button.getXIndex()) && (b.getYIndex() == button.getYIndex()))
				{
					oldButton = b;
					
					gameBoard.swapButtons(b, button);
					gameBoard.repaint();
				}
			}
		}
		
		GameManager.getInstance().setMapAt(button.getPlayer(), button.getXIndex(), button.getYIndex(), 1);
		GameManager.getInstance().checkforWin(button.getPlayer());
	}

	@Override
	public void undo() 
	{
		//button.setPlayer(oldPlayer);
		//button.setText(oldText);
		
		GameManager.getInstance().setMapAt(button.getPlayer(), button.getXIndex(), button.getYIndex(), 0);
		
		gameBoard.swapButtons(button, oldButton);
		gameBoard.repaint();
		
		gameBoard.fireTurnEvent(new PlayerTurnChangeEvent(null, button.getPlayer()));
	}

}
