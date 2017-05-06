package game.chess.piece.path;

import java.util.List;

import game.chess.ChessMove;

/**
 * Generates a list of valid moves for a ChessPiece.
 * */
public interface Path 
{
	
	/**
	 * Generates and/or caches a list of valid moves.
	 * 
	 * @return a list of cached valid moves, or a newly generated list of moves if this
	 *         PathFinder has been invalidated since the last call to this method.
	 * */
	public List<ChessMove> getValidMoves();
	
	/**
	 * Generates and/or caches a list of valid "predictive" moves. Predictive moves
	 * include any moves that pass through an enemy chess piece.
	 * 
	 * @return a list of cached predictive moves, or a newly generated list of moves if 
	 *         this PathFinder has been invalidated since the last call to this method.
	 * */
	public List<ChessMove> getPredictiveMoves();
	
	/**
	 * Invalidates cached moves. Subsequent calls to {@link #getValidMoves()} or 
	 * {@link #getPredictiveMoves()} must generate a fresh list of moves.
	 * */
	public void invalidate();
}
