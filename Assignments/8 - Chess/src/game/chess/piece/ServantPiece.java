package game.chess.piece;

import java.util.ArrayList;
import java.util.List;

import game.chess.Chess;
import game.chess.ChessMove;
import game.chess.ChessPlayer;
import game.position.Position;

public class ServantPiece extends ChessPieceFeature
{
	private Chess chess;
	
	public ServantPiece(ChessPiece piece, Chess chess) 
	{
		super(piece);
		
		this.chess = chess;
	}
	
	public List<ChessMove> getValidMoves()
	{	
		if(chess.getCurrentTurn() != chessPiece.getOwner())
		{
			return chessPiece.getValidMoves();
		}
		
		ChessPlayer opponent = null;
		Position kingPos = chessPiece.getOwner().getKing().getBoardPosition();

		if(chessPiece.getOwner() == chess.getPlayerOne())
			opponent = chess.getPlayerTwo();
		else
			opponent = chess.getPlayerOne();
		
		if(opponent.getValidMoveMap()[(int)kingPos.getX()][(int)kingPos.getY()] == 1)
		{
			List<ChessMove> oldValidMoves = chessPiece.getValidMoves();
			List<ChessMove> validMoves = new ArrayList<ChessMove>();
			
			List<ChessPiece> targetEnemies = new ArrayList<ChessPiece>();
			List<ChessMove> blockOptions = new ArrayList<ChessMove>(); //Board positions that can block the King.
			
			boolean canBlockKing = true;
			
			for(ChessPiece p : opponent.getAlivePieces())
			{
				for(ChessMove enemyMove : p.getValidMoves())
				{
					for(ChessMove chain : enemyMove.asList())
					{
						if(Position.add(p.getBoardPosition(), chain).equals(kingPos))
						{
							if(chain == enemyMove) 
								canBlockKing = false;
							else
							{
								ChessMove root = new ChessMove(p.getBoardPosition());
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
						Position chainPos = Position.add(getBoardPosition(), chain);
						
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
					ChessPiece target = targetEnemies.get(0);
					
					for(ChessMove move : oldValidMoves)
					{
						for(ChessMove chain : move.asList())
						{
							if(Position.add(chessPiece.getBoardPosition(), chain).equals(target.getBoardPosition()))
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
		
		return chessPiece.getValidMoves();
	}

	
}
