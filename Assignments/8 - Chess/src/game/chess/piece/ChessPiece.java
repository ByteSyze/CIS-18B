package game.chess.piece;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import game.Game2D;
import game.GameObject;
import game.chess.ChessMove;
import game.chess.ChessPlayer;
import game.position.Position;
import game.position.ScaledPosition;

public abstract class ChessPiece implements GameObject
{
	private static final float POSITION_SNAP_THRESH = 0.5f;
	
	private ScaledPosition position;
	private Position boardPosition;
	
	protected Rectangle2D bounds;
	
	private ChessPlayer owner;
	
	private boolean isCaptured;
	
	protected List<ChessMove> cachedLookAhead;
	protected List<ChessMove> cachedMoves;
	
	protected boolean useCachedLookAhead;
	protected boolean useCachedMoves;
	
	public ChessPiece(){}
	
	public ChessPiece(ChessPlayer owner, Position boardPosition)
	{
		this.owner = owner;
		
		this.isCaptured = false;
		
		this.cachedLookAhead = new ArrayList<ChessMove>();
		this.cachedMoves = new ArrayList<ChessMove>();
		
		this.useCachedMoves = false;
		
		this.boardPosition = boardPosition;
		this.position = new ScaledPosition(boardPosition);
		
		this.position.setScale(50f);
		
		this.bounds = new Rectangle2D.Float(position.getX(),position.getY(),this.getComponentWidth(), this.getComponentHeight());
	}
	
	public void update(Game2D game)
	{
		//TODO use transforms to help with all of this junk.
		
		ScaledPosition scaledBoardPos = new ScaledPosition(getBoardPosition());
		ScaledPosition pos = (ScaledPosition)getPosition();
		
		scaledBoardPos.setScale(pos.getScale());
		
		if(pos.distanceSq(scaledBoardPos) > POSITION_SNAP_THRESH)
		{
			pos.move(new Position((scaledBoardPos.getX()-pos.getX())/500f,(scaledBoardPos.getY()-pos.getY())/500f));
		}
		else if((pos.getX() != scaledBoardPos.getX()) || (pos.getY() != scaledBoardPos.getY()))
		{
			pos.setX(scaledBoardPos.getUnscaledX());
			pos.setY(scaledBoardPos.getUnscaledY());
		}
		
		bounds.setRect(position.getX()*game.getXScale(), position.getY()*game.getYScale(), getComponentWidth()*game.getXScale(), getComponentHeight()*game.getYScale());
	}
	
	public ChessPlayer getOwner()
	{
		return owner;
	}
	
	public Shape getBounds()
	{
		return bounds;
	}
	
	public boolean isCaptured()
	{
		return isCaptured;
	}
	
	public void setCaptured(boolean captured)
	{
		this.isCaptured = captured;
	}
	
	public boolean isActive()
	{
		return !isCaptured();
	}
	
	/**
	 * Returns the true position of this chess piece.
	 * */
	public Position getPosition()
	{
		return position;
	}
	
	public Position getBoardPosition()
	{
		return boardPosition;
	}
	
	public void setBoardPosition(Position boardPosition)
	{
		this.boardPosition = boardPosition;
	}
	
	/**
	 * Invalidates any cached valid moves for this piece. A subsequent call to {@link ChessPiece#getValidMoves() getValidMoves()}
	 * must recalculate valid moves at least once.
	 * */
	public void invalidateMoves()
	{
		useCachedLookAhead = false;
		useCachedMoves = false;
	}

	/**
	 * Returns a list of valid moves for this ChessPiece.
	 * 
	 * At the concrete ChessPiece level, this simply returns the basic path the ChessPiece
	 * can move in for the first Quadrant. Decorators are then used to filter out invalid moves
	 * based on the current state of the game.
	 * */
	public List<ChessMove> getValidMoves()
	{
		if(useCachedMoves)
		{
			return cachedMoves;
		}
		else
		{
			ChessMove[] basicMoves = getBasicMoves();
			ChessMove[] basicMovesClone = new ChessMove[basicMoves.length];
			
			for(int i = 0; i < basicMoves.length; i++)
			{
				basicMovesClone[i] = basicMoves[i].clone();
			}
			
			cachedMoves = new ArrayList<ChessMove>(Arrays.asList(basicMovesClone));
			useCachedMoves = true;
			
			return cachedMoves;
		}
	}
	
	/**
	 * Returns all moves this ChessPiece can make, but does not invalidate moves
	 * that pass through enemy pieces. This is useful for looking ahead in the game.
	 * */
	public List<ChessMove> getLookAheadMoves()
	{
		if(useCachedLookAhead)
		{
			return cachedLookAhead;
		}
		else
		{
			ChessMove[] basicMoves = getBasicMoves();
			ChessMove[] basicMovesClone = new ChessMove[basicMoves.length];
			
			for(int i = 0; i < basicMoves.length; i++)
			{
				basicMovesClone[i] = basicMoves[i].clone();
			}
			
			cachedLookAhead = new ArrayList<ChessMove>(Arrays.asList(basicMovesClone));
			useCachedLookAhead = true;
			
			return cachedLookAhead;
		}
	}
	
	protected final static ChessMove cmove(int x, int y, ChessMove move)
	{
		ChessMove m = new ChessMove(x, y);
		
		move.setPreviousMove(m);
		m.setNextMove(move);
		
		return m;
	}
	
	protected final static ChessMove cmove(int x, int y)
	{
		return new ChessMove(x, y);
	}
	
	/**
	 * Returns all of the moves this chess piece can make in the first Quadrant.
	 * */
	protected abstract ChessMove[] getBasicMoves();

}
