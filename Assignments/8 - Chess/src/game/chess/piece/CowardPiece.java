package game.chess.piece;

import java.util.ArrayList;
import java.util.List;

import game.chess.Chess;
import game.chess.ChessMove;
import game.chess.ChessPlayer;
import game.position.Position;

public class CowardPiece extends ChessPieceFeature
{
	private Chess chess;
	
	public CowardPiece(ChessPiece piece, Chess chess) 
	{
		super(piece);
		
		this.chess = chess;
	}
	
	public List<ChessMove> getValidMoves()
	{
		if(useCachedMoves)
		{
			return cachedMoves;
		}
		else if(chess.getCurrentTurn() != chessPiece.getOwner())
		{
			cachedMoves = chessPiece.getValidMoves();
			useCachedMoves = true;
			
			return cachedMoves;
		}
		
		ChessPlayer opponent = (chessPiece.getOwner() == chess.getPlayerOne()) ? chess.getPlayerTwo() : chess.getPlayerOne();
		
		List<ChessMove> invalidMoves = new ArrayList<ChessMove>();
		
		cachedMoves = chessPiece.getValidMoves();
		
		for(ChessPiece enemy : opponent.getAlivePieces())
		{
			for(ChessMove enemyMove : enemy.getLookAheadMoves())
			{
				for(ChessMove enemyChain : enemyMove.asList())
				{
					for(ChessMove move : cachedMoves)
					{
						for(ChessMove chain : move.asList())
						{
							Position lookAheadPos = Position.add(chessPiece.getBoardPosition(), chain);
							Position enemyPos = Position.add(enemy.getBoardPosition(), enemyChain);
							
							//System.out.println(enemy);
							//System.out.println(lookAheadPos + " : " + enemyPos);
							
							if(lookAheadPos.equals(enemyPos))
							{
								if(chain == move)
									invalidMoves.add(move);
								else
									chain.getPreviousMove().setNextMove(null);
							}
						}
					}
				}
			}
		}
		
		cachedMoves.removeAll(invalidMoves);
		
		useCachedMoves = true;
		
		return cachedMoves;
	}

}
