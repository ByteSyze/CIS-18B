package game.chess;

import java.awt.Color;
import java.util.Random;

import game.Game2D;
import game.chess.piece.ChessPieceController;
import game.command.MoveCommand;

public class ChessAIPlayer extends ChessPlayer
{

	public ChessAIPlayer(int playerID, Color color) 
	{
		super(playerID, color);
	}
	
	public void onTurn(Game2D game)
	{
		super.onTurn(game);
		
		if(((Chess)game).getCurrentTurn() == this)
		{
			Chess chess = (Chess)game;
			
			Random r = new Random();
			
			ChessPieceController man;
			do
			{
				man = this.getAlivePieces().get(r.nextInt(this.getAlivePieces().size()));
			}while(man.getModel().getValidMoves().size() == 0);
			
			ChessMove move = man.getModel().getValidMoves().get(r.nextInt(man.getModel().getValidMoves().size()));
			
			chess.getCommandQueue().add(new MoveCommand((Chess)game, man, move));
			chess.getCommandQueue().executeNextCommand();
			
			chess.fireTurnChangeEvent();
		}
	}

}
