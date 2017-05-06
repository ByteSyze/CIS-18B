package game.chess.piece;

import game.chess.ChessPlayer;
import game.position.Position;

public class Bishop extends ChessPiece
{
	public Bishop()
	{
		super(Type.BISHOP);
	}
	
	public Bishop(ChessPlayer owner, Position boardPosition)
	{
		super(owner, boardPosition, Type.BISHOP);
	}
}
