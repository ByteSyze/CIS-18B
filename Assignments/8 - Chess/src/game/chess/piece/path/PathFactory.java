package game.chess.piece.path;

import game.chess.Chess;
import game.chess.piece.ChessPiece;
import game.chess.piece.ChessPiece.Type;

public class PathFactory 
{
	public static ChessPath createPath(Chess chess, ChessPiece piece)
	{
		ChessPath path = SimplePath.getSimplePath(chess, piece);
		
		if(isSymmetrical(piece))
			path = new SymmetricalPath(path);
		
		path = new BoundedPath(path);
		
		if(isServant(piece))
			path = new ServantPath(path);
		else
			path = new CowardPath(path);
		
		return path;
	}
	
	private static boolean isSymmetrical(ChessPiece piece)
	{
		return (piece.getType() != Type.PAWN);
	}
	
	private static boolean isServant(ChessPiece piece)
	{
		return (piece.getType() != Type.KING);
	}

}
