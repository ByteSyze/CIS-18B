package game.command;

import game.chess.piece.ChessPiece;
import game.position.Position;

public class MoveCommand implements ReversibleCommand
{
	private ChessPiece chessPiece;
	
	private Position move;
	private Position reverseMove;
	
	public MoveCommand(ChessPiece chessPiece, Position move)
	{
		this.chessPiece = chessPiece;
		this.move = move;
		
		this.reverseMove = new Position(-move.getX(), -move.getY());
	}

	@Override
	public void execute()
	{
		chessPiece.getBoardPosition().move(move);
	}

	@Override
	public void undo()
	{
		chessPiece.getBoardPosition().move(reverseMove);
	}

}
