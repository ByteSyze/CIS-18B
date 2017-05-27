package game.chess.piece.path;

import game.chess.Chess;
import game.chess.piece.ChessPiece;
import game.chess.piece.ChessPiece.Type;
import game.chess.piece.ChessPieceController;

public class PathFactory 
{
	public static ChessPath createPath(Chess chess, ChessPieceController piece)
	{
		ChessPath path = SimplePath.getSimplePath(chess, piece);
		
		if(isSymmetrical(piece))
			path = new SymmetricalPath(path);
		else
		{
			if(piece.getOwner() == chess.getPlayerTwo())
				path = new ReversePath(path);
			
			path = new PawnPath(path);
		}
		
		path = new BoundedPath(path);
		
		if(isServant(piece))
			path = new ServantPath(path);
		else
			path = new CowardPath(path);
		
		return path;
	}
	
	private static boolean isSymmetrical(ChessPieceController piece)
	{
		return (piece.getModel().getType() != Type.PAWN);
	}
	
	private static boolean isServant(ChessPieceController piece)
	{
		return (piece.getModel().getType() != Type.KING);
	}

}
