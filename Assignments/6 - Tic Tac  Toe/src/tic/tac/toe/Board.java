package tic.tac.toe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import tic.tac.toe.GameManager.Player;
import tic.tac.toe.command.PlayerMove;
import tic.tac.toe.event.PlayerMoveEvent;
import tic.tac.toe.event.PlayerTurnChangeEvent;
import tic.tac.toe.listeners.*;
import tic.tac.toe.ui.TicTacButton;

public final class Board extends JPanel implements ActionListener
{
	private static final long serialVersionUID = -499497826266047478L;
	
	/**Listeners for changes to player turn.*/
	private List<PlayerTurnListener> turnListeners;
	
	/**Listeners for new moves made by players.*/
	private List<PlayerMoveListener> moveListeners;

	public Board()
	{
		super();
		
		this.turnListeners = new ArrayList<PlayerTurnListener>();
		this.moveListeners = new ArrayList<PlayerMoveListener>();
		
		this.setLayout(new GridLayout(3, 3));
		
		//Generate a 3x3 grid of buttons.
		for(int i = 0; i < 9; i++)
		{
			JButton nextButton = new TicTacButton(i%3, i/3);
			
			nextButton.addActionListener(this);
			
			this.add(nextButton);
		}
	}
	
	public void registerTurnListener(PlayerTurnListener listener)
	{
		this.turnListeners.add(listener);
	}
	
	public void unregisterTurnListener(PlayerTurnListener listener)
	{
		this.turnListeners.remove(listener);
	}
	
	public void registerMoveListener(PlayerMoveListener listener)
	{
		this.moveListeners.add(listener);
	}
	
	public void unregisterMoveListener(PlayerMoveListener listener)
	{
		this.moveListeners.remove(listener);
	}
	
	/**
	 * Called whenever a button on the game board is pressed. Relays actions to more specific listeners.
	 * */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		PlayerMoveEvent moveEvent = new PlayerMoveEvent(GameManager.getInstance().getCurrentTurn(), (TicTacButton)e.getSource());
		
		for(int i = this.moveListeners.size()-1; i >= 0; i--)
			this.moveListeners.get(i).onPlayerMove(moveEvent);
		
		if(moveEvent.getCancelled() == false)
		{
			//Nobody cancelled us! Let's actually do this thing.
			PlayerMove successfulMove = new PlayerMove(moveEvent.getPlayer(), moveEvent.getButton());
			
			GameManager.getInstance().addPlayerMove(successfulMove);
			GameManager.getInstance().executeAllPendingMoves();

			//Once a move has been successfully made, fire a player turn change event.
			Player lastPlayer = GameManager.getInstance().getCurrentTurn();
			Player nextPlayer = (lastPlayer == Player.X) ? Player.O : Player.X;
			
			PlayerTurnChangeEvent turnEvent = new PlayerTurnChangeEvent(lastPlayer, nextPlayer);

			//Turn events can't be cancelled, so send and forget.
			for(int i = this.turnListeners.size()-1; i >= 0; i--)
				this.turnListeners.get(i).onPlayerTurnChange(turnEvent);
		}
		
		
	}
}
