package game.chess.piece.path;

import java.util.List;

import game.chess.ChessMove;

public class ReversePath extends PathFilter
{
	public ReversePath(ChessPath path) 
	{
		super(path);
	}
	
	public List<ChessMove> generateValidPath()
	{
		return reversePath(super.generateValidPath());
	}
	
	public List<ChessMove> generatePredictivePath()
	{
		return reversePath(super.generatePredictivePath());
	}
	
	private List<ChessMove> reversePath(List<ChessMove> path)
	{
		for(ChessMove move : path)
		{
			for(ChessMove chain : move.asList())
			{
				chain.setX(-chain.getX());
				chain.setY(-chain.getY());
			}
		}
		
		return path;
	}

}
