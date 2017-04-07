package tic.tac.toe;

import java.util.ArrayList;
import java.util.List;

import tic.tac.toe.command.PlayerMove;
import tic.tac.toe.command.ReversibleCommandQueue;
import tic.tac.toe.event.game.GameEndEvent;
import tic.tac.toe.event.player.PlayerMoveEvent;
import tic.tac.toe.event.player.PlayerTurnChangeEvent;
import tic.tac.toe.listeners.PlayerMoveListener;
import tic.tac.toe.listeners.PlayerTurnListener;
import tic.tac.toe.listeners.game.GameEndListener;
import tic.tac.toe.ui.PlayerButton;

/**
 * Manages the game's mechanics and rules through listeners
 * */
public class GameManager implements PlayerTurnListener, PlayerMoveListener
{
	private static GameManager instance;
	
	/**
	 * Returns the single instance of GameManager.
	 * 
	 * If no instance currently exists, an instance will be created first.
	 * */
	public static GameManager getInstance()
	{
		if(instance != null)
			return instance;
		else
			return (instance = new GameManager());
	}
	
	public enum Player { X, O }
	
	/**The player that goes next.*/
	private Player turn;
	
	private boolean gameOver = false;
	
	/**The history of player moves.*/
	private ReversibleCommandQueue playerMoves;
	
	private List<GameEndListener> endListeners;

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
		
		endListeners = new ArrayList<GameEndListener>();
	}
	
	protected void setTurn(Player turn)
	{
		this.turn = turn;
	}
	
	public Player getCurrentTurn()
	{
		return this.turn;
	}
	
	public boolean isGameOver()
	{
		return this.gameOver;
	}
	
	public void addPlayerMove(PlayerMove move)
	{
		playerMoves.addCommand(move);
	}
	
	public void setMapAt(Player player, int x, int y, int val)
	{
		if(player == Player.X)
		{
			playerXMap[x][y] = val;
		}
		else if(player == Player.O)
		{
			playerOMap[x][y] = val;
		}
	}
	
	public boolean executeNextMove()
	{
		return playerMoves.executeNextCommand();
	}
	
	public void undoLastMove()
	{
		playerMoves.undoLastCommand();
	}
	
	public void executeAllPendingMoves()
	{
		while(playerMoves.executeNextCommand());
	}
	
	public void registerWinListener(GameEndListener listener)
	{
		this.endListeners.add(listener);
	}
	
	public void unregisterWinListener(GameEndListener listener)
	{
		this.endListeners.remove(listener);
	}
	
	@Override
	public void onPlayerTurnChange(PlayerTurnChangeEvent e)
	{
		this.setTurn(e.getNextTurn());
	}

	@Override
	public void onPlayerMove(PlayerMoveEvent e)
	{
		if((e.getButton() instanceof PlayerButton) || (this.gameOver))
		{
			//If the player chose an already occupied spot, cancel this event.
			e.setCancelled(true);
		}
	}
	
	public boolean checkForGameEnd(Player player)
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
				{
					fireGameEndEvent(player);
					return true;
				}
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
				{
					fireGameEndEvent(player);
					return true;
				}
			}
		}
		
		//Diagonal test 1
		for(int x = 0; x < 3; x++)
		{
			if(map[x][x] != 1)
				break;
			if(x == 2)
			{
				fireGameEndEvent(player);
				return true;
			}
		}
		
		//Diagonal test 2
		for(int x = 0; x < 3; x++)
		{
			if(map[x][2-x] != 1)
				break;
			if(x == 2)
			{
				fireGameEndEvent(player);
				return true;
			}
		}
		
		for(int i = 0; i < 9; i++)
		{
			int x = i%3;
			int y = i/3;
			
			if((playerXMap[x][y]  | playerOMap[x][y]) == 0)
			{
				//No win condition was hit, and there is at least one available spot to play.
				return false;
			}
		}
		
		//No win condition and no available spots. Cat's game.
		fireGameEndEvent(null);
		return true;
	}
	
	protected void fireGameEndEvent(Player player)
	{
		GameEndEvent endEvent = new GameEndEvent(player);
		
		for(GameEndListener listener : this.endListeners)
			listener.onGameEnd(endEvent);
		
		this.gameOver = true;
	}
}
