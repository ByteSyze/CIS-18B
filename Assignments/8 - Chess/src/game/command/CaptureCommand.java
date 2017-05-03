package game.command;

import game.chess.Chess;
import game.chess.piece.ChessPiece;
import game.position.Position;

public class CaptureCommand extends MoveCommand
{
	private ChessPiece captured;
	
	public CaptureCommand(Chess chess, ChessPiece capturer, ChessPiece captured)
	{
		super(chess, capturer, Position.subtract(captured.getBoardPosition(), capturer.getBoardPosition()));
		
		this.captured = captured;
	}

	@Override
	public void execute()
	{
		super.execute();
		
		this.captured.setCaptured(true);
		
		this.captured.getOwner().getAlivePieces().remove(captured);
		this.captured.getOwner().getSlainPieces().add(captured);
	}

	@Override
	public void undo() 
	{
		super.undo();
		
		this.captured.setCaptured(false);
		
		this.captured.getOwner().getAlivePieces().add(captured);
		this.captured.getOwner().getSlainPieces().remove(captured);
	}

}
