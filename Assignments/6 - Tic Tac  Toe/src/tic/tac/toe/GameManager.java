package tic.tac.toe;

import tic.tac.toe.command.PlayerMove;
import tic.tac.toe.command.ReversibleCommandQueue;
import tic.tac.toe.event.PlayerMoveEvent;
import tic.tac.toe.event.PlayerTurnChangeEvent;
import tic.tac.toe.listeners.PlayerMoveListener;
import tic.tac.toe.listeners.PlayerTurnListener;

/**
 * Manages the game's mechanics and rules through listeners
 * */
public class GameManager implements PlayerTurnListener, PlayerMoveListener
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
	
	private boolean gameRunning = true;
	
	/**The history of player moves.*/
	private ReversibleCommandQueue playerMoves;

	/**A map of player 1's moves on the current board.*/
	private int playerXMap[][] = {{0, 0, 0},
								  {0, 0, 0},
								  {0, 0, 0}};
	
	/**A map of player 2's moves on the current board.*/
	private int playerOMap[][] = {{0, 0, 0},
			  					  {0, 0, 0},
			  					  {0, 0, 0}};
	
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
		this.setTurn(e.getNextTurn());
	}

	@Override
	public void onPlayerMove(PlayerMoveEvent e)
	{
		if((e.getButton().getPlayer() != null) || (!this.gameRunning))
		{
			//If the player chose an already occupied spot, cancel this event.
			e.setCancelled(true);
		}
		else
		{
			if(e.getPlayer() == Player.X)
			{
				playerXMap[e.getButton().getXIndex()][e.getButton().getYIndex()] = 1;
			}
			else if(e.getPlayer() == Player.O)
			{
				playerOMap[e.getButton().getXIndex()][e.getButton().getYIndex()] = 1;
			}
		}
	}
	
	public void checkforWin(Player player)
	{
		int[][] map = (player == Player.X) ? playerXMap : playerOMap;
		
		//Vertical test
		for(int x = 0; x < 3; x++)
		{	
			for(int y = 0; y < 3; y++)
			{
				if(map[x][y] != 1)
					break;
				if(y == 2)
					win(player);
			}
		}
		//Horizontal test
		for(int x = 0; x < 3; x++)
		{	
			for(int y = 0; y < 3; y++)
			{
				if(map[y][x] != 1)
					break;
				if(y == 2)
					win(player);
			}
		}
		
		//Diagonal test 1
		for(int x = 0; x < 3; x++)
		{
			if(map[x][x] != 1)
				break;
			if(x == 2)
				win(player);
		}
		
		//Diagonal test 2
		for(int x = 0; x < 3; x++)
		{
			if(map[x][2-x] != 1)
				break;
			if(x == 2)
				win(player);
		}
	}
	
	protected void win(Player player)
	{
		System.out.println("Winnner "  + player);
		this.gameRunning = false;
	}

}
