package game.chess.piece.path;

import java.util.ArrayList;
import java.util.List;

import game.chess.ChessMove;
import game.chess.ChessPlayer;
import game.chess.piece.ChessPieceController;
import game.position.Position;

/**
 * Filters ChessMoves that fail to save the King when necessary.
 * */
public class ServantPath extends PathFilter
{
	public ServantPath(ChessPath path)
	{
		super(path);
	}
	
	public List<ChessMove> generateValidPath()
	{	
		if(getChess().getCurrentTurn() != getPiece().getOwner())
		{
			return super.generateValidPath();
		}
		
		ChessPlayer opponent = null;
		Position kingPos = getPiece().getOwner().getKing().getModel().getBoardPosition();

		if(getPiece().getOwner() == getChess().getPlayerOne())
			opponent = getChess().getPlayerTwo();
		else
			opponent = getChess().getPlayerOne();
		
		if(opponent.getValidMoveMap()[(int)kingPos.getX()][(int)kingPos.getY()] == 1)
		{
			List<ChessMove> oldValidMoves = super.generateValidPath();
			List<ChessMove> validMoves = new ArrayList<ChessMove>();
			
			List<ChessPieceController> targetEnemies = new ArrayList<ChessPieceController>(); //Enemy pieces that can reach the King.
			List<ChessMove> blockOptions = new ArrayList<ChessMove>(); //Board positions that can protect the King.
			
			boolean canBlockKing = true;
			
			for(ChessPieceController p : opponent.getAlivePieces())
			{
				for(ChessMove enemyMove : p.getModel().getValidMoves())
				{
					for(ChessMove chain : enemyMove.asList())
					{
						if(Position.add(p.getModel().getBoardPosition(), chain).equals(kingPos))
						{
							if(chain == enemyMove) 
								canBlockKing = false;
							else
							{
								ChessMove root = new ChessMove(p.getModel().getBoardPosition());
								root.setNextMove(enemyMove);
								
								blockOptions.add(root);
							}
							
							targetEnemies.add(p);
						}
					}
				}
			}
			
			if(canBlockKing && blockOptions.size() <= 1)
			{
				//Check for ways to block the King.
				for(ChessMove move : oldValidMoves)
				{
					for(ChessMove chain : move.asList())
					{
						Position chainPos = Position.add(getPiece().getModel().getBoardPosition(), chain);
						
						for(ChessMove blockOption : blockOptions)
						{
							for(ChessMove blockChain : blockOption.asList())
							{
								Position blockPos = Position.add(blockOption, blockChain);
								
								if(chainPos.equals(blockPos))
								{
									chain.setPreviousMove(null);
									chain.setNextMove(null);
									
									validMoves.add(chain);
								}
							}
						}
					}
				}
			}
			else
			{
				//If there is only one enemy chess piece in range
				//of the King, check for ways to take out that piece.
				if(targetEnemies.size() == 1)
				{
					ChessPieceController target = targetEnemies.get(0);
					
					for(ChessMove move : oldValidMoves)
					{
						for(ChessMove chain : move.asList())
						{
							if(Position.add(getPiece().getModel().getBoardPosition(), chain).equals(target.getModel().getBoardPosition()))
							{
								chain.setNextMove(null);
								validMoves.add(chain);
							}
						}
					}
				}
			}
			
			return validMoves;
		}
		
		return super.generateValidPath();
	}

}
