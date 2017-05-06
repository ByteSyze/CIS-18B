package game.chess.piece;

import game.chess.ChessPlayer;
import game.position.Position;

public class Pawn extends ChessPiece
{
	public Pawn()
	{
		super(Type.PAWN);
	}
	
	public Pawn(ChessPlayer owner, Position boardPosition, Type type)
	{
		super(owner, boardPosition, type);
	}
}
