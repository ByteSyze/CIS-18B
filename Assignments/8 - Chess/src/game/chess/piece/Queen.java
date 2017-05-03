package game.chess.piece;

import java.awt.Graphics2D;

import game.chess.ChessMove;
import game.chess.ChessPlayer;
import game.position.Position;

public class Queen extends ChessPiece
{
	private static final ChessMove[] QUEEN_MOVES = 
		{cmove(0,1,cmove(0,2,cmove(0,3,cmove(0,4,cmove(0,5,cmove(0,6,cmove(0,7))))))), //Vertical
		 cmove(1,1,cmove(2,2,cmove(3,3,cmove(4,4,cmove(5,5,cmove(6,6,cmove(7,7))))))), //Diagonal
		 cmove(1,0,cmove(2,0,cmove(3,0,cmove(4,0,cmove(5,0,cmove(6,0,cmove(7,0)))))))};//Horizontal

	public Queen(ChessPlayer owner, Position position)
	{
		super(owner, position);
	}
	
	@Override
	public ChessMove[] getBasicMoves()
	{
		return QUEEN_MOVES;
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
