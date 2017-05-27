package game.chess.piece.path;

import java.util.ArrayList;
import java.util.List;

import game.chess.ChessMove;
import game.chess.piece.ChessPiece;
import game.chess.piece.ChessPieceController;
import game.position.Position;

/**
 * Filters out ChessMoves that go outside the bounds of the chess board,
 * as well as moves that are blocked by other ChessPieces.
 * */
public class BoundedPath extends PathFilter
{
	public BoundedPath(ChessPath path) 
	{
		super(path);
	}
	
	public List<ChessMove> generateValidPath()
	{
		List<ChessMove> moves = super.generateValidPath();
		
		filterLimits(moves);
		
		filterBlockedMoves(moves, getChess().getPlayerOne().getAlivePieces());
		filterBlockedMoves(moves, getChess().getPlayerTwo().getAlivePieces());
		
		return moves;
	}
	
	public List<ChessMove> generatePredictivePath()
	{
		List<ChessMove> moves = super.generatePredictivePath();
		
		filterLimits(moves);
		
		return moves;
	}

	private void filterLimits(List<ChessMove> moves)
	{
		List<ChessMove> outOfBounds = new ArrayList<ChessMove>();
		
		Position moveBoardPos;
		
		for(ChessMove move : moves)
		{
			for(ChessMove chain : move.asList())
			{
				moveBoardPos = Position.add(getPiece().getModel().getBoardPosition(), chain);
				
				if((moveBoardPos.getX() > 7) || (moveBoardPos.getY() > 7) ||
						(moveBoardPos.getX() < 0) || (moveBoardPos.getY() < 0))
				{
					if(chain == move)
						outOfBounds.add(move);
					else
						chain.getPreviousMove().setNextMove(null);
				}
			}
		}
		
		moves.removeAll(outOfBounds);
	}
	
	private void filterBlockedMoves(List<ChessMove> moves, List<ChessPieceController> pieces)
	{
		List<ChessMove> invalidRootMoves = new ArrayList<ChessMove>();
		ChessPieceController hit;
		
		for(ChessMove move : moves)
		{
			for(ChessMove chain : move.asList())
			{
				hit = getChess().getPieceAt(Position.add(getPiece().getModel().getBoardPosition(), chain));
				
				if(hit != null)
				{
					boolean sameOwner = (hit.getOwner() == getPiece().getOwner());
					
					if(chain == move && sameOwner)
						invalidRootMoves.add(move);
					else if(sameOwner)
						chain.getPreviousMove().setNextMove(null);
					else
						chain.setNextMove(null);
				}
			}
		}
		
		moves.removeAll(invalidRootMoves);
	}

}
