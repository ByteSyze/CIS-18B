package tic.tac.toe.manager;

import tic.tac.toe.command.PlayerMove;
import tic.tac.toe.command.ReversibleCommandQueue;
import tic.tac.toe.event.PlayerTurnChangeEvent;
import tic.tac.toe.listeners.PlayerTurnListener;

public class GameManager implements PlayerTurnListener
{
	private static GameManager instance;
	
	public static GameManager getInstance()
	{
		if(instance != null)
			return instance;
		else
			return (instance = new GameManager());
	}
	
	public enum Player
	{
		X, O
	}
	
	/**The player that goes next.*/
	private Player turn;
	
	/**The history of player moves.*/
	private ReversibleCommandQueue playerMoves;
	
	private GameManager()
	{
		turn = Player.X;
		
		playerMoves = new ReversibleCommandQueue();
	}
	
	protected void setTurn(Player turn)
	{
		this.turn = turn;
	}
	
	public Player getCurrentTurn()
	{
		return this.turn;
	}
	
	public void addPlayerMove(PlayerMove move)
	{
		playerMoves.addCommand(move);
	}
	
	public boolean executeNextMove()
	{
		return playerMoves.executeNextCommand();
	}
	
	public void executeAllPendingMoves()
	{
		while(playerMoves.executeNextCommand());
	}
	
	@Override
	public void onPlayerTurnChange(PlayerTurnChangeEvent e)
	{
		GameManager.getInstance().setTurn(e.getNextTurn());
	}

}
