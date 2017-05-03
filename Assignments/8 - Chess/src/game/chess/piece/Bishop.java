package game.chess.piece;

import java.awt.Color;
import java.awt.Graphics2D;

import game.chess.ChessMove;
import game.chess.ChessPlayer;
import game.position.Position;

public class Bishop extends ChessPiece
{
	ChessMove[] BISHOP_MOVES = { cmove(1,1,cmove(2,2,cmove(3,3,cmove(4,4,cmove(5,5,cmove(6,6,cmove(7,7))))))) };
	
	public Bishop(ChessPlayer owner, Position boardPosition)
	{
		super(owner, boardPosition);
	}

	@Override
	public void draw(Graphics2D g) 
	{
		g.setColor(getOwner().getColor());
		//g.fillRect(10,10,30,30);
		g.drawString("Bishop", 10, 25);
	}

	@Override
	public void highlight(Graphics2D g) 
	{
		g.setColor(Color.GREEN);
		g.drawRect(10, 10, 30, 30);
	}

	@Override
	public float getComponentHeight() 
	{
		return 50;
	}

	@Override
	public float getComponentWidth() 
	{
		return 50;
	}

	@Override
	public ChessMove[] getBasicMoves()
	{
		return BISHOP_MOVES;
	}

}
