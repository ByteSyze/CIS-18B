package game.chess.piece;

import java.awt.Color;
import java.awt.Graphics2D;

import game.chess.ChessMove;
import game.chess.ChessPlayer;
import game.position.Position;

public class King extends ChessPiece
{
	private static final ChessMove[] KING_MOVES = {cmove(0,1),cmove(1,1)};
	
	public King(ChessPlayer owner, Position boardPosition)
	{
		super(owner, boardPosition);
	}

	@Override
	public ChessMove[] getBasicMoves() 
	{
		return KING_MOVES;
	}

	@Override
	public void draw(Graphics2D g)
	{
		g.setColor(getOwner().getColor());
		g.drawString(this.getClass().getSimpleName(), 10, 25);
		//g.fillOval(0, 0, (int)getComponentWidth(), (int)getComponentHeight());
	}

	@Override
	public void highlight(Graphics2D g)
	{
		g.setColor(Color.GREEN);
		g.drawOval(0, 0, (int)getComponentWidth(), (int)getComponentHeight());
	}

	@Override
	public float getComponentHeight() {
		return 50;
	}

	@Override
	public float getComponentWidth() {
		return 50;
	}

}
