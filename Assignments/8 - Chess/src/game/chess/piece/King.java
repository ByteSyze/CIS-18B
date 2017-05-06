package game.chess.piece;

import game.chess.ChessPlayer;
import game.position.Position;

public class King extends ChessPiece
{
	public King()
	{
		super(Type.KING);
	}
	
	public King(ChessPlayer owner, Position boardPosition)
	{
		super(owner, boardPosition, Type.KING);
	}
}
