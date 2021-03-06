package tic.tac.toe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import tic.tac.toe.ui.TurnLabel;

public class Main
{
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Play TicTacToe");
		
		GameManager manager = GameManager.getInstance();
		Board gameBoard = new Board();
		
		TurnLabel label = new TurnLabel();
		
		JButton undoButton = new JButton("undo");
		
		undoButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{ 
				if(!GameManager.getInstance().isGameOver())
					GameManager.getInstance().undoLastMove(); 
			}
		});
		
		//All game mechanics depend on the Game Manager's ability to listen to events.
		//In other words, these lines are absolutely vital.
		gameBoard.registerTurnListener(manager);
		gameBoard.registerMoveListener(manager);
		
		gameBoard.registerTurnListener(label);
		GameManager.getInstance().registerWinListener(label);

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
		
        frame.setLayout(new BorderLayout());

		frame.add(label, BorderLayout.NORTH);
		frame.add(gameBoard, BorderLayout.CENTER);
		frame.add(undoButton, BorderLayout.SOUTH);
		
		frame.pack();
		frame.setMinimumSize(new Dimension(300,300));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
