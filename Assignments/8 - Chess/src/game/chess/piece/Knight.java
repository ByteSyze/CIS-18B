package game.chess.piece;

import java.awt.Color;
import java.awt.Graphics2D;

import game.chess.ChessMove;
import game.chess.ChessPlayer;
import game.chess.piece.ChessModels.Type;
import game.position.Position;


public class Knight extends ChessPiece
{
	private static final ChessMove[] KNIGHT_MOVES = {cmove(1,2),cmove(2,1)};
	private static final ChessModels.Model KNIGHT_MODEL = ChessModels.create(Type.KNIGHT);
	
	public Knight(ChessPlayer owner, Position boardPosition)
	{
		super(owner, boardPosition);
	}

	@Override
	public void draw(Graphics2D g)
	{
		g.setColor(getOwner().getColor());
		g.fill(KNIGHT_MODEL);
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

	@Override
	public ChessMove[] getBasicMoves()
	{
		return KNIGHT_MOVES;
	}

}
