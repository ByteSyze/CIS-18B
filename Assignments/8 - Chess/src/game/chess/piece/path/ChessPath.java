package game.chess.piece.path;

import java.util.List;

import game.chess.Chess;
import game.chess.ChessMove;
import game.chess.piece.ChessPieceController;

/**
 * Generates a list of valid moves for a ChessPiece.
 * */
public interface ChessPath 
{
	/**
	 * Returns the ChessPiece associated with this path.
	 * */
	public ChessPieceController getPiece();
	
	/**
	 * Returns the Chess game associated with this path.
	 * */
	public Chess getChess();
	
	/**
	 * Generates and/or uses a cached list of valid moves.
	 * 
	 * @return a list of cached valid moves, or a newly generated list of moves if this
	 *         PathFinder has been invalidated since the last call to this method.
	 * */
	//public List<ChessMove> getValidPath();

	/**
	 * Generates and/or uses a cached list of valid "predictive" moves. Predictive moves
	 * include any moves that pass through an enemy chess piece.
	 * 
	 * @return a list of cached predictive moves, or a newly generated list of moves if 
	 *         this PathFinder has been invalidated since the last call to this method.
	 * */
	//public List<ChessMove> getPredictivePath();
	
	public List<ChessMove> generateValidPath();
	public List<ChessMove> generatePredictivePath();
	
	/**
	 * Invalidates cached moves. Subsequent calls to {@link #generateValidPath()} or 
	 * {@link #generatePredictivePath()} must generate a fresh list of moves.
	 * */
	public void invalidate();
}
