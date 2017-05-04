package game.command;

import game.chess.Chess;
import game.chess.piece.ChessPiece;
import game.position.Position;

public class MoveCommand implements ReversibleCommand
{
	private Chess chess;
	
	private ChessPiece chessPiece;
	
	private Position move;
	private Position reverseMove;
	
	public MoveCommand(Chess chess, ChessPiece chessPiece, Position move)
	{
		this.chess = chess;
		this.chessPiece = chessPiece;
		this.move = move;
		
		this.reverseMove = new Position(-move.getX(), -move.getY());
	}

	@Override
	public void execute()
	{
		chess.setPieceAt(chessPiece.getBoardPosition(), null);
		
		chessPiece.getBoardPosition().move(move);
		
		chess.setPieceAt(chessPiece.getBoardPosition(), chessPiece);
		
		chess.invalidateMoves();
	}

	@Override
	public void undo()
	{
		chess.setPieceAt(chessPiece.getBoardPosition(), null);
		
		chessPiece.getBoardPosition().move(reverseMove);
		
		chess.setPieceAt(chessPiece.getBoardPosition(), chessPiece);
		
		chess.invalidateMoves();
	}

}
