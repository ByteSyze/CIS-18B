package tic.tac.toe;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import tic.tac.toe.manager.GameManager;

public class Main
{
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Play TicTacToe");
		GameManager manager = GameManager.getInstance();
		Board gameBoard = new Board();
		
		gameBoard.registerTurnListener(manager);

		//Use a (marginally) less ugly L&F.
        try 
        {
			UIManager.setLookAndFeel(
			    UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e)
        {
			e.printStackTrace();
		}
		
		frame.add(gameBoard);
		frame.pack();
		frame.setMinimumSize(new Dimension(300,300));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
