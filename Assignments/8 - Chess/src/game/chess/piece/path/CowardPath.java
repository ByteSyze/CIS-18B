package game.chess.piece.path;

import java.util.ArrayList;
import java.util.List;

import game.chess.ChessMove;
import game.chess.ChessPlayer;
import game.chess.piece.ChessPieceController;
import game.position.Position;

/**
 * Filters ChessMoves that put a ChessPiece in danger of being captured.
 * */
public class CowardPath extends PathFilter
{

	public CowardPath(ChessPath path) 
	{
		super(path);
	}
	
	public List<ChessMove> generateValidPath()
	{
		if(getChess().getCurrentTurn() != getPiece().getOwner())
		{
			return super.generateValidPath();
		}
		
		ChessPlayer opponent = (getPiece().getOwner() == getChess().getPlayerOne()) ? getChess().getPlayerTwo() : getChess().getPlayerOne();
		
		List<ChessMove> invalidMoves = new ArrayList<ChessMove>();
		
		List<ChessMove> validMoves = super.generateValidPath();
		
		for(ChessPieceController enemy : opponent.getAlivePieces())
		{
			for(ChessMove enemyMove : enemy.getModel().getLookAheadMoves())
			{
				if(enemyMove.isAttack())
				{
					for(ChessMove enemyChain : enemyMove.asList())
					{
						for(ChessMove move : validMoves)
						{
							for(ChessMove chain : move.asList())
							{
								Position lookAheadPos = Position.add(getPiece().getModel().getBoardPosition(), chain);
								Position enemyPos = Position.add(enemy.getModel().getBoardPosition(), enemyChain);
								
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
		}
		
		validMoves.removeAll(invalidMoves);
		
		return validMoves;
	}

}
