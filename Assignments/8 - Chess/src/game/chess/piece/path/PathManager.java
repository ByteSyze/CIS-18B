package game.chess.piece.path;

import java.util.List;

import game.chess.ChessMove;

/**
 * A ChessPath wrapper for caching generated paths.
 * */
public class PathManager 
{
	private ChessPath path;

	private List<ChessMove> cachedPath;
	private List<ChessMove> cachedPredictivePath;
	
	private boolean useCachedPath;
	private boolean useCachedPredictivePath;
	
	public PathManager(){}
	
	public PathManager(ChessPath path)
	{
		this.path = path;
	}
	
	public void setPath(ChessPath path)
	{
		this.path = path;
	}
	
	public final List<ChessMove> getValidPath()
	{
		if(useCachedPath)
		{
			return cachedPath;
		}
		else
		{
			cachedPath = path.generateValidPath();
			useCachedPath = true;
			
			return cachedPath;
		}
	}
	
	public final List<ChessMove> getPredictivePath()
	{
		if(useCachedPredictivePath)
		{
			return cachedPredictivePath;
		}
		else
		{
			cachedPredictivePath = path.generatePredictivePath();
			useCachedPredictivePath = true;
			
			return cachedPredictivePath;
		}
	}
	
	public void invalidate()
	{
		this.useCachedPath = false;
		this.useCachedPredictivePath = false;
	}
}
