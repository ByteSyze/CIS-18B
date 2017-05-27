package game.command;

import game.chess.Chess;
import game.chess.piece.ChessPiece;
import game.chess.piece.ChessPieceController;
import game.position.Position;

/**
 * Promotes a ChessPiece (typically a Pawn) to a Queen.
 * */
public class PromoteCommand extends MoveCommand
{	
	/**The new Queen that will take the place of the promoted Pawn.*/
	private ChessPieceController queen;
	
	public PromoteCommand(Chess chess, ChessPieceController chessPiece, Position move) 
	{
		super(chess, chessPiece, move);
	}
	
	public void execute()
	{
		super.execute();
		
		if(queen == null)
			queen = chess.createPiece(chessPiece.getOwner(), chessPiece.getModel().getBoardPosition(), ChessPiece.Type.QUEEN);
		
		chessPiece.getOwner().getAlivePieces().remove(chessPiece);
		chess.unregisterGameObject(chessPiece);
		
		
		queen.getOwner().addAlivePiece(queen);
		chess.registerGameObject(queen);
	}
	
	public void undo()
	{
		super.undo();
		
		queen.getOwner().getAlivePieces().remove(queen);
		chess.unregisterGameObject(queen);
		
		chessPiece.getOwner().addAlivePiece(chessPiece);
		chess.registerGameObject(chessPiece);
	}
}
