package game.chess.piece;

import java.awt.Color;
import java.awt.Graphics2D;

import game.chess.ChessMove;
import game.chess.ChessPlayer;
import game.position.Position;


public class Knight extends ChessPiece
{
	private static final ChessModels.Model KNIGHT_MODEL = ChessModels.create(Type.KNIGHT);
	
	public Knight(ChessPlayer owner, Position boardPosition)
	{
		super(owner, boardPosition, Type.KNIGHT);
	}

	@Override
	public void draw(Graphics2D g)
	{
		g.setColor(getOwner().getColor());
		g.drawString(this.getClass().getSimpleName(), 10, 25);
		//g.fill(KNIGHT_MODEL);
	}

	@Override
	public void highlight(Graphics2D g)
	{
		g.setColor(Color.GREEN);
		g.draw(KNIGHT_MODEL);
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
}
