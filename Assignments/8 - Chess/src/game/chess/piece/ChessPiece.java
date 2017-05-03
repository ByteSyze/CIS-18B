package game.chess.piece;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import game.Game2D;
import game.GameObject;
import game.chess.Chess;
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
	
	public ChessPiece(){}
	
	public ChessPiece(ChessPlayer owner, Position boardPosition)
	{
		this.owner = owner;
		
		this.boardPosition = boardPosition;
		this.position = new ScaledPosition(boardPosition);
		
		this.position.setScale(50f);
		
		bounds = new Rectangle2D.Float(position.getX(),position.getY(),this.getComponentWidth(), this.getComponentHeight());
	}
	
	public void fixedUpdate(Game2D game)
	{
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
		update((Chess)game);
	}
	
	public ChessPlayer getOwner()
	{
		return owner;
	}
	
	public Shape getBounds()
	{
		return bounds;
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

	public List<ChessMove> getValidMoves()
	{
		ChessMove[] basicMoves = getBasicMoves();
		ChessMove[] basicMovesClone = new ChessMove[basicMoves.length];
		
		for(int i = 0; i < basicMoves.length; i++)
		{
			basicMovesClone[i] = basicMoves[i].clone();
		}
		
		return new ArrayList<ChessMove>(Arrays.asList(basicMovesClone));
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
	
	public abstract void update(Chess chess);
	
	/**
	 * Returns all of the moves this chess piece can make in the first Quadrant.
	 * */
	public abstract ChessMove[] getBasicMoves();

}
