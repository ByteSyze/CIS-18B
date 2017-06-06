package game.chess.piece.path;

import java.util.List;

import game.chess.ChessMove;
import game.chess.ChessPlayer;
import game.chess.piece.ChessPieceController;
import game.position.Position;

public class PawnPath extends PathFilter
{
	public PawnPath(ChessPath path) 
	{
		super(path);
	}

	public List<ChessMove> generateValidPath()
	{
		List<ChessMove> path = super.generateValidPath();
		
		ChessPlayer opponent = (getPiece().getOwner() == getChess().getPlayerOne()) ? getChess().getPlayerTwo() : getChess().getPlayerOne();

		boolean pawnMoveInvalid = false;
		
		ChessMove pawnMove        = path.get(0);	
		ChessMove pawnAttackMove1 = new ChessMove(1,pawnMove.getY());
		ChessMove pawnAttackMove2 = new ChessMove(-1,pawnMove.getY());
		
		Position pawnMovePos    = Position.add(getPiece().getModel().getBoardPosition(), pawnMove);
		Position pawnAttackPos1 = Position.add(getPiece().getModel().getBoardPosition(), pawnAttackMove1);
		Position pawnAttackPos2 = Position.add(getPiece().getModel().getBoardPosition(), pawnAttackMove2);
		
		for(ChessPieceController enemy : opponent.getAlivePieces())
		{
			if(enemy.getModel().getBoardPosition().equals(pawnMovePos))
			{
				//pawnMoveInvalid = true;
			}
			else if(enemy.getModel().getBoardPosition().equals(pawnAttackPos1))
			{
				path.add(pawnAttackMove1);
			}
			else if(enemy.getModel().getBoardPosition().equals(pawnAttackPos2))
			{
				path.add(pawnAttackMove2);
			}
		}
		
		if(pawnMoveInvalid)
			path.remove(pawnMove);
		
		return path;
	}
	
	public List<ChessMove> generatePredictivePath()
	{
		////
		// If you think this looks like some shady workaround coding...
		// That's because it is :)
		////
		List<ChessMove> moves = super.generatePredictivePath();
		ChessMove fwd = moves.get(0);
		
		moves.add(new ChessMove(-1,fwd.getY()));
		moves.add(new ChessMove(1,fwd.getY()));
		
		return moves;
	}
}
