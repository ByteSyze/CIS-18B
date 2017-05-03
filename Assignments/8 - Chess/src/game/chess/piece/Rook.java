package game.chess.piece;

import java.awt.Color;
import java.awt.Graphics2D;

import game.chess.ChessMove;
import game.chess.ChessPlayer;
import game.chess.piece.ChessModels.Type;
import game.position.Position;

public class Rook extends ChessPiece
{
	private static final ChessMove[] ROOK_MOVES =
		{cmove(0,1,cmove(0,2,cmove(0,3,cmove(0,4,cmove(0,5,cmove(0,6,cmove(0,7)))))))};//Vertical
	
	private static final ChessModels.Model ROOK_MODEL = ChessModels.create(Type.ROOK);
	
	
	public Rook(ChessPlayer owner, Position boardPosition)
	{
		super(owner, boardPosition);
	}
	
	@Override
	public void draw(Graphics2D g)
	{
		g.setColor(getOwner().getColor());
		g.fill(ROOK_MODEL);
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

	@Override
	public ChessMove[] getBasicMoves() 
	{
		return ROOK_MOVES;
	}

}
