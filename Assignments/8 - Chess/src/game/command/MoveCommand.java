package game.command;

import game.chess.Chess;
import game.chess.piece.ChessPiece;
import game.chess.piece.ChessPieceController;
import game.position.Position;

public class MoveCommand implements ReversibleCommand
{
	protected Chess chess;
	
	protected ChessPieceController chessPiece;
	
	protected Position move;
	
	public MoveCommand(Chess chess, ChessPieceController chessPiece, Position move)
	{
		this.chess = chess;
		this.chessPiece = chessPiece;
		this.move = move;
	}

	@Override
	public void execute()
	{
		chess.setPieceAt(chessPiece.getModel().getBoardPosition(), null);
		
		chessPiece.getModel().move(move);
		
		chess.setPieceAt(chessPiece.getModel().getBoardPosition(), chessPiece);
		
		chess.invalidateMoves();
		
		chess.fireTurnChangeEvent();
	}

	@Override
	public void undo()
	{
		chess.setPieceAt(chessPiece.getModel().getBoardPosition(), null);
		
		chessPiece.getModel().unmove(move);
		
		chess.setPieceAt(chessPiece.getModel().getBoardPosition(), chessPiece);
		
		chess.invalidateMoves();
		
		chess.fireTurnChangeEvent();
	}

}
