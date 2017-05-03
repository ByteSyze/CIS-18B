package game.chess;

import java.util.ArrayList;
import java.util.List;

import game.position.Position;

public class ChessMove extends Position implements Cloneable
{
	private ChessMove prevMove;
	private ChessMove nextMove;
	
	public ChessMove(float x, float y)
	{
		super(x,y);
	}
	
	public ChessMove(Position position)
	{
		super(position.getX(), position.getY());
	}
	
	public void setPreviousMove(ChessMove prevMove)
	{
		this.prevMove = prevMove;
	}

	public void setNextMove(ChessMove nextMove)
	{
		this.nextMove = nextMove;
	}
	
	public ChessMove getPreviousMove()
	{
		return prevMove;
	}
	
	public ChessMove getNextMove()
	{
		return nextMove;
	}
	
	public List<ChessMove> asList()
	{
		List<ChessMove> chainList = new ArrayList<ChessMove>();
		ChessMove chain = this;
		
		while(chain != null)
		{
			chainList.add(chain);
			
			chain = chain.nextMove;
		}
		
		return chainList;
	}
	
	public boolean contains(Position position)
	{
		ChessMove chain = this;
		
		while(chain != null)
		{
			if(chain.equals(position))
				return true;
			
			chain = chain.nextMove;
		}
		
		return false;
	}
	
	public ChessMove clone()
	{
		ChessMove chain = this;
		
		ChessMove clone = new ChessMove(getX(), getY());
		ChessMove cloneChain = clone;
		
		while(chain.getNextMove() != null)
		{
			chain = chain.getNextMove();
			
			cloneChain.setNextMove(new ChessMove(chain.getX(), chain.getY()));
			cloneChain = cloneChain.getNextMove();
		}
		
		return clone;
	}
}
