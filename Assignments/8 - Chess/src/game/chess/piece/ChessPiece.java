package game.chess.piece;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import game.Game2D;
import game.GameObject;
import game.chess.ChessMove;
import game.chess.ChessPlayer;
import game.chess.piece.path.CachedPath;
import game.chess.piece.path.ChessPath;
import game.position.Position;
import game.position.ScaledPosition;

public abstract class ChessPiece implements GameObject
{
	public enum Type
	{
		PAWN,
		BISHOP,
		KNIGHT,
		ROOK,
		QUEEN,
		KING
	}
	
	private static final float POSITION_SNAP_THRESH = 0.5f;
	
	private CachedPath cachedPath;
	
	private Type type;
	
	private ScaledPosition position;
	private Position boardPosition;
	
	protected Rectangle2D bounds;
	
	private ChessPlayer owner;
	
	private boolean isCaptured;
	
	public ChessPiece(){}
	
	public ChessPiece(ChessPlayer owner, Position boardPosition, Type type)
	{
		this.owner = owner;
		
		this.type = type;
		
		this.isCaptured = false;
		
		this.cachedPath = new CachedPath();
		
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
	
	public void setPath(ChessPath path)
	{
		this.cachedPath.setPath(path);
	}
	
	public ChessPlayer getOwner()
	{
		return owner;
	}
	
	public Type getType()
	{
		return type;
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
		cachedPath.invalidate();
	}

	/**
	 * Returns a list of valid moves for this ChessPiece.
	 * */
	public List<ChessMove> getValidMoves()
	{
		return cachedPath.getValidPath();
	}
	
	/**
	 * Returns all moves this ChessPiece can make, but does not invalidate moves
	 * that pass through enemy pieces. This is useful for looking ahead in the game.
	 * */
	public List<ChessMove> getLookAheadMoves()
	{
		return cachedPath.getPredictivePath();
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
}
