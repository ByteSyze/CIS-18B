package game.command;

import game.chess.Chess;
import game.chess.piece.ChessPieceController;
import game.position.Position;

public class CaptureCommand extends MoveCommand
{
	private ChessPieceController captured;
	
	public CaptureCommand(Chess chess, ChessPieceController capturer, ChessPieceController captured)
	{
		super(chess, capturer, Position.subtract(captured.getModel().getBoardPosition(), capturer.getModel().getBoardPosition()));
		
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
