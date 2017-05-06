package game.chess.piece;

import game.chess.ChessPlayer;
import game.position.Position;

public class Queen extends ChessPiece
{
	public Queen()
	{
		super(Type.QUEEN);
	}
	
	public Queen(ChessPlayer owner, Position position)
	{
		super(owner, position, Type.QUEEN);
	}
}
