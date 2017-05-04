package game.chess.piece;

import java.util.ArrayList;
import java.util.List;

import game.chess.ChessMove;

public class SymmetricalPiece extends ChessPieceFeature
{

	public SymmetricalPiece(ChessPiece piece) 
	{
		super(piece);
	}
	
	@Override
	public List<ChessMove> getValidMoves()
	{
		if(useCachedMoves)
			return cachedMoves;
		
		cachedMoves = symmetrize(chessPiece.getValidMoves());
		useCachedMoves = true;
		
		return cachedMoves;
	}
	
	public List<ChessMove> getLookAheadMoves()
	{
		if(useCachedLookAhead)
			return cachedLookAhead;
		
		cachedLookAhead = symmetrize(chessPiece.getLookAheadMoves());
		useCachedLookAhead = true;
			
		return cachedLookAhead;
	}
	
	/**
	 * Did you know symmetrize is a word?????
	 * */
	private List<ChessMove> symmetrize(List<ChessMove> simpleMoves)
	{
		List<ChessMove> moves = new ArrayList<ChessMove>();
		
		for(ChessMove move : simpleMoves)
		{
			ChessMove m1 = move;
			ChessMove m2 = new ChessMove(move.getY(), -move.getX());
			ChessMove m3 = new ChessMove(-move.getX(), -move.getY());
			ChessMove m4 = new ChessMove(-move.getY(), move.getX());

			moves.add(m1);
			moves.add(m2);
			moves.add(m3);
			moves.add(m4);
			
			m1 = move.getNextMove();
			
			while(m1 != null)
			{
				m2.setNextMove(new ChessMove(m1.getY(), -m1.getX()));
				m3.setNextMove(new ChessMove(-m1.getX(), -m1.getY()));
				m4.setNextMove(new ChessMove(-m1.getY(), m1.getX()));
				
				m2.getNextMove().setPreviousMove(m2);
				m3.getNextMove().setPreviousMove(m3);
				m4.getNextMove().setPreviousMove(m4);
				
				m2 = m2.getNextMove();
				m3 = m3.getNextMove();
				m4 = m4.getNextMove();
				
				m1 = m1.getNextMove();
			}
		}
		
		return moves;
	}

}
