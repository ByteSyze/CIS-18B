package game.chess.piece;

import game.chess.ChessPlayer;
import game.position.Position;

public class Rook extends ChessPiece
{
	public Rook()
	{
		super(Type.ROOK);
	}
	
	public Rook(ChessPlayer owner, Position boardPosition)
	{
		super(owner, boardPosition, Type.ROOK);
	}
}
