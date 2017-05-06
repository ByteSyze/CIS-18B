package game.chess.piece.path;

import java.util.List;

import game.chess.Chess;
import game.chess.ChessMove;
import game.chess.piece.ChessPiece;

public abstract class PathFilter implements ChessPath
{
	private ChessPath path;
	
	public PathFilter(ChessPath path)
	{
		this.path = path;
	}
	
	public Chess getChess()
	{
		return path.getChess();
	}
	
	public ChessPiece getPiece()
	{
		return path.getPiece();
	}
	
	public List<ChessMove> generateValidPath()
	{
		return path.generateValidPath();
	}
	
	public List<ChessMove> generatePredictivePath()
	{
		return path.generatePredictivePath();
	}
	
	public void invalidate()
	{
		path.invalidate();
	}

}
