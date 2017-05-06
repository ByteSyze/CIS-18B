package game.chess.piece;

import java.awt.Color;
import java.awt.Graphics2D;

import game.chess.ChessPlayer;
import game.position.Position;

public class Rook extends ChessPiece
{
	private static final ChessModels.Model ROOK_MODEL = ChessModels.create(Type.ROOK);
	
	
	public Rook(ChessPlayer owner, Position boardPosition)
	{
		super(owner, boardPosition, Type.ROOK);
	}
	
	@Override
	public void draw(Graphics2D g)
	{
		g.setColor(getOwner().getColor());
		g.drawString(this.getClass().getSimpleName(), 10, 25);
		//g.fill(ROOK_MODEL);
	}

	@Override
	public void highlight(Graphics2D g) 
	{
		g.setColor(Color.GREEN);
		g.draw(ROOK_MODEL);
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
