package game.chess.piece;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.List;

import game.Game2D;
import game.GameObject;
import game.chess.ChessMove;
import game.chess.ChessPlayer;
import game.chess.piece.path.CachedPath;
import game.chess.piece.path.ChessPath;
import game.position.Position;
import game.position.ScaledPosition;

public class ChessPiece implements GameObject
{
	public enum Type { PAWN, BISHOP, KNIGHT, ROOK, QUEEN, KING }
	
	private static final float POSITION_SNAP_THRESH = 0.5f;
	
	private ChessModels.Model model;
	
	private Type type;
	
	private ChessPlayer owner;
	
	private CachedPath cachedPath;
	
	private ScaledPosition position;
	private Position boardPosition;
	
	protected Rectangle2D bounds;
	
	private boolean isCaptured;
	
	/**The number of times this ChessPiece has been moved.*/
	private int moveCount = 0;
	
	public ChessPiece(Type type)
	{
		this.type = type;
	}
	
	public ChessPiece(ChessPlayer owner, Position boardPosition, Type type)
	{
		this.owner = owner;
		
		this.type = type;
		
		this.isCaptured = false;
		
		this.model = ChessModels.create(type);
		
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

	public void draw(Graphics2D g) 
	{
		g.setColor(owner.getColor());
		//g.drawString(type.name(), 0, 25);
		g.fill(model);
	}

	public void highlight(Graphics2D g) 
	{
		g.setColor(Color.GREEN);
		g.draw(model);
	}
	
	public void setPath(ChessPath path)
	{
		this.cachedPath.setPath(path);
	}
	
	public ChessPlayer getOwner()
	{
		return owner;
	}
	
	public void setOwner(ChessPlayer owner)
	{
		this.owner = owner;
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
	 * Returns the number of times this ChessPiece has been moved.
	 * */
	public int getMoveCount()
	{
		return moveCount;
	}
	
	/**
	 * Moves this ChessPiece by {@code pos} relative to the ChessPiece's current position.
	 * 
	 * @param pos the relative change in position
	 * */
	public void move(Position pos)
	{
		moveCount++;
		getBoardPosition().move(pos);
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
	
	public float getComponentHeight()
	{
		return 50f;
	}

	public float getComponentWidth() 
	{
		return 50f;
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
