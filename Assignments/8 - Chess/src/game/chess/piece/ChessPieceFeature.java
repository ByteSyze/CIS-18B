package game.chess.piece;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.List;

import game.Game2D;
import game.chess.Chess;
import game.chess.ChessMove;
import game.chess.ChessPlayer;
import game.position.Position;

public abstract class ChessPieceFeature extends ChessPiece
{
	protected ChessPiece chessPiece;
	
	public ChessPieceFeature(ChessPiece piece)
	{
		super();
		this.chessPiece = piece;
	}
	
	public void update(Game2D game)
	{
		chessPiece.update(game);
	}
	
	public void update(Chess chess)
	{
		chessPiece.update(chess);
	}
	
	public void draw(Graphics2D g)
	{
		chessPiece.draw(g);
	}
	
	public void highlight(Graphics2D g)
	{
		chessPiece.highlight(g);
	}
	
	public ChessPlayer getOwner()
	{
		return chessPiece.getOwner();
	}
	
	public void invalidateMoves()
	{
		super.invalidateMoves();
		
		chessPiece.invalidateMoves();
	}
	
	public List<ChessMove> getLookAheadMoves()
	{
		return chessPiece.getLookAheadMoves();
	}
	
	public Shape getBounds()
	{
		return chessPiece.getBounds();
	}
	
	public Position getPosition()
	{
		return chessPiece.getPosition();
	}
	
	public Position getBoardPosition()
	{
		return chessPiece.getBoardPosition();
	}
	
	public void setBoardPosition(Position boardPosition)
	{
		chessPiece.setBoardPosition(boardPosition);
	}
	
	public float getComponentHeight()
	{
		return chessPiece.getComponentHeight();
	}
	
	public float getComponentWidth()
	{
		return chessPiece.getComponentWidth();
	}
	
	public ChessMove[] getBasicMoves()
	{
		return chessPiece.getBasicMoves();
	}
}