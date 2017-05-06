package game.chess.piece.path;

import java.util.List;

import game.chess.ChessMove;

public abstract class PathFilter implements Path
{
	private Path path;
	
	public PathFilter(Path path)
	{
		this.path = path;
	}
	
	public List<ChessMove> getValidMoves()
	{
		return path.getValidMoves();
	}
	
	public List<ChessMove> getPredictiveMoves()
	{
		return path.getPredictiveMoves();
	}
	
	public void invalidate()
	{
		path.invalidate();
	}

}
