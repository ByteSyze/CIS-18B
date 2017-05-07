package game.chess.piece.path;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import game.chess.Chess;
import game.chess.ChessMove;
import game.chess.piece.ChessPiece;

public abstract class SimplePath implements ChessPath
{
	private Chess chess;
	private ChessPiece piece;
	
	public SimplePath(Chess chess, ChessPiece piece)
	{
		this.chess = chess;
		this.piece = piece;
	}
	
	public Chess getChess()
	{
		return chess;
	}
	
	public ChessPiece getPiece()
	{
		return piece;
	}
	
	/**
	 * Creates a chain of ChessMoves. A new ChessMove will be created 
	 * with the given coordinates and then appended to the specified ChessMove.
	 * 
	 * @param x    the x coordinate of the next move in the chain
	 * @param y    the y coordinate of the next move in the chain
	 * @param move the previous move in the chain
	 * */
	protected final static ChessMove cmove(int x, int y, ChessMove move)
	{
		ChessMove m = new ChessMove(x, y);
		
		move.setPreviousMove(m);
		m.setNextMove(move);
		
		return m;
	}
	
	/**
	 * Convenience method for instantiating a new ChessMove.
	 * */
	protected final static ChessMove cmove(int x, int y)
	{
		return new ChessMove(x, y);
	}
	
	//// Base PathFinder classes /////
	
	public static NaivePath getSimplePath(Chess chess, ChessPiece piece)
	{
		switch(piece.getType())
		{
			case PAWN:
				return new NaivePawnPath(chess, piece);
			case BISHOP:
				return new NaiveBishopPath(chess, piece);
			case KNIGHT:
				return new NaiveKnightPath(chess, piece);
			case ROOK:
				return new NaiveRookPath(chess, piece);
			case QUEEN:
				return new NaiveQueenPath(chess, piece);
			case KING:
				return new NaiveKingPath(chess, piece);
			default:
				return null;
		}
	}
	
	/**
	 * Generates a list of "naive" moves for a ChessPiece. Naive moves represent the basic shape
	 * of a piece's moves in the first Quadrant, and ignores any current chess board properties.
	 * */
	protected static abstract class NaivePath extends SimplePath
	{
		public NaivePath(Chess chess, ChessPiece piece)
		{
			super(chess, piece);
		}
		
		public final List<ChessMove> generateValidPath() 
		{
			ChessMove[] basicMoves = getNaiveMoves();
			ChessMove[] basicMovesClone = new ChessMove[basicMoves.length];
			
			for(int i = 0; i < basicMoves.length; i++)
			{
				basicMovesClone[i] = basicMoves[i].clone();
			}
			
			return new ArrayList<ChessMove>(Arrays.asList(basicMovesClone));
		}
		
		public final List<ChessMove> generatePredictivePath()
		{
			return generateValidPath();
		}
		
		public void invalidate(){}
		
		protected abstract ChessMove[] getNaiveMoves();
		
	}
	
	protected static class NaivePawnPath extends NaivePath
	{
		private static final ChessMove[] PAWN_FIRST_MOVES = { cmove(0,1,cmove(0,2)) };
		private static final ChessMove[] PAWN_MOVES = { cmove(0,1) };
		
		public NaivePawnPath(Chess chess, ChessPiece piece)
		{
			super(chess, piece);
		}

		protected ChessMove[] getNaiveMoves()
		{
			if(getPiece().getMoveCount() == 0)
			{
				return PAWN_FIRST_MOVES;
			}
			else
				return PAWN_MOVES;
		}
	}
	
	protected static class NaiveBishopPath extends NaivePath
	{
		private static final ChessMove[] BISHOP_MOVES = 
			{ cmove(1,1,cmove(2,2,cmove(3,3,cmove(4,4,cmove(5,5,cmove(6,6,cmove(7,7))))))) }; //Diagonal
		
		public NaiveBishopPath(Chess chess, ChessPiece piece)
		{
			super(chess, piece);
		}

		protected ChessMove[] getNaiveMoves()
		{
			return BISHOP_MOVES;
		}
	}
	
	protected static class NaiveKnightPath extends NaivePath
	{
		private static final ChessMove[] KNIGHT_MOVES = {cmove(1,2),cmove(2,1)}; //L-Shapes
		
		public NaiveKnightPath(Chess chess, ChessPiece piece)
		{
			super(chess, piece);
		}

		protected ChessMove[] getNaiveMoves()
		{
			return KNIGHT_MOVES;
		}
	}
	
	protected static class NaiveRookPath extends NaivePath
	{
		private static final ChessMove[] ROOK_MOVES = 
			{ cmove(0,1,cmove(0,2,cmove(0,3,cmove(0,4,cmove(0,5,cmove(0,6,cmove(0,7))))))) };//Vertical
		
		public NaiveRookPath(Chess chess, ChessPiece piece)
		{
			super(chess, piece);
		}

		protected ChessMove[] getNaiveMoves()
		{
			return ROOK_MOVES;
		}
	}
	
	protected static class NaiveQueenPath extends NaivePath
	{
		private static final ChessMove[] QUEEN_MOVES = 
			{cmove(0,1,cmove(0,2,cmove(0,3,cmove(0,4,cmove(0,5,cmove(0,6,cmove(0,7))))))), //Vertical
			 cmove(1,1,cmove(2,2,cmove(3,3,cmove(4,4,cmove(5,5,cmove(6,6,cmove(7,7))))))), //Diagonal
			 cmove(1,0,cmove(2,0,cmove(3,0,cmove(4,0,cmove(5,0,cmove(6,0,cmove(7,0)))))))};//Horizontal
		
		public NaiveQueenPath(Chess chess, ChessPiece piece)
		{
			super(chess, piece);
		}

		protected ChessMove[] getNaiveMoves()
		{
			return QUEEN_MOVES;
		}
	}
	
	protected static class NaiveKingPath extends NaivePath
	{
		private static final ChessMove[] KING_MOVES = { cmove(0,1), cmove(1,1) };
		
		public NaiveKingPath(Chess chess, ChessPiece piece)
		{
			super(chess, piece);
		}

		protected ChessMove[] getNaiveMoves()
		{
			return KING_MOVES;
		}
	}

}
