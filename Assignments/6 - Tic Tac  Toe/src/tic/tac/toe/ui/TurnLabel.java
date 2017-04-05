package tic.tac.toe.ui;

import javax.swing.JLabel;

import tic.tac.toe.GameManager;
import tic.tac.toe.event.PlayerTurnChangeEvent;
import tic.tac.toe.listeners.PlayerTurnListener;

public class TurnLabel extends JLabel implements PlayerTurnListener
{
	private static final long serialVersionUID = -5293776413892269343L;

	public TurnLabel()
	{
		super("Player " + GameManager.getInstance().getCurrentTurn() + "'s turn");
	}
	
	@Override
	public void onPlayerTurnChange(PlayerTurnChangeEvent e)
	{
		this.setText("Player " + e.getNextTurn() + "'s turn");
	}

}
