package game.chess.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.chess.Chess;

public class CommandUndoListener implements ActionListener
{
	private Chess chess;
	
	public CommandUndoListener(Chess chess)
	{
		this.chess = chess;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getActionCommand().contains("Undo"))
		{
			chess.getCommandQueue().undoLastCommand();
			chess.repaint();
		}
		else if(e.getActionCommand().contains("Redo"))
		{
			chess.getCommandQueue();
		}
	}
}
