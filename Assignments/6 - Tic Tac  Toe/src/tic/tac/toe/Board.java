package tic.tac.toe;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import tic.tac.toe.GameManager.Player;
import tic.tac.toe.command.PlayerMove;
import tic.tac.toe.event.player.PlayerMoveEvent;
import tic.tac.toe.event.player.PlayerTurnChangeEvent;
import tic.tac.toe.listeners.*;
import tic.tac.toe.ui.ButtonFactory;
import tic.tac.toe.ui.PlayerButton;
import tic.tac.toe.ui.TicTacButton;

public final class Board extends JPanel implements ActionListener
{
	private static final long serialVersionUID = -499497826266047478L;
	
	/**Listeners for changes to player turn.*/
	private List<PlayerTurnListener> turnListeners;
	
	/**Listeners for new moves made by players.*/
	private List<PlayerMoveListener> moveListeners;
	
	private GridBagConstraints constraints;

	public Board()
	{
		super();
		
		this.turnListeners = new ArrayList<PlayerTurnListener>();
		this.moveListeners = new ArrayList<PlayerMoveListener>();
		
		constraints = new GridBagConstraints();
		
		constraints.ipadx = constraints.ipady = 40;
		constraints.fill = GridBagConstraints.BOTH;
		
		this.setLayout(new GridBagLayout());
		
		//Generate a 3x3 grid of buttons.
		for(int i = 0; i < 9; i++)
		{
			int xIdx = i%3;
			int yIdx = i/3;
			
			JButton nextButton = ButtonFactory.createButton(xIdx, yIdx, null);
			
			nextButton.addActionListener(this);
			
			constraints.gridx = xIdx;
			constraints.gridy = yIdx;
			
			this.add(nextButton, constraints);
		}
	}
	
	/**
	 * Nothing makes sense anymore...
	 * */
	public void swapButtons(TicTacButton oldButton, TicTacButton newButton)
	{
		constraints.gridx = oldButton.getXIndex();
		constraints.gridy = oldButton.getYIndex();
		
		this.remove(oldButton);
		this.add(newButton, constraints);
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
		
		if(fireMoveEvent(moveEvent))
		{
			//Nobody cancelled us! Let's actually do this thing.
			TicTacButton newButton = ButtonFactory.createButton(moveEvent.getButton().getXIndex(), moveEvent.getButton().getYIndex(), moveEvent.getPlayer());
			PlayerMove successfulMove = new PlayerMove(this, moveEvent.getPlayer(), (PlayerButton)newButton);
			
			GameManager.getInstance().addPlayerMove(successfulMove);
			GameManager.getInstance().executeAllPendingMoves();

			//Check if game is over, since game win condition is checked during command execution.
			if(!GameManager.getInstance().isGameOver())
			{
				//Once a move has been successfully made, fire a player turn change event.
				Player lastPlayer = GameManager.getInstance().getCurrentTurn();
				Player nextPlayer = (lastPlayer == Player.X) ? Player.O : Player.X;
				
				PlayerTurnChangeEvent turnEvent = new PlayerTurnChangeEvent(lastPlayer, nextPlayer);
				fireTurnEvent(turnEvent);
			}
		}
	}
	
	/**
	 * @return true if the event was successful
	 * */
	public boolean fireMoveEvent(PlayerMoveEvent e)
	{
		for(int i = this.moveListeners.size()-1; i >= 0; i--)
			this.moveListeners.get(i).onPlayerMove(e);
		
		return !e.getCancelled();
	}
	
	public void fireTurnEvent(PlayerTurnChangeEvent e)
	{
		//Turn events can't be cancelled, so send and forget.
		for(int i = this.turnListeners.size()-1; i >= 0; i--)
			this.turnListeners.get(i).onPlayerTurnChange(e);
	}
}
