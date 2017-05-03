package game.chess.piece;

import java.awt.Graphics2D;
import java.awt.Shape;

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
	
	public void fixedUpdate(Game2D game)
	{
		chessPiece.fixedUpdate(game);
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
