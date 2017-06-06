package game.chess;

import java.util.ArrayList;
import java.util.List;

import game.position.Position;

public class ChessMove extends Position implements Cloneable
{
	private ChessMove prevMove;
	private ChessMove nextMove;
	
	private boolean isAttack = true;
	
	public ChessMove(float x, float y)
	{
		super(x,y);
	}
	
	public ChessMove(float x, float y, boolean attack)
	{
		super(x,y);
		
		setAttack(attack);
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
	
	public boolean isAttack()
	{
		return isAttack;
	}
	
	public void setAttack(boolean isAttack)
	{
		this.isAttack = isAttack;
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
		
		ChessMove clone = new ChessMove(getX(), getY(), isAttack());
		ChessMove cloneChain = clone;
		
		while(chain.getNextMove() != null)
		{
			cloneChain.setNextMove(new ChessMove(chain.getNextMove().getX(), chain.getNextMove().getY()));
			cloneChain.getNextMove().setPreviousMove(cloneChain);
			
			chain = chain.getNextMove();
			cloneChain = cloneChain.getNextMove();
		}
		
		return clone;
	}
}
