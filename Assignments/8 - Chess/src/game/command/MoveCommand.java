package game.command;

import game.chess.Chess;
import game.chess.piece.ChessPiece;
import game.position.Position;

public class MoveCommand implements ReversibleCommand
{
	protected Chess chess;
	
	protected ChessPiece chessPiece;
	
	protected Position move;
	
	public MoveCommand(Chess chess, ChessPiece chessPiece, Position move)
	{
		this.chess = chess;
		this.chessPiece = chessPiece;
		this.move = move;
	}

	@Override
	public void execute()
	{
		chess.setPieceAt(chessPiece.getBoardPosition(), null);
		
		chessPiece.move(move);
		
		chess.setPieceAt(chessPiece.getBoardPosition(), chessPiece);
		
		chess.invalidateMoves();
		
		chess.fireTurnChangeEvent();
	}

	@Override
	public void undo()
	{
		chess.setPieceAt(chessPiece.getBoardPosition(), null);
		
		chessPiece.unmove(move);
		
		chess.setPieceAt(chessPiece.getBoardPosition(), chessPiece);
		
		chess.invalidateMoves();
		
		chess.fireTurnChangeEvent();
	}

}
