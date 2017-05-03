package game.chess.piece;

import java.util.ArrayList;
import java.util.List;

import game.chess.Chess;
import game.chess.ChessMove;
import game.position.Position;

public class BoundedPiece extends ChessPieceFeature
{
	private Chess chess;

	public BoundedPiece(ChessPiece piece, Chess chess)
	{
		super(piece);
		
		this.chess = chess;
	}

	@Override
	public List<ChessMove> getValidMoves()
	{
		List<ChessMove> moves = chessPiece.getValidMoves();

		filterLimits(moves);
		
		filterBlockedMoves(moves, chess.getPlayerOne().getAlivePieces());
		filterBlockedMoves(moves, chess.getPlayerTwo().getAlivePieces());
		
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
				moveBoardPos = Position.add(chessPiece.getBoardPosition(), chain);
				
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
	
	private void filterBlockedMoves(List<ChessMove> moves, List<ChessPiece> pieces)
	{
		List<ChessMove> invalidRootMoves = new ArrayList<ChessMove>();
		ChessPiece hit;
		
		for(ChessMove move : moves)
		{
			for(ChessMove chain : move.asList())
			{
				hit = chess.getPieceAt(Position.add(chessPiece.getBoardPosition(), chain));
				
				if(hit != null)
				{
					boolean sameOwner = (hit.getOwner() == chessPiece.getOwner());
					
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
