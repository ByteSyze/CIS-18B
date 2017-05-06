package game.chess.piece.path;

import java.util.Arrays;
import java.util.List;

import game.chess.ChessMove;
import game.chess.piece.ChessPiece;

public abstract class SimplePath implements Path
{
	private ChessPiece piece;
	
	protected boolean useCachedPath;
	protected boolean useCachedPredictivePath;
	
	public SimplePath(ChessPiece piece)
	{
		this.piece = piece;
	}
	
	protected ChessPiece getPiece()
	{
		return piece;
	}
	
	public void invalidate()
	{
		this.useCachedPath = false;
		this.useCachedPredictivePath = false;
	}
	
	protected final static ChessMove cmove(int x, int y, ChessMove move)
	{
		ChessMove m = new ChessMove(x, y);
		
		move.setPreviousMove(m);
		m.setNextMove(move);
		
		return m;
	}
	
	protected final static ChessMove cmove(int x, int y)
	{
		return new ChessMove(x, y);
	}
	
	//// Base PathFinder classes /////
	
	/**
	 * Generates a list of "naive" moves for a ChessPiece. Naive moves represent the basic shape
	 * of a piece's moves in the first Quadrant, and ignores any current chess board properties.
	 * */
	protected static abstract class NaivePath extends SimplePath
	{
		public NaivePath(ChessPiece piece)
		{
			super(piece);
		}
		
		public final List<ChessMove> getValidMoves() 
		{
			return Arrays.asList(getNaiveMoves());
		}
		
		public final List<ChessMove> getPredictiveMoves()
		{
			return getValidMoves();
		}
		
		protected abstract ChessMove[] getNaiveMoves();
		
	}
	
	protected static class SimplePawnPath extends NaivePath
	{
		private static final ChessMove[] PAWN_MOVES = { cmove(0,1) };
		
		public SimplePawnPath(ChessPiece piece) 
		{
			super(piece);
		}

		protected ChessMove[] getNaiveMoves()
		{
			return PAWN_MOVES;
		}
	}
	
	protected static class SimpleBishopPath extends NaivePath
	{
		private static final ChessMove[] BISHOP_MOVES = 
			{ cmove(1,1,cmove(2,2,cmove(3,3,cmove(4,4,cmove(5,5,cmove(6,6,cmove(7,7))))))) }; //Diagonal
		
		public SimpleBishopPath(ChessPiece piece) 
		{
			super(piece);
		}

		protected ChessMove[] getNaiveMoves()
		{
			return BISHOP_MOVES;
		}
	}
	
	protected static class SimpleKnightPath extends NaivePath
	{
		private static final ChessMove[] KNIGHT_MOVES = {cmove(1,2),cmove(2,1)}; //L-Shapes
		
		public SimpleKnightPath(ChessPiece piece) 
		{
			super(piece);
		}

		protected ChessMove[] getNaiveMoves()
		{
			return KNIGHT_MOVES;
		}
	}
	
	protected static class SimpleRookPath extends NaivePath
	{
		private static final ChessMove[] ROOK_MOVES = 
			{ cmove(0,1,cmove(0,2,cmove(0,3,cmove(0,4,cmove(0,5,cmove(0,6,cmove(0,7))))))) };//Vertical
		
		public SimpleRookPath(ChessPiece piece) 
		{
			super(piece);
		}

		protected ChessMove[] getNaiveMoves()
		{
			return ROOK_MOVES;
		}
	}
	
	protected static class SimpleQueenPath extends NaivePath
	{
		private static final ChessMove[] QUEEN_MOVES = 
			{cmove(0,1,cmove(0,2,cmove(0,3,cmove(0,4,cmove(0,5,cmove(0,6,cmove(0,7))))))), //Vertical
			 cmove(1,1,cmove(2,2,cmove(3,3,cmove(4,4,cmove(5,5,cmove(6,6,cmove(7,7))))))), //Diagonal
			 cmove(1,0,cmove(2,0,cmove(3,0,cmove(4,0,cmove(5,0,cmove(6,0,cmove(7,0)))))))};//Horizontal
		
		public SimpleQueenPath(ChessPiece piece) 
		{
			super(piece);
		}

		protected ChessMove[] getNaiveMoves()
		{
			return QUEEN_MOVES;
		}
	}
	
	protected static class SimpleKingPath extends NaivePath
	{
		private static final ChessMove[] KING_MOVES = { cmove(0,1), cmove(1,1) };
		
		public SimpleKingPath(ChessPiece piece) 
		{
			super(piece);
		}

		protected ChessMove[] getNaiveMoves()
		{
			return KING_MOVES;
		}
	}

}
