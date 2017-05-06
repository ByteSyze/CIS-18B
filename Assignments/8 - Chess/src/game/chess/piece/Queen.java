package game.chess.piece;

import java.awt.Graphics2D;

import game.chess.ChessPlayer;
import game.position.Position;

public class Queen extends ChessPiece
{
	public Queen(ChessPlayer owner, Position position)
	{
		super(owner, position, Type.QUEEN);
	}

	@Override
	public void draw(Graphics2D g)
	{
		
	}

	@Override
	public void highlight(Graphics2D g)
	{
		
	}

	@Override
	public float getComponentHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getComponentWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

}
