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
		
		Position anchor = chessPiece.getBoardPosition();
		Position moveBoardPos;
		
		for(ChessMove move: moves)
		{
			ChessMove moveChain = move;
			ChessMove previousChain = null;
			
			while(moveChain != null)
			{
				moveBoardPos = Position.add(anchor, moveChain);
				
				if((moveBoardPos.getX() > 7) || (moveBoardPos.getY() > 7) ||
						(moveBoardPos.getX() < 0) || (moveBoardPos.getY() < 0))
				{
					if(moveChain == move)
						outOfBounds.add(move);
					else
						previousChain.setNextMove(null);
				}
				
				previousChain = moveChain;
				moveChain = moveChain.getNextMove();
				
			}
		}
		
		for(ChessMove invalid : outOfBounds)
		{
			moves.remove(invalid);
		}
	}
	
	private void filterBlockedMoves(List<ChessMove> moves, List<ChessPiece> pieces)
	{
		List<ChessMove> invalidRootMoves = new ArrayList<ChessMove>();
		
		Position anchor = chessPiece.getBoardPosition();
		
		//TODO optimize this
		for(ChessMove move : moves)
		{
			for(ChessPiece p : pieces)
			{
				ChessMove moveChain = move;
				ChessMove previousInChain = move;
				
				boolean blocked = false;
				
				while(moveChain != null)
				{
					if(Position.add(moveChain, anchor).equals(p.getBoardPosition()))
					{
						if(p.getOwner() == chessPiece.getOwner())
						{
							if(moveChain == move)
								invalidRootMoves.add(moveChain);
							else
								previousInChain.setNextMove(null);
						}
						else
						{
							moveChain.setNextMove(null);
						}
						
						blocked = true;
						
						break;
					}
					
					previousInChain = moveChain;
					moveChain = moveChain.getNextMove();
				}
				
				if(blocked)
				{
					break;
				}
			}
		}
		
		for(ChessMove invalid : invalidRootMoves)
			moves.remove(invalid);
		
	}

}
