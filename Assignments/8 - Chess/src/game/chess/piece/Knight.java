package game.chess.piece;

import game.chess.ChessPlayer;
import game.position.Position;

public class Knight extends ChessPiece
{
	public Knight()
	{
		super(Type.KNIGHT);
	}
	
	public Knight(ChessPlayer owner, Position boardPosition)
	{
		super(owner, boardPosition, Type.KNIGHT);
	}
}
